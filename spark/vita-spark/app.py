from flask import Flask, request
from sqlalchemy import text
from urllib import request as rq
import xml.etree.ElementTree as ET
from flask_cors import CORS
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
CORS(app)

def samsung(type, csv):
    df = type.read_csv(csv)
    day_df = type.samsung_day(df)
    return day_df

def apple(type, data):
    day_df = type.apple_day(data)
    return day_df

def samsung_df(userId):
    zipfile.ZipFile(userId + ".zip").extractall('./samsunghealth/')
    files = glob.glob('samsunghealth/*')
    if files.count('samsunghealth\\jsons') == 0 and files.count('samsunghealth/jsons') == 0:
        files = glob.glob(files[0] + '/*')

    for csv in files:
        if 'weight' in csv:
            weight_df = samsung(weight, csv)
        if 'step_daily_trend' in csv:
            step_daily_trend_df = samsung(step_daily_trend, csv)
        if 'calories_burned' in csv:
            calories_burned_df = samsung(calories_burned, csv)
        if 'stress' in csv and 'histogram' not in csv:
            stress_df = samsung(stress, csv)
        if 'heart_rate' in csv and 'recovery' not in csv:
            heart_rate_df = samsung(heart_rate, csv)
        if 'sleep_stage' in csv:
            sleep_stage_df = samsung(sleep_stage, csv)
            sleep_stage_df['user_id'] = userId

    result = [weight_df, step_daily_trend_df, calories_burned_df, stress_df, heart_rate_df, sleep_stage_df]
    return result

def apple_df(userId):
    with zipfile.ZipFile(userId + ".zip", 'r') as zf:
        zipinfo = zf.infolist()
        for info in zipinfo:
            info.filename = info.filename.encode('cp437').decode('utf-8')
            zf.extract(info, './applehealth/')

    tree = ET.parse('./applehealth/apple_health_export/내보내기.xml')
    root = tree.getroot()

    weight_list, step_daily_trend_list, heart_rate_list, calories_burned_list, stress_list, sleep_stage_list = [], [], [], [], [], []

    for child in root:
        if child.get('type') == 'HKQuantityTypeIdentifierBodyMass':
            weight_data = [child.get('creationDate'), child.get('value'), 0, 0]
            weight_list.append(weight_data)
        if child.get('type') == 'HKQuantityTypeIdentifierStepCount':
            if ('Apple Watch') in child.get('sourceName'):
                step_daily_trend_data = [child.get('endDate'), child.get('value')]
                step_daily_trend_list.append(step_daily_trend_data)
        if child.get('type') == 'HKQuantityTypeIdentifierHeartRate':
            heart_rate_data = [child.get('startDate'), child.get('value')]
            heart_rate_list.append(heart_rate_data)
        if child.get('type') == 'HKQuantityTypeIdentifierActiveEnergyBurned':
            calories_burned_data = [child.get('creationDate'), child.get('value')]
            calories_burned_list.append(calories_burned_data)
        if child.get('type') == 'HKCategoryTypeIdentifierSleepAnalysis':
            sleep_stage_data = [child.get('startDate'), child.get('endDate'), child.get('value')]
            sleep_stage_list.append(sleep_stage_data)

    weight_df = apple(weight, weight_list)
    step_daily_trend_df = apple(step_daily_trend, step_daily_trend_list)
    heart_rate_df = apple(heart_rate, heart_rate_list)
    calories_burned_df = apple(calories_burned, calories_burned_list)
    stress_df = apple(stress, stress_list)
    sleep_stage_df = apple(sleep_stage, sleep_stage_list)
    sleep_stage_df['user_id'] = userId

    result = [weight_df, step_daily_trend_df, calories_burned_df, stress_df, heart_rate_df, sleep_stage_df]
    return result

def makeDay(db, userId, device):
    with db.connect() as conn:
        sleep_date = conn.execute(text("SELECT max(daily_sleep_start) FROM daily_sleep WHERE user_id = '" + userId + "'")).fetchone()[0]
        day_date = conn.execute(text("SELECT max(date) FROM daily_wearable WHERE user_id = '" + userId + "'")).fetchone()[0]
        if sleep_date: conn.execute(text(f"DELETE FROM daily_sleep WHERE daily_sleep_start = '{sleep_date}' AND user_id = '{userId}'"))
        if day_date: conn.execute(text(f"DELETE FROM daily_wearable WHERE date = '{day_date}' AND user_id = '{userId}'"))
        conn.commit()
        conn.close()

    if 'samsung' in device:
        result = samsung_df(userId)
    if 'apple' in device:
        result = apple_df(userId)

    weight_df = result[0]
    step_daily_trend_df = result[1]
    calories_burned_df = result[2]
    stress_df = result[3]
    heart_rate_df = result[4]
    sleep_stage_df = result[5]

    if sleep_date: common.saveDB(db, 'daily_sleep', sleep_stage_df[sleep_stage_df['daily_sleep_start'] >= sleep_date].fillna(0))
    else: common.saveDB(db, 'daily_sleep', sleep_stage_df.fillna(0))

    day = common.combine(calories_burned_df, step_daily_trend_df, stress_df, weight_df, heart_rate_df, sleep_stage.sleep_day(sleep_stage_df))
    day['daily_wearable_score'] = common.dailyScore(day, userId, db)
    day = day.drop(['step', 'energy', 'rhr', 'stress', 'sleep'], axis = 'columns')
    day['user_id'] = userId
    
    if day_date: common.saveDB(db, 'daily_wearable', day[pd.to_datetime(day['date']).dt.date >= pd.to_datetime(day_date)].fillna(0))
    else: common.saveDB(db, 'daily_wearable', day.fillna(0))
    
    return day.drop('daily_wearable_score', axis = 'columns')

@app.route('/upload')
def upload():
    userId = request.args.get('userId')
    device = request.args.get('device')

    db = common.connectDB()
    with db.connect() as conn:
        url = conn.execute(text("SELECT user_upload_img FROM user_upload WHERE user_id = '" + userId + "'")).fetchone()[0]

    rq.urlretrieve(url, userId + ".zip")

    day = makeDay(db, userId, device)
    week = common.periodDF(day, '1W', userId)
    month = common.periodDF(day, '1M', userId)
    average = common.avgDF(month)
    common.totalScore(average, userId, db)

    with db.connect() as conn:
        week_date = conn.execute(text("SELECT max(date) FROM weekly_wearable WHERE user_id = '" + userId + "'")).fetchone()[0]
        if week_date: conn.execute(text(f"DELETE FROM weekly_wearable WHERE date = '{week_date}' AND user_id = '{userId}'"))
        month_date = conn.execute(text("SELECT max(date) FROM monthly_wearable WHERE user_id = '" + userId + "'")).fetchone()[0]
        if month_date: conn.execute(text(f"DELETE FROM monthly_wearable WHERE date = '{month_date}' AND user_id = '{userId}'"))
        isExist = conn.execute(text(f"SELECT user_id FROM user_average WHERE user_id = '{userId}'")).fetchone
        if isExist: conn.execute(text(f"DELETE FROM user_average WHERE user_id = '{userId}'"))
        conn.commit()
        conn.close()

    if week_date: common.saveDB(db, 'weekly_wearable', week[week['date'] >= pd.to_datetime(week_date)].fillna(0))
    else: common.saveDB(db, 'weekly_wearable', week.fillna(0))
    if month_date: common.saveDB(db, 'monthly_wearable', month[month['date'] >= pd.to_datetime(month_date)].fillna(0))
    else: common.saveDB(db, 'monthly_wearable', month.fillna(0))
    common.saveDB(db, 'user_average', average.fillna(0))

    paths = glob.glob('./*', recursive=True)
    for path in paths:
        if 'zip' in path:
            os.remove(path)
        if 'samsunghealth' in path:
            shutil.rmtree(path)
        if 'applehealth' in path:
            shutil.rmtree(path)

    return f'Hello, {userId}!'    

@app.route('/')
def main():
    return "Hello, Vita!"

if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True, ssl_context =("cert.pem", "privkey.pem"))