from flask import Flask

import common
import weight
import step_daily_trend
import calories_burned
import stress

app = Flask(__name__)

@app.route('/')
def home():
    return 'Hello, World!'

@app.route('/upload/<userId>')
def upload(userId):
    s3 = common.connectS3()
    common.decompress(s3, userId)
    return f'Hello, {userId}!'

if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)