<template lang="pug">
.modal-container
    div(:class='{"modal-open": isOpen}')
    .container
        .modal(:class='{show: isOpen}')
        .modal-dialog
            .modal-content
            .modal-header
                button.close(@click='$emit("onCloseModal")') X
            .modal-body
                .socal_login_container
                    h4.modal-title 서비스 이용을 위해서 로그인이 필요합니다!
    .modal-backdrop(:class='{show: isOpen}')
</template>

<script>
// import $ from '@/utils'
// import accountApi from '@/api/account'
import { mapActions, mapMutations } from 'vuex'

export default {
props: ['isOpen'],
data () {
return {
    id: '',
    password: '',
    isProcess: false,
    cannotLogin: false,
    isLoginFailed: false,
}
},
methods: {
...mapActions(['fetchUser']),
...mapMutations(['setToken']),
    // login () {
    //     if (this.isProcess) return
    //     if (this.id.trim() === '' || this.password.trim() === '') {
    //     this.cannotLogin = true
    //     return
    //         }
    //         accountApi.login(
    //         {
    //             id: this.id,
    //             password: this.password,
    //             socialType: 'LOCAL'
    //         },
    //         body => {
    //             this.setToken(body.token)
    //             this.id = this.password = ''
    //             this.isProcess = false
    //             this.fetchUser(() => {
    //             this.$emit('onCloseModal')
    //             })
    //         },
    //         err => {
    //             if (err.response.data.status === 401) {
    //             this.isLoginFailed = true
    //             }
    //         }
    //         )
    //     },
    //     inputChanged () {
    //         if (!this.cannotLogin) return
    //         if (this.id.trim() !== '' && this.password.trim() !== '') {
    //         this.cannotLogin = false
    //         }
    //     },
    //     socialLoginUrl (socialType) {
    //         return $.getSocialLoginUrl(socialType)
    //     }
    }
}
</script>

<style scoped>

.container {
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: auto;
    margin-left: auto;
}
.modal-open .modal {
    overflow-x: hidden;
    overflow-y: auto;
}

.modal {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1050;
    display: none;
    width: 100%;
    height: 100%;
    overflow: hidden;
    outline: 0;
}

.modal-dialog {
    position: relative;
    width: auto;
    margin: 0.5rem;
    pointer-events: none;
}

.modal.show .modal-dialog {
    -webkit-transform: none;
    transform: none;
}

.modal-content {
    position: relative;
    display: -ms-flexbox;
    display: -webkit-box;
    display: flex;
    -ms-flex-direction: column;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    flex-direction: column;
    width: 100%;
    pointer-events: auto;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid rgba(0, 0, 0, 0.2);
    border-radius: 0.3rem;
    outline: 0;
}

.modal-header {
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

.modal-title {
    margin-bottom: 0;
    line-height: 1.5;
    font-size: 1.5rem;
}

.modal-header .close {
    padding: 1rem 1rem;
    margin: -1rem -1rem -1rem auto;
    cursor: pointer;
}

button.close {
    padding: 0;
    background-color: transparent;
    border: 0;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
}

.close {
    float: right;
    font-size: 1.5rem;
    font-weight: 700;
    line-height: 1;
    color: #000;
    text-shadow: 0 1px 0 #fff;
    opacity: .5;
}

.show {
    display: block;
}
.modal-body {
    padding-left: 2em;
    padding-right: 2em;
    position: relative;
    -ms-flex: 1 1 auto;
    -webkit-box-flex: 1;
    flex: 1 1 auto;
    padding: 1rem;
}

.form-control {
    display: block;
    width: calc(100% - 1.5rem);
    height: calc(1.5em + 0.75rem + 2px);
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
    -webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
    transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
    transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
}

.socal_login_container {
    text-align: center
}

.or-text {
    position: absolute;
    left: 46%;
    top: 0;
    background: #fff;
    padding: 10px;
    color: rgba(0,0,0,.45);
}

.or-separator {
    border-bottom: 1px solid #eee;
    padding: 10px 0;
    position: relative;
    display: block;
    margin-top: 20px;
    margin-bottom: 30px;
    font-size: 1em;
}

.form-item {
    margin-bottom: 18px;
}

.btn-primary.disabled {
    background-color: #a0d6ff;
    border-color: #a0d6ff;
}

.modal-backdrop.show {
    position: fixed;
    top: 0;
    left: 0;
    z-index: 1040;
    width: 100vw;
    height: 100vh;
    background-color: #000;
    opacity: 0.5;
}

.modal-footer {
    display: -ms-flexbox;
    display: -webkit-box;
    display: flex;
    -ms-flex-wrap: wrap;
    flex-wrap: wrap;
    -ms-flex-align: center;
    -webkit-box-align: center;
    align-items: center;
    -ms-flex-pack: end;
    -webkit-box-pack: end;
    justify-content: flex-end;
    padding: 0.75rem;
    border-top: 1px solid #dee2e6;
    border-bottom-right-radius: calc(0.3rem - 1px);
    border-bottom-left-radius: calc(0.3rem - 1px);
}

@media screen and (min-width: 768px) {
    .container {
    max-width: 720px;
    }
}
@media screen and (min-width: 576px) {
    .container {
    max-width: 540px;
    }
    .modal-dialog {
    max-width: 500px;
    margin: 1.75rem auto;
    }
}
</style>
    