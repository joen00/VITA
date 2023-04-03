import pandas as pd

# csv 파일 읽기
def read_csv(csv_name):
    df = pd.read_csv(csv_name, header=0, 
                     names=['start_time', 'custom', 'binning_data', 'tag_id', 'update_time', 'create_time', 'max', 'min', 'score', 'algorithm', 'time_offset', 'deviceuuid', 'comment', 'pkg_name', 'end_time', 'datauuid', ''], 
                     skiprows=1)
    return df

# Samsung 일별 데이터 처리
def samsung_day(df):
    df.rename(columns={'score':'daily_wearable_stress', 'start_time':'date'}, inplace=True)
    df['date'] = df.date.str.split(' ').str[0] # 날짜 형식
    df = df[['daily_wearable_stress', 'date']]
    df = df.groupby('date', as_index=False).mean().round(0) # 날짜별 평균
    return df

# Apple 일별 데이터 처리
def apple_day(data):
    df = pd.DataFrame(columns=['date', 'daily_wearable_stress'])
    return df