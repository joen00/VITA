import pandas as pd

# csv 파일 읽기
def readCsv(csvName):
    df = pd.read_csv(csvName, header=0, 
                     names=['start_time', 'custom', 'binning_data', 'tag_id', 'update_time', 'create_time', 'max', 'min', 'score', 'algorithm', 'time_offset', 'deviceuuid', 'comment', 'pkg_name', 'end_time', 'datauuid', ''], 
                     skiprows=1)
    return df

# 일 데이터 처리
def dayDF(df):
    df = df[['score', 'start_time']]
    df['start_time'] = df.start_time.str.split(' ').str[0] # 날짜 형식
    df2 = df.groupby('start_time', as_index=False).mean().round(0) # 날짜별 합계
    df2 = df2[['score', 'start_time']]
    return df2

# 주, 월 데이터 처리
def periodDF(df, period):
    df = df.resample(rule=period, on='start_time').mean().round(0)
    df = df.reset_index()
    df = df[['score', 'start_time']]
    df = df.fillna(0)
    return df

# DB Query 작성
def update(table, userId):
    query = "UPDATE " + table + " SET daily_wearable_stress = %s WHERE user_id = '" + userId + "' AND date = %s"
    print(query)
    return query

def insert(table, userId):
    query = "INSERT INTO " + table + " (user_id, daily_wearable_stress, date) values ('" + userId + "', %s, %s)"
    print(query)
    return query