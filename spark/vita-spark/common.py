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
    db_connection_str = config.db_connection_str
    db_connection = create_engine(db_connection_str)
    return db_connection

# DB 저장
def saveDB(db_connection, table, df):
    df.to_sql(name=table, con=db_connection, if_exists='append', index=False)
