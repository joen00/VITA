import pandas as pd
import datetime

# csv 파일 읽기
def read_csv(csv_name):
    df = pd.read_csv(csv_name, header=0, 
                     names=['active_calories_goal', 'version', 'extra_data', 'com.samsung.shealth.calories_burned.tef_calorie', 'com.samsung.shealth.calories_burned.active_time', 'com.samsung.shealth.calories_burned.rest_calorie', 'com.samsung.shealth.calories_burned.update_time', 'com.samsung.shealth.calories_burned.create_time', 'com.samsung.shealth.calories_burned.active_calorie', 'com.samsung.shealth.calories_burned.deviceuuid', 'com.samsung.shealth.calories_burned.pkg_name', 'com.samsung.shealth.calories_burned.datauuid', 'com.samsung.shealth.calories_burned.day_time', ''], 
                     skiprows=1)
    return df

# Samsung 일별 데이터 처리
def samsung_day(df):
    df.rename(columns={'com.samsung.shealth.calories_burned.day_time':'date', 'com.samsung.shealth.calories_burned.active_calorie':'daily_wearable_energy'}, inplace=True)
    df['date'] = df['date'].apply(lambda d: datetime.date.fromtimestamp((float)(d)/1000.0)).astype(str) # 날짜 형식 변환
    df = df[['daily_wearable_energy', 'date']]
    df = df.groupby('date', as_index=False).sum().round(0) # 날짜별 합계
    return df

# Apple 일별 데이터 처리
def apple_day(data):
    df = pd.DataFrame(data, columns=['date', 'daily_wearable_energy'])
    df['date'] = df.date.str.split(' ').str[0] # 날짜 형식
    df['daily_wearable_energy'] = df['daily_wearable_energy'].astype(float)
    df = df.groupby('date', as_index=False).sum().round(0) # 날짜별 합계
    return df