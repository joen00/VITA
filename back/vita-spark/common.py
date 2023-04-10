import pandas as pd
from functools import reduce
from numpy import NaN

import pymysql
from sqlalchemy import create_engine
from pyspark import SparkContext

pymysql.install_as_MySQLdb()
import MySQLdb

import config
import api_average
import user_badge

# DB 연결
def connectDB():
    db_connection_str = config.db_connection_str
    db_connection = create_engine(db_connection_str)
    return db_connection

# DB 저장
def saveDB(db_connection, table, df):
    df.to_sql(name=table, con=db_connection, if_exists='append', index=False)

# combine df
def combine(calories_burned, step_daily_trend, stress, weight, heart_rate, sleep_stage):
    df_list = [calories_burned, step_daily_trend, stress, weight, heart_rate, sleep_stage]
    df_merge = reduce(lambda left, right: pd.merge(left, right, on='date', how='outer'), df_list)
    return df_merge

# 주, 월 데이터 처리
def periodDF(df, period, userId):
    df.date = pd.to_datetime(df.date)
    df = df.fillna(0).replace(0, NaN).resample(rule=period, on='date').mean().round(1)
    df = df.reset_index()

    if period == '1W':
        df.rename(columns = {'daily_wearable_stress':'weekly_wearable_stress', 'daily_wearable_rhr':'weekly_wearable_rhr', 'daily_wearable_energy':'weekly_wearable_energy', 'daily_wearable_step':'weekly_wearable_step',
                             'daily_wearable_weight':'weekly_wearable_weight', 'daily_wearable_muscle':'weekly_wearable_muscle', 'daily_wearable_fat':'weekly_wearable_fat',
                             'daily_wearable_sleep':'weekly_wearable_sleep', 'daily_wearable_awake':'weekly_wearable_awake', 'daily_wearable_light':'weekly_wearable_light', 'daily_wearable_deep':'weekly_wearable_deep', 'daily_wearable_rem':'weekly_wearable_rem'}, inplace=True)
    elif period == '1M':
        df.rename(columns = {'daily_wearable_stress':'monthly_wearable_stress', 'daily_wearable_rhr':'monthly_wearable_rhr', 'daily_wearable_energy':'monthly_wearable_energy', 'daily_wearable_step':'monthly_wearable_step',
                             'daily_wearable_weight':'monthly_wearable_weight', 'daily_wearable_muscle':'monthly_wearable_muscle', 'daily_wearable_fat':'monthly_wearable_fat',
                             'daily_wearable_sleep':'monthly_wearable_sleep', 'daily_wearable_awake':'monthly_wearable_awake', 'daily_wearable_light':'monthly_wearable_light', 'daily_wearable_deep':'monthly_wearable_deep', 'daily_wearable_rem':'monthly_wearable_rem'}, inplace=True)
    
    df['user_id'] = userId
    return df

# 사용자 평균 데이터 처리
def avgDF(df):
    df = df.drop(['monthly_wearable_weight', 'monthly_wearable_muscle', 'monthly_wearable_fat', 'date'], axis = 'columns')
    df.rename(columns = {'monthly_wearable_stress':'user_average_stress', 'monthly_wearable_rhr':'user_average_rhr', 'monthly_wearable_energy':'user_average_energy', 'monthly_wearable_step':'user_average_step',
                         'monthly_wearable_sleep':'user_average_sleep', 'monthly_wearable_awake':'user_average_awake', 'monthly_wearable_light':'user_average_light', 'monthly_wearable_deep':'user_average_deep', 'monthly_wearable_rem':'user_average_rem'}, inplace=True)
    df = df.groupby('user_id', as_index=False).mean().round(0) # 사용자 아이디별 평균
    return df.fillna(0)

# 일별 점수 계산
def dailyScore(df, userId, db):
    user_info = api_average.getUser(db, userId)
    api_info = api_average.getAPI(db, user_info)

    df['step'] = 100 - abs(df[df['daily_wearable_step'].notna() & df['daily_wearable_step'] != 0]['daily_wearable_step'] - api_info['step'][0]) / 100
    df['energy'] = 100 - abs(df[df['daily_wearable_energy'].notna() & df['daily_wearable_energy'] != 0]['daily_wearable_energy'] - api_info['energy'][0]) / 10
    df['rhr'] = 100 - abs(df[df['daily_wearable_rhr'].notna() & df['daily_wearable_rhr'] != 0]['daily_wearable_rhr'] - api_info['rhr'][0]) / 10
    df['stress'] = 100 - df[df['daily_wearable_stress'].notna() & df['daily_wearable_stress'] != 0]['daily_wearable_stress'] / api_info['stress'][0] * 10
    df['sleep'] = 100 - abs(df[df['daily_wearable_sleep'].notna() & df['daily_wearable_sleep'] != 0]['daily_wearable_sleep'] - api_info['sleep'][0]) / 10
    df = df.fillna(0)

    df['daily_wearable_score'] = ((df['step'] + df['energy'] + df['rhr'] + df['stress'] + df['sleep']) / 5).round(0)
    df.loc[df['daily_wearable_score'] < 0, 'daily_wearable_score'] = 0
    return df['daily_wearable_score']

# 총 점수 계산
def totalScore(df, userId, db):
    user_info = api_average.getUser(db, userId)
    api_info = api_average.getAPI(db, user_info)

    step, energy, rhr, stress, sleep = 0, 0, 0, 0, 0

    if df['user_average_step'][0] != 0:
        step = 100 - abs(df['user_average_step'][0] - api_info['step'][0]) / 100
        api_average.insertAPI(db, user_info, 'api_average_step', df['user_average_step'][0])
    if step < 0: step = 0

    if df['user_average_energy'][0] != 0:
        energy = 100 - abs(df['user_average_energy'][0] - api_info['energy'][0]) / 10
        api_average.insertAPI(db, user_info, 'api_average_energy', df['user_average_energy'][0])
    if energy < 0: energy = 0

    if df['user_average_rhr'][0] != 0:
        rhr = 100 - abs(df['user_average_rhr'][0] - api_info['rhr'][0]) / 10
        api_average.insertAPI(db, user_info, 'api_average_rhr', df['user_average_rhr'][0])
    if rhr < 0: rhr = 0

    if df['user_average_stress'][0] != 0:
        stress = 100 - abs(df['user_average_stress'][0] - api_info['stress'][0]) / 10
        api_average.insertAPI(db, user_info, 'api_average_stress', df['user_average_stress'][0])
    if stress < 0: stress = 0

    if df['user_average_sleep'][0] != 0:
        sleep = 100 - abs(df['user_average_sleep'][0] - api_info['sleep'][0]) / 10
        api_average.insertAPI(db, user_info, 'api_average_sleep', df['user_average_sleep'][0])
    if sleep < 0: sleep = 0

    created_date = pd.datetime.now().date()
    maxScore = user_badge.maxScore(db, userId)
    data = [[userId, step, energy, rhr, stress, sleep, created_date]]
    result = pd.DataFrame(data, columns=['user_id', 'total_score_step', 'total_score_energy', 'total_score_rhr', 'total_score_stress', 'total_score_sleep', 'created_date'])
    saveDB(db, 'total_score', result)

    # 95점 이상 뱃지
    if (step + energy + rhr + stress + sleep) >= 475:
        id = user_badge.findId(db, 'score')
        user_badge.giveBagde(db, userId, id)
    
    # 10점 상승 뱃지
    if (maxScore != -1) & ((step + energy + rhr + stress + sleep) >= maxScore + 50):
        id = user_badge.findId(db, 'upgrade')
        user_badge.giveBagde(db, userId, id)

def setScore(data, type, api_info):
    if data != 0: result = 100 - abs(data - api_info[type][0]) / 100
    else: result = 0
    return result

def calcScore(data, type, api_info):
    sc = SparkContext.getOrCreate()
    lines = sc.parallelize(data)
    map = lines.map(lambda x: setScore(x, type, api_info))
    result = map.collect()
    return result