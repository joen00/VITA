from flask import Flask, request
from sqlalchemy import text
from urllib import request as rq
import pandas as pd
import zipfile
import shutil
import glob
import os

import common
import weight
import step_daily_trend
import calories_burned
import stress
import heart_rate
import sleep_stage

app = Flask(__name__)

def makeDF(type, csv):
    df = type.readCsv(csv)
    day_df = type.dayDF(df)    
    return day_df

def makeDay(db, file, userId):
    with db.connect() as conn:
        sleep_date = conn.execute(text("SELECT max(daily_sleep_start) FROM daily_sleep WHERE user_id = '" + userId + "'")).fetchone()[0]
        day_date = conn.execute(text("SELECT max(date) FROM daily_wearable WHERE user_id = '" + userId + "'")).fetchone()[0]
        if sleep_date: conn.execute(text(f"DELETE FROM daily_sleep WHERE daily_sleep_start = '{sleep_date}' AND user_id = '{userId}'"))
        if day_date: conn.execute(text(f"DELETE FROM daily_wearable WHERE date = '{day_date}' AND user_id = '{userId}'"))
        conn.commit()
        conn.close()

    for csv in file:
        if 'weight' in csv:
            weight_list = makeDF(weight, csv)
        if 'step_daily_trend' in csv:
            step_daily_trend_list = makeDF(step_daily_trend, csv)
        if 'calories_burned' in csv:
            calories_burned_list = makeDF(calories_burned, csv)
        if 'stress' in csv and 'histogram' not in csv:
            stress_list = makeDF(stress, csv)
        if 'heart_rate' in csv and 'recovery' not in csv:
            heart_rate_list = makeDF(heart_rate, csv)
        if 'sleep_stage' in csv:
            sleep_stage_list = makeDF(sleep_stage, csv)
            sleep_stage_list['user_id'] = userId
            if sleep_date: common.saveDB(db, 'daily_sleep', sleep_stage_list[sleep_stage_list['daily_sleep_start'] >= sleep_date])
            else: common.saveDB(db, 'daily_sleep', sleep_stage_list)

    day = common.combine(calories_burned_list, step_daily_trend_list, stress_list, weight_list, heart_rate_list)
    day['user_id'] = userId
    if day_date: common.saveDB(db, 'daily_wearable', day[pd.to_datetime(day['date']).dt.date >= pd.to_datetime(day_date)])
    else: common.saveDB(db, 'daily_wearable', day)
    
    day_merge = pd.merge(day, sleep_stage.sleepDF(sleep_stage_list), on='date', how='outer')
    return day_merge

@app.route('/upload')
def upload():
    userId = request.args.get('userId')

    db = common.connectDB()
    with db.connect() as conn:
        url = conn.execute(text("SELECT user_upload_img FROM user_upload WHERE user_id = '" + userId + "'")).fetchone()[0]
    
    rq.urlretrieve(url, userId + ".zip")
    zipfile.ZipFile(userId + ".zip").extractall('./samsunghealth/')
    
    files = glob.glob('samsunghealth/*')
    file = glob.glob(files[0] + '/*')

    day = makeDay(db, file, userId)
    week = common.periodDF(day, '1W', userId)
    month = common.periodDF(day, '1M', userId)
    average = common.avgDF(month)

    with db.connect() as conn:
        week_date = conn.execute(text("SELECT max(date) FROM weekly_wearable WHERE user_id = '" + userId + "'")).fetchone()[0]
        if week_date: conn.execute(text(f"DELETE FROM weekly_wearable WHERE date = '{week_date}' AND user_id = '{userId}'"))
        month_date = conn.execute(text("SELECT max(date) FROM monthly_wearable WHERE user_id = '" + userId + "'")).fetchone()[0]
        if month_date: conn.execute(text(f"DELETE FROM monthly_wearable WHERE date = '{month_date}' AND user_id = '{userId}'"))
        isExist = conn.execute(text(f"SELECT user_id FROM user_average WHERE user_id = '{userId}'")).fetchone
        if isExist: conn.execute(text(f"DELETE FROM user_average WHERE user_id = '{userId}'"))
        conn.commit()
        conn.close()

    if week_date: common.saveDB(db, 'weekly_wearable', week[day['date'] >= pd.to_datetime(week_date)])
    else: common.saveDB(db, 'weekly_wearable', week)
    if month_date: common.saveDB(db, 'monthly_wearable', month[day['date'] >= pd.to_datetime(month_date)])
    else: common.saveDB(db, 'monthly_wearable', month)
    common.saveDB(db, 'user_average', average)

    if os.path.isfile(userId + ".zip"):
        os.remove(userId + ".zip")
        shutil.rmtree('./samsunghealth/')

    return f'Hello, {userId}!'    

@app.route('/')
def main():
    return "Hello, Vita!"

if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True, ssl_context =("cert.pem", "privkey.pem"))