import pandas as pd

# csv 파일 읽기
def read_csv(csv_name):
    df = pd.read_csv(csv_name, header=0, 
                     names=['body_fat_mass', 'start_time', 'custom', 'height', 'weight', 'muscle_mass', 'update_time', 'create_time', 'skeletal_muscle', 'fat_free_mass', 'basal_metabolic_rate', 'time_offset', 'deviceuuid', 'skeletal_muscle_mass', 'comment', 'fat_free', 'pkg_name', 'body_fat', 'datauuid', 'vfa_level', 'total_body_water', ''], 
                     skiprows=1)
    return df

# Samsung 일별 데이터 처리
def samsung_day(df):
    df.rename(columns={'weight':'daily_wearable_weight', 'skeletal_muscle_mass':'daily_wearable_muscle', 'body_fat_mass':'daily_wearable_fat', 'start_time':'date'}, inplace=True)
    df['date'] = df.date.str.split(' ').str[0] # 날짜 형식
    df['daily_wearable_muscle'] = df['daily_wearable_muscle'].round(1) # 골격근량
    df['daily_wearable_fat'] = df['daily_wearable_fat'].round(1) # 체지방량
    df = df[['daily_wearable_weight', 'daily_wearable_muscle', 'daily_wearable_fat', 'date']]
    df = df.groupby('date', as_index=False).mean().round(1) # 날짜별 평균
    return df

# Apple 일별 데이터 처리
def apple_day(data):
    df = pd.DataFrame(data, columns=['date', 'daily_wearable_weight', 'daily_wearable_muscle', 'daily_wearable_fat'])
    df['daily_wearable_weight'] = df['daily_wearable_weight'].astype(float)
    df['date'] = df.date.str.split(' ').str[0] # 날짜 형식
    df = df.groupby('date', as_index=False).mean().round(1) # 날짜별 평균
    return df