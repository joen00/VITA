import pandas as pd

# csv 파일 읽기
def readCsv(csvName):
    df = pd.read_csv(csvName, header=0, 
                     names=['body_fat_mass', 'start_time', 'custom', 'height', 'weight', 'muscle_mass', 'update_time', 'create_time', 'skeletal_muscle', 'fat_free_mass', 'basal_metabolic_rate', 'time_offset', 'deviceuuid', 'skeletal_muscle_mass', 'comment', 'fat_free', 'pkg_name', 'body_fat', 'datauuid', 'vfa_level', 'total_body_water', ''], 
                     skiprows=1)
    return df

# 일 데이터 처리
def dayDF(df):
    df = df[['weight', 'skeletal_muscle_mass', 'body_fat_mass', 'start_time']]
    df['start_time'] = df.start_time.str.split(' ').str[0] # 날짜 형식
    df['skeletal_muscle_mass'] = df['skeletal_muscle_mass'].round(1) # 골격근량
    df['body_fat_mass'] = df['body_fat_mass'].round(1) # 체지방량
    df2 = df.groupby('start_time', as_index=False).mean() # 날짜별 최대값
    df2 = df2[['weight', 'skeletal_muscle_mass', 'body_fat_mass', 'start_time']]
    df2.rename(columns = {'weight':'daily_wearable_weight', 'skeletal_muscle_mass':'daily_wearable_muscle', 'body_fat_mass':'daily_wearable_fat', 'start_time':'date'}, inplace=True)
    df2 = df2.fillna(0)
    return df2

# 주, 월 데이터 처리
def periodDF(df, period):
    df.date = pd.to_datetime(df.date)
    df = df.resample(rule=period, on='date').mean().round(1)
    df = df.reset_index()
    df = df.fillna(0)
    
    if period == '1W':
        df.rename(columns = {'daily_wearable_weight':'weekly_wearable_weight', 'daily_wearable_muscle':'weekly_wearable_muscle', 'daily_wearable_fat':'weekly_wearable_fat'}, inplace=True)
    elif period == '1M':
        df.rename(columns = {'daily_wearable_weight':'monthly_wearable_weight', 'daily_wearable_muscle':'monthly_wearable_muscle', 'daily_wearable_fat':'monthly_wearable_fat'}, inplace=True)
    return df

# DB Query 작성
def update(table, userId):
    query = "UPDATE " + table + " SET daily_wearable_weight = %s, daily_wearable_muscle = %s, daily_wearable_fat = %s WHERE user_id = '" + userId + "' AND date = %s"
    print(query)
    return query

def insert(table, userId):
    query = "INSERT INTO " + table + " (user_id, daily_wearable_weight, daily_wearable_muscle, daily_wearable_fat, date) values ('" + userId + "', %s, %s, %s, %s)"
    print(query)
    return query