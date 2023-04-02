import pandas as pd
from datetime import timedelta

# csv 파일 읽기
def read_csv(csv_name):
    df = pd.read_csv(csv_name, header=0, 
                     names=['start_time', 'sleep_id', 'custom', 'update_time', 'create_time', 'stage', 'time_offset', 'deviceuuid', 'pkg_name', 'end_time', 'datauuid', ''], 
                     skiprows=1)
    return df

# Samsung 일-시간 데이터 처리
def samsung_day(df):
    df.rename(columns={'start_time':'daily_sleep_start', 'end_time':'daily_sleep_end', 'stage':'daily_sleep_stage'}, inplace=True)
    df = df[['daily_sleep_start', 'daily_sleep_end', 'daily_sleep_stage']]
    df['daily_sleep_start'] = pd.DatetimeIndex(df['daily_sleep_start']) + timedelta(hours=9)
    df['daily_sleep_end'] = pd.DatetimeIndex(df['daily_sleep_end']) + timedelta(hours=9)
    df['daily_sleep_total'] = df['daily_sleep_end'] - df['daily_sleep_start']
    df['daily_sleep_total'] = df['daily_sleep_total'].apply(timedelta.total_seconds) / 60
    df = df.replace({'daily_sleep_stage': {40001: 'AWAKE', 40002: 'LIGHT', 40003: 'DEEP', 40004: 'REM'}})
    return df

# Apple 일-시간 데이터 처리
def apple_day(data):
    df = pd.DataFrame(data, columns=['daily_sleep_start', 'daily_sleep_end', 'daily_sleep_stage'])
    df2 = df[(df.daily_sleep_stage != 'HKCategoryValueSleepAnalysisInBed') & (df.daily_sleep_stage != 'HKCategoryValueSleepAnalysisAsleepUnspecified')].copy()
    df2['daily_sleep_total'] = pd.to_datetime(df['daily_sleep_end']) - pd.to_datetime(df['daily_sleep_start'])
    df2['daily_sleep_total'] = df2['daily_sleep_total'].apply(timedelta.total_seconds) / 60
    df2 = df.replace({'daily_sleep_stage': {'HKCategoryValueSleepAnalysisAwake': 'AWAKE', 'HKCategoryValueSleepAnalysisAsleepCore': 'LIGHT', 'HKCategoryValueSleepAnalysisAsleepDeep': 'DEEP', 'HKCategoryValueSleepAnalysisAsleepREM': 'REM'}})
    return df2

# 주, 월 데이터 처리를 위한 일 데이터 처리
def sleep_day(df):
    df['daily_sleep_start'] = df.daily_sleep_start.astype(str).str.split(' ').str[0] # 날짜 형식
    df = df.drop('daily_sleep_end', axis = 'columns')
    df2 = df.groupby(['daily_sleep_start', 'daily_sleep_stage'], as_index=False).sum() # 날짜, 수면 단계별 합계

    df3 = pd.DataFrame(columns=['date', 'daily_wearable_awake','daily_wearable_light', 'daily_wearable_deep', 'daily_wearable_rem', 'daily_wearable_sleep'])
    df3['date'] = df2['daily_sleep_start'].unique()

    for row in df2.itertuples():
        if row.daily_sleep_stage == 'AWAKE':
            r_index = df3.loc[df3['date'] == row.daily_sleep_start].index[0]
            df3.loc[r_index, 'daily_wearable_awake'] = row.daily_sleep_total
        if row.daily_sleep_stage == 'LIGHT':
            r_index = df3.loc[df3['date'] == row.daily_sleep_start].index[0]
            df3.loc[r_index, 'daily_wearable_light'] = row.daily_sleep_total
        if row.daily_sleep_stage == 'DEEP':
            r_index = df3.loc[df3['date'] == row.daily_sleep_start].index[0]
            df3.loc[r_index, 'daily_wearable_deep'] = row.daily_sleep_total
        if row.daily_sleep_stage == 'REM':
            r_index = df3.loc[df3['date'] == row.daily_sleep_start].index[0]
            df3.loc[r_index, 'daily_wearable_rem'] = row.daily_sleep_total

    df3 = df3.fillna(0)
    df3['daily_wearable_sleep'] = df3['daily_wearable_awake'] + df3['daily_wearable_light'] + df3['daily_wearable_deep'] + df3['daily_wearable_rem']
    return df3