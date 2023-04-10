from sqlalchemy import text
import pandas as pd

# 사용자 정보 가져오기
def getUser(db, userId):
    with db.connect() as conn:
        result = conn.execute(text("SELECT user_age, user_gender, user_height, user_weight FROM user WHERE user_id = '" + userId + "'")).fetchone()
    
    data = [[str(int(result[0] / 10) * 10), str(result[1]), result[2], result[3]]]
    df = pd.DataFrame(data, columns=['age', 'gender', 'height', 'weight'])
    return df

# api 평균 데이터 가져오기
def getAPI(db, user):
    with db.connect() as conn:
        query = text("SELECT * from api_average where age = " + user['age'][0] + " and sex = '" + user['gender'][0] + "'")
        result = conn.execute(query).fetchone()
    
    step = round(result[3] / result[4])
    energy = round(result[5] / result[6])
    rhr = round(result[7] / result[8])
    stress = round(result[9] / result[10])
    sleep = round(result[11] / result[12])

    data = [[step, energy, rhr, stress, sleep]]
    df = pd.DataFrame(data, columns=['step', 'energy', 'rhr', 'stress', 'sleep'])
    return df

# api 평균 데이터에 사용자 데이터 업데이트
def insertAPI(db, user, key, value):
    with db.connect() as conn:
        conn.execute(text("UPDATE api_average SET " + key + " = " + key + " + " + value.astype(str) + " where age = '" + user['age'][0] + "' and sex = '" + user['gender'][0] + "'"))
        conn.execute(text("UPDATE api_average SET " + key + "_cnt = " + key + "_cnt + 1 where age = '" + user['age'][0] + "' and sex = '" + user['gender'][0] + "'"))
        conn.commit()
        conn.close()