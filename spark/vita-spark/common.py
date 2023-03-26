import boto3
import zipfile
import io
import pandas as pd
from functools import reduce

import pymysql
from sqlalchemy import create_engine

pymysql.install_as_MySQLdb()
import MySQLdb

import config

# s3 저장소 연결
def connectS3():
    s3 = boto3.client(service_name="s3",
                      aws_access_key_id=config.aws_access_key_id,
                      aws_secret_access_key=config.aws_secret_access_key)
    return s3

# s3 파일 가져온 후 zip 입축 해제
def decompress(s3, zipName):
    obj = s3.get_object(Bucket="vita-project-bucket", Key=zipName+".zip")
    zipfile.ZipFile(io.BytesIO(obj["Body"].read())).extractall('./samsunghealth/')

# DB 연결
def connectDB():
    db_connection_str = config.db_connection_str
    db_connection = create_engine(db_connection_str)
    # db_connection = pymysql.connect(host=config.host, user=config.user,
    #                                 password=config.password, db=config.db)
    return db_connection

# DB 저장-1
def saveDB(db_connection, table, df):
    df.to_sql(name=table, con=db_connection, if_exists='append', index=False)

# DB 저장-2
def execute(db_connection, query, df):
    cursor = db_connection.cursor()
    cursor.executemany(query, df)
    db_connection.commit()

# select
def select(db_connection, table, userId, df):
    query = "SELECT count(*) FROM " + table + " WHERE user_id = '" + userId + "' AND date = %s"
    
    resultList = []
    insertList = []
    updateList = []
    list = df.values.tolist()

    for row in list:
        cursor = db_connection.cursor()
        cursor.execute(query, row[1])
        result = cursor.fetchone()

        if result[0] == 0:
            insertList.append(row)
        else:
            updateList.append(row)
    
    resultList.append(insertList)
    resultList.append(updateList)

    return resultList

# insert, update
def save(db_connection, table, type, userId, df):
    list = select(db_connection, table, userId, df)

    insertQuery = type.insert(table, userId)
    execute(db_connection, insertQuery, list[0])
    updateQuery = type.update(table, userId)
    execute(db_connection, updateQuery, list[1])

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
    df = df.fillna(0)
    
    if period == '1W':
        df.rename(columns = {'daily_wearable_stress':'weekly_wearable_stress', 'daily_wearable_rhr':'weekly_wearable_rhr', 'daily_wearable_energy':'weekly_wearable_energy', 'daily_wearable_step':'weekly_wearable_step',
                             'daily_wearable_weight':'weekly_wearable_weight', 'daily_wearable_muscle':'weekly_wearable_muscle', 'daily_wearable_fat':'weekly_wearable_fat'}, inplace=True)
    elif period == '1M':
        df.rename(columns = {'daily_wearable_stress':'monthly_wearable_stress', 'daily_wearable_rhr':'monthly_wearable_rhr', 'daily_wearable_energy':'monthly_wearable_energy', 'daily_wearable_step':'monthly_wearable_step',
                             'daily_wearable_weight':'monthly_wearable_weight', 'daily_wearable_muscle':'monthly_wearable_muscle', 'daily_wearable_fat':'monthly_wearable_fat'}, inplace=True)
    
    df['user_id'] = userId
    return df