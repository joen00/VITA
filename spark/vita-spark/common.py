import boto3
import zipfile
import io

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
    db_connection = pymysql.connect(host=config.host, user=config.user,
                                    password=config.password, db=config.db)
    return db_connection

# DB 저장
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

def save(db_connection, table, type, userId, df):
    list = select(db_connection, table, userId, df)

    insertQuery = type.insert(table, userId)
    execute(db_connection, insertQuery, list[0])
    updateQuery = type.update(table, userId)
    execute(db_connection, updateQuery, list[1])