import pandas as pd
import datetime

# csv 파일 읽기
def readCsv(csvName):
    df = pd.read_csv(csvName, header=0, 
                     names=['active_calories_goal', 'version', 'extra_data', 'com.samsung.shealth.calories_burned.tef_calorie', 'com.samsung.shealth.calories_burned.active_time', 'com.samsung.shealth.calories_burned.rest_calorie', 'com.samsung.shealth.calories_burned.update_time', 'com.samsung.shealth.calories_burned.create_time', 'com.samsung.shealth.calories_burned.active_calorie', 'com.samsung.shealth.calories_burned.deviceuuid', 'com.samsung.shealth.calories_burned.pkg_name', 'com.samsung.shealth.calories_burned.datauuid', 'com.samsung.shealth.calories_burned.day_time', ''], 
                     skiprows=1)
    return df

# 일 데이터 처리
def dayDF(df):
    df.rename(columns={'com.samsung.shealth.calories_burned.day_time':'day_time', 'com.samsung.shealth.calories_burned.active_calorie':'active_calorie'}, inplace=True)
    df = df[['active_calorie', 'day_time']]
    df['day_time']=df['day_time'].apply(lambda d: datetime.date.fromtimestamp((float)(d)/1000.0)) # 날짜 형식 변환
    df.sort_values(by='day_time' ,ascending=True) # 날짜 오름차순 정렬
    df = df[['active_calorie', 'day_time']]
    return df

# DB Query 작성
def update(table, userId):
    query = "UPDATE " + table + " SET daily_wearable_energy = %s WHERE user_id = '" + userId + "' AND date = %s"
    print(query)
    return query

def insert(table, userId):
    query = "INSERT INTO " + table + " (user_id, daily_wearable_energy, date) values ('" + userId + "', %s, %s)"
    print(query)
    return query