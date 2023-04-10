import pandas as pd

# csv 파일 읽기
def read_csv(csv_name):
    df = pd.read_csv(csv_name, header=0, 
                     names=['source', 'tag_id', 'com.samsung.health.heart_rate.heart_beat_count', 'com.samsung.health.heart_rate.start_time', 'com.samsung.health.heart_rate.custom', 'com.samsung.health.heart_rate.binning_data', 'com.samsung.health.heart_rate.update_time', 'com.samsung.health.heart_rate.create_time', 'com.samsung.health.heart_rate.max', 'com.samsung.health.heart_rate.min', 'com.samsung.health.heart_rate.time_offset', 'com.samsung.health.heart_rate.deviceuuid', 'com.samsung.health.heart_rate.comment', 'com.samsung.health.heart_rate.pkg_name', 'com.samsung.health.heart_rate.end_time', 'com.samsung.health.heart_rate.datauuid', 'com.samsung.health.heart_rate.heart_rate', ''], 
                     skiprows=1)
    return df

# Samsung 일별 데이터 처리
def samsung_day(df):
    df.rename(columns={'com.samsung.health.heart_rate.start_time':'date', 'com.samsung.health.heart_rate.heart_rate':'daily_wearable_rhr'}, inplace=True)
    df['date'] = df.date.str.split(' ').str[0] # 날짜 형식
    df = df[['daily_wearable_rhr', 'date']]
    df = df.groupby('date', as_index=False).mean().round(0) # 날짜별 평균
    return df

# Apple 일별 데이터 처리
def apple_day(data):
    df = pd.DataFrame(data, columns=['date', 'daily_wearable_rhr'])
    df['date'] = df.date.str.split(' ').str[0] # 날짜 형식
    df['daily_wearable_rhr'] = df['daily_wearable_rhr'].astype(float)
    df = df.groupby('date', as_index=False).mean().round(0) # 날짜별 평균
    return df