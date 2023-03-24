from flask import Flask

import os
import glob

import common
import weight
import step_daily_trend
import calories_burned
import stress

app = Flask(__name__)

def makeDF(type, csv):
    list = []
    df = type.readCsv(csv)
    day_df = type.dayDF(df)
    list.append(day_df)

    week_df = type.periodDF(day_df, '1W')
    list.append(week_df)
    month_df = type.periodDF(day_df, '1M')
    list.append(month_df)
    return list

@app.route('/')
def home():
    return 'Hello, World!'

@app.route('/upload/<userId>')
def upload(userId):
    db = common.connectDB()
    s3 = common.connectS3()
    common.decompress(s3, userId)

    files = glob.glob('samsunghealth/*')
    file = glob.glob(files[0] + '/*')

    weight_list = []
    step_daily_trend_list = []
    calories_burned_list = []
    stress_list = []

    for csv in file:
        if 'weight' in csv:
            weight_list = makeDF(weight, csv)
        if 'step_daily_trend' in csv:
            step_daily_trend_list = makeDF(step_daily_trend, csv)
        if 'calories_burned' in csv:
            calories_burned_list = makeDF(calories_burned, csv)
        if 'stress' in csv and 'histogram' not in csv:
            stress_list = makeDF(stress, csv)

    day = common.combine(calories_burned_list[0], step_daily_trend_list[0], stress_list[0], weight_list[0])
    week = common.combine(calories_burned_list[1], step_daily_trend_list[1], stress_list[1], weight_list[1])
    month = common.combine(calories_burned_list[2], step_daily_trend_list[2], stress_list[2], weight_list[2])
    
    day['user_id'] = userId
    week['user_id'] = userId
    month['user_id'] = userId

    common.saveDB(db, 'daily_wearable', day)
    common.saveDB(db, 'weekly_wearable', week)
    common.saveDB(db, 'monthly_wearable', month)

    # db.close()
    return f'Hello, {userId}!'

if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)