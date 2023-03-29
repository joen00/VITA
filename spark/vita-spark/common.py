import pandas as pd
from functools import reduce

import pymysql
from sqlalchemy import create_engine

pymysql.install_as_MySQLdb()
import MySQLdb

import config

# DB 연결
def connectDB():
    db_connection_str = config.db_connection_str
    db_connection = create_engine(db_connection_str)
    return db_connection

# DB 저장
def saveDB(db_connection, table, df):
    df.to_sql(name=table, con=db_connection, if_exists='append', index=False)

# combine df
def combine(calories_burned, step_daily_trend, stress, weight, heart_rate):
    df_list = [calories_burned, step_daily_trend, stress, weight, heart_rate]
    df_merge = reduce(lambda left, right: pd.merge(left, right, on='date', how='outer'), df_list)
    return df_merge

# 주, 월 데이터 처리
def periodDF(df, period, userId):
    df.date = pd.to_datetime(df.date)
    df = df.resample(rule=period, on='date').mean().round(1)
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
    df = df.groupby('user_id', as_index=False).mean().round(1) # 사용자 아이디별 평균
    return df