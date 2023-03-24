import pandas as pd
import datetime

# csv 파일 읽기
def readCsv(csvName):
    df = pd.read_csv(csvName, header=0, 
                     names=['binning_data', 'update_time', 'create_time', 'source_pkg_name', 'source_type', 'count', 'speed', 'distance', 'calorie', 'deviceuuid', 'pkg_name', 'datauuid', 'day_time', ''], 
                     skiprows=1)
    return df

# 일 데이터 처리
def dayDF(df):
    df = df[['count', 'day_time']]
    df['day_time']=df['day_time'].apply(lambda d: datetime.date.fromtimestamp((float)(d)/1000.0)) # 날짜 형식 변환
    df2 = df.groupby('day_time', as_index=False).max() # 날짜별 최대값
    df2 = df2[['count', 'day_time']]
    return df2

# DB Query 작성
def update(table, userId):
    query = "UPDATE " + table + " SET daily_wearable_step = %s WHERE user_id = '" + userId + "' AND date = %s"
    print(query)
    return query

def insert(table, userId):
    query = "INSERT INTO " + table + " (user_id, daily_wearable_step, date) values ('" + userId + "', %s, %s)"
    print(query)
    return query