from flask import Flask

import os
import glob

import common
import weight
import step_daily_trend
import calories_burned
import stress

app = Flask(__name__)

def day(db_connection, table, type, csv, userId):
    df = type.readCsv(csv)
    df2 = type.dayDF(df)

    common.save(db_connection, table, type, userId, df2)

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

    for csv in file:
        if 'weight' in csv:
            day(db, 'daily_wearable', weight, csv, userId)
        if 'step_daily_trend' in csv:
            day(db, 'daily_wearable', step_daily_trend, csv, userId)
        if 'calories_burned' in csv:
            day(db, 'daily_wearable', calories_burned, csv, userId)
        if 'stress' in csv and 'histogram' not in csv:
            day(db, 'daily_wearable', stress, csv, userId)

    db.close()
    return f'Hello, {userId}!'

if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)