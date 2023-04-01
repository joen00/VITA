from sqlalchemy import text

# bagde id 찾기
def findId(db, name):
    with db.connect() as conn:
        result = conn.execute(text("SELECT badge_id FROM badge WHERE badge_name = '" + name + "'")).fetchone()
    return result[0]

# user badge 주기
def giveBagde(db, userId, id):
    with db.connect() as conn:
        conn.execute(text("UPDATE user_badge SET user_badge_get = 1 where user_id = '" + userId + "' and badge_id = " + str(id)))
        conn.commit()
        conn.close()

# user max score get
def maxScore(db, userId):
    with db.connect() as conn:
        result = conn.execute(text("SELECT max(total_score_energy + total_score_rhr + total_score_stress + total_score_step + total_score_sleep) FROM total_score WHERE user_id = '" + userId + "'")).fetchone()

    if result[0] != None:
        return result[0]
    else: return 0