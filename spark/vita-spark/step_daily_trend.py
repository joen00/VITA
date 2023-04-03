import pandas as pd
import datetime

# csv 파일 읽기
def read_csv(csv_name):
    df = pd.read_csv(csv_name, header=0, 
                     names=['binning_data', 'update_time', 'create_time', 'source_pkg_name', 'source_type', 'count', 'speed', 'distance', 'calorie', 'deviceuuid', 'pkg_name', 'datauuid', 'day_time', ''], 
                     skiprows=1)
    return df

# Samsung 일별 데이터 처리
def samsung_day(df):
    df.rename(columns={'count':'daily_wearable_step', 'day_time':'date'}, inplace=True)
    df['date'] = df['date'].apply(lambda d: datetime.date.fromtimestamp((float)(d)/1000.0)).astype(str) # 날짜 형식 변환
    df = df[['daily_wearable_step', 'date']]
    df = df.groupby('date', as_index=False).max() # 날짜별 최대값
    return df

# Apple 일별 데이터 처리
def apple_day(data):
    df = pd.DataFrame(data, columns=['date', 'daily_wearable_step'])
    df['date'] = df.date.str.split(' ').str[0] # 날짜 형식
    df['daily_wearable_step'] = df['daily_wearable_step'].astype(float)
    df = df.groupby('date', as_index=False).sum() # 날짜별 합계
    return df