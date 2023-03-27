import pandas as pd
from datetime import timedelta

# csv 파일 읽기
def readCsv(csvName):
    df = pd.read_csv(csvName, header=0, 
                     names=['start_time', 'sleep_id', 'custom', 'update_time', 'create_time', 'stage', 'time_offset', 'deviceuuid', 'pkg_name', 'end_time', 'datauuid', ''], 
                     skiprows=1)
    return df

# 테이블 맞춤 일 데이터 처리
def dayDF(df):
    df.rename(columns={'start_time':'daily_sleep_start', 'end_time':'daily_sleep_end', 'stage':'daily_sleep_stage'}, inplace=True)
    df = df[['daily_sleep_start', 'daily_sleep_end', 'daily_sleep_stage']]
    df['daily_sleep_start'] = pd.DatetimeIndex(df['daily_sleep_start']) + timedelta(hours=9)
    df['daily_sleep_end'] = pd.DatetimeIndex(df['daily_sleep_end']) + timedelta(hours=9)
    df['daily_sleep_total'] = df['daily_sleep_end'] - df['daily_sleep_start']
    df['daily_sleep_total'] = df['daily_sleep_total'].apply(timedelta.total_seconds) / 60
    return df

# 주, 월 데이터 처리를 위한 일 데이터 처리
def sleepDF(df):
    df['daily_sleep_start'] = df.daily_sleep_start.astype(str).str.split(' ').str[0] # 날짜 형식
    df = df.drop('daily_sleep_end', axis = 'columns')
    df2 = df.groupby(['daily_sleep_start', 'daily_sleep_stage'], as_index=False).sum() # 날짜, 수면 단계별 합계

    df3 = pd.DataFrame(columns=['date', 'daily_wearable_awake','daily_wearable_light', 'daily_wearable_deep', 'daily_wearable_rem', 'daily_wearable_sleep'])
    df3['date'] = df2['daily_sleep_start'].unique()

    for index, row in df2.iterrows():
        if row['daily_sleep_stage'] == 40001:
            r_index = df3.loc[df3['date'] == row['daily_sleep_start']].index[0]
            df3.loc[r_index,'daily_wearable_awake'] = row['daily_sleep_total']
        if row['daily_sleep_stage'] == 40002:
            r_index = df3.loc[df3['date'] == row['daily_sleep_start']].index[0]
            df3.loc[r_index,'daily_wearable_light'] = row['daily_sleep_total']
        if row['daily_sleep_stage'] == 40003:
            r_index = df3.loc[df3['date'] == row['daily_sleep_start']].index[0]
            df3.loc[r_index,'daily_wearable_deep'] = row['daily_sleep_total']
        if row['daily_sleep_stage'] == 40004:
            r_index = df3.loc[df3['date'] == row['daily_sleep_start']].index[0]
            df3.loc[r_index,'daily_wearable_rem'] = row['daily_sleep_total']

    df3 = df3.fillna(0)
    df3['daily_wearable_sleep'] = df3['daily_wearable_awake'] + df3['daily_wearable_light'] + df3['daily_wearable_deep'] + df3['daily_wearable_rem']
    return df3