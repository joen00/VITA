import pandas as pd
import datetime

# csv 파일 읽기
def readCsv(csvName):
    df = pd.read_csv(csvName, header=0, 
                     names=['source', 'tag_id', 'com.samsung.health.heart_rate.heart_beat_count', 'com.samsung.health.heart_rate.start_time', 'com.samsung.health.heart_rate.custom', 'com.samsung.health.heart_rate.binning_data', 'com.samsung.health.heart_rate.update_time', 'com.samsung.health.heart_rate.create_time', 'com.samsung.health.heart_rate.max', 'com.samsung.health.heart_rate.min', 'com.samsung.health.heart_rate.time_offset', 'com.samsung.health.heart_rate.deviceuuid', 'com.samsung.health.heart_rate.comment', 'com.samsung.health.heart_rate.pkg_name', 'com.samsung.health.heart_rate.end_time', 'com.samsung.health.heart_rate.datauuid', 'com.samsung.health.heart_rate.heart_rate', ''], 
                     skiprows=1)
    return df

# 일 데이터 처리
def dayDF(df):
    df.rename(columns={'com.samsung.health.heart_rate.start_time':'start_time', 'com.samsung.health.heart_rate.heart_rate':'heart_rate'}, inplace=True)
    df = df[['heart_rate', 'start_time']]
    df['start_time'] = df.start_time.str.split(' ').str[0] # 날짜 형식
    df = df.groupby('start_time', as_index=False).mean().round(0) # 날짜별 합계
    df = df[['heart_rate', 'start_time']]
    df.rename(columns = {'heart_rate':'daily_wearable_rhr', 'start_time':'date'}, inplace=True)
    return df

# 주, 월 데이터 처리
def periodDF(df, period):
    df.date = pd.to_datetime(df.date)
    df = df.resample(rule=period, on='date').mean().round(0)
    df = df.reset_index()
    df = df.fillna(0)
    
    if period == '1W':
        df.rename(columns = {'daily_wearable_rhr':'weekly_wearable_rhr'}, inplace=True)
    elif period == '1M':
        df.rename(columns = {'daily_wearable_rhr':'monthly_wearable_rhr'}, inplace=True)
    return df