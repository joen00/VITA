import pandas as pd

# csv 파일 읽기
def readCsv(csvName):
    df = pd.read_csv(csvName, header=0, 
                     names=['body_fat_mass', 'start_time', 'custom', 'height', 'weight', 'muscle_mass', 'update_time', 'create_time', 'skeletal_muscle', 'fat_free_mass', 'basal_metabolic_rate', 'time_offset', 'deviceuuid', 'skeletal_muscle_mass', 'comment', 'fat_free', 'pkg_name', 'body_fat', 'datauuid', 'vfa_level', 'total_body_water', ''], 
                     skiprows=1)
    return df

# 일 데이터 처리
def dayDF(df):
    df = df[['start_time', 'weight', 'skeletal_muscle_mass', 'body_fat_mass']]
    df['start_time'] = df.start_time.str.split(' ').str[0] # 날짜 형식
    df['skeletal_muscle_mass'] = df['skeletal_muscle_mass'].round(1) # 골격근량
    df['body_fat_mass'] = df['body_fat_mass'].round(1) # 체지방량
    df.sort_values(by='start_time' ,ascending=True) # 날짜 오름차순 정렬
    return df