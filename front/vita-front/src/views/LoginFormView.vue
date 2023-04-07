<template>
    <div class="container"> 
        <div class="social-login-container">
            <div class="container-header">
                <h4 class="container-title">로그인</h4>
            </div>
            <div class="container-body">
                <a :href="socialLoginUrl(kakao.socialType)" class="social_btn">
                <img :src="kakao.src" :style="{ width: kakao.width, height: kakao.height }" class="social_login" />
                &nbsp;&nbsp;{{ kakao.comment }}
                </a>
            </div>
        </div>
    </div>
</template>

<script>
import $ from '@/utils'
import accountApi from '@/api/account'
import { mapActions, mapMutations } from 'vuex'

export default {
    name: "LoginFormView",

    data () {
    return {
        id: '',
        password: '',
        isProcess: false,
        cannotLogin: false,
        isLoginFailed: false,
        kakao: {
            socialType: 'kakao',
            src: $.getSocialImage('kakao'),
            width: '32px',
            height: '32px',
            comment: '카카오 로그인'
        }
        }
    },
    methods: {
        ...mapActions(['fetchUser']),
        ...mapMutations(['setToken']),
        login() {
            if (this.isProcess) return
            if (this.id.trim() === '' || this.password.trim() === '') {
                this.cannotLogin = true
                return
            }
            accountApi.login(
                {
                    id: this.id,
                    password: this.password,
                    socialType: 'LOCAL'
                },
                body => {
                    this.setToken(body.token)
                    this.id = this.password = ''
                    this.isProcess = false
                    this.fetchUser(() => {
                        this.$emit('onCloseModal')
                    })
                },
                err => {
                    if (err.response.data.status === 401) {
                        this.isLoginFailed = true
                    }
                }
            )
        },
        inputChanged() {
            if (!this.cannotLogin) return
            if (this.id.trim() !== '' && this.password.trim() !== '') {
                this.cannotLogin = false
            }
        },
        socialLoginUrl(socialType) {
            return $.getSocialLoginUrl(socialType)
        }
    }
}
</script>

<style scoped>
.container {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
}

.container-header {
    display: -ms-flexbox;
    display: -webkit-box;
    display: flex;
    -ms-flex-align: start;
    -webkit-box-align: start;
    align-items: flex-start;
    -ms-flex-pack: justify;
    -webkit-box-pack: justify;
    justify-content: space-between;
    padding: 1rem 1rem;
    border-bottom: 1px solid #dee2e6;
    border-top-left-radius: calc(0.3rem - 1px);
    border-top-right-radius: calc(0.3rem - 1px);
}

.container-body {
    padding-left: 2em;
    padding-right: 2em;
    position: relative;
    -ms-flex: 1 1 auto;
    -webkit-box-flex: 1;
    flex: 1 1 auto;
    padding: 2rem;
    display: flex;
    text-align: center;
    justify-content: center;
    align-content: center;
}

.social-login-container {
    width: 50%;
    background: #fff;
    border-radius: 20px;
    margin-top: -50px;
    box-shadow: 1.5px 1.5px 1.5px 1.5px rgba(0.25, 0.25, 0.25, 0.25);
}

.social_btn:hover {
    color: #2098f3;
}
.social_btn {
    font-weight: 400;
    font-size: 16px;
    display: block;
    width: 80%;
    height: 45px;
    line-height: 45px;
    border-radius: 4px;
    border: 1px solid #e8e8e8;
    cursor: pointer;
    color: black;
    text-decoration: none;   
}
</style>