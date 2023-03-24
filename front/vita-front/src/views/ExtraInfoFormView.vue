<template>
    <div id="backgroundcolor">
        <div class="text-center">
                <div class="header">
                    <VueHeader :VueHeaderTitle = VueHeaderTitle :VueHeaderContent = VueHeaderContent />
                </div>
                <div class="body">
                    <div id="form">
                        <b-form @submit.prevent.stop="onSubmit" id="input-form">
                            <b-container>
                                <!-- 닉네임 -->
                                <b-row class="my-5">
                                    <b-col sm="2">
                                        <label for="input-nickname">닉네임:</label>
                                    </b-col>
                                    <b-col sm="10">
                                        <b-form-input id="input-nickname" v-model="form.nickname" :state="validationNickname" required ></b-form-input>
                                        <b-form-invalid-feedback :state="validationNickname">
                                            Please set your nickname.
                                        </b-form-invalid-feedback>
                                        <b-form-valid-feedback :state="validationNickname">
                                            Looks Good.
                                        </b-form-valid-feedback>
                                    </b-col>
                                </b-row> 

                                <!-- 나이 -->
                                <b-row class="my-5">
                                    <b-col sm="2">
                                        <label for="input-age">나이:</label>
                                    </b-col>
                                    <b-col sm="10">
                                        <b-form-input id="input-age" type="number" :state="validationAge" v-model="form.age" required ></b-form-input>
                                        <b-form-invalid-feedback :state="validationAge">
                                            Your age must be at least 1 years old.
                                        </b-form-invalid-feedback>
                                        <b-form-valid-feedback :state="validationAge">
                                            Looks Good.
                                        </b-form-valid-feedback>
                                    </b-col>
                                </b-row>

                                <!-- 성별 -->
                                <b-row class="my-5">
                                    <b-col sm="2">
                                        <label for="input-gender">성별:</label>
                                    </b-col>
                                    <b-col sm="10">
                                        <b-form-radio-group
                                            id="radio-group-gender"
                                            v-model="form.gender"
                                            :options="optionsGender"
                                            buttons
                                        ></b-form-radio-group>
                                    </b-col>
                                </b-row>

                                <!-- 키 -->
                                <b-row class="my-5">
                                    <b-col sm="2">
                                        <label for="input-height">키:</label>
                                    </b-col>
                                    <b-col sm="10">
                                        <b-form-input id="input-height" type="number" step="any" v-model="form.height" :state="validationHeight" required ></b-form-input>
                                        <b-form-invalid-feedback :state="validationHeight">
                                            Please set your height.
                                        </b-form-invalid-feedback>
                                        <b-form-valid-feedback :state="validationHeight">
                                            Looks Good.
                                        </b-form-valid-feedback>
                                    </b-col>
                                </b-row>

                                <!-- 체중 -->
                                <b-row class="my-5">
                                    <b-col sm="2">
                                        <label for="input-weight">체중:</label>
                                    </b-col>
                                    <b-col sm="10">
                                        <b-form-input id="input-weight" type="number" step="any" :state="validationWeight" v-model="form.weight" required ></b-form-input>
                                        <b-form-invalid-feedback :state="validationWeight">
                                            Please set your weight.
                                        </b-form-invalid-feedback>
                                        <b-form-valid-feedback :state="validationWeight">
                                            Looks Good.
                                        </b-form-valid-feedback>
                                    </b-col>
                                </b-row>

                                <!-- 기종 --->
                                <b-row class="my-5">
                                    <b-col sm="2">
                                        <label for="input-phone-type">휴대폰 기종:</label>
                                    </b-col>
                                    <b-col sm="10">
                                        <b-form-radio-group
                                            id="radio-group-phone-type"
                                            v-model="form.phoneType"
                                            :options="optionsPhoneType"
                                            buttons
                                        ></b-form-radio-group>
                                    </b-col>
                                </b-row>

                                <!-- 공개여부 -->
                                <b-row class="my-5">
                                    <b-col sm="2">
                                        <label for="input-user-public">정보 공개 여부:</label>
                                    </b-col>
                                    <b-col sm="10">
                                        <b-form-radio-group
                                            id="input-user-public"
                                            v-model="form.userPublic"
                                            :options="publics"
                                            buttons
                                        ></b-form-radio-group>
                                    </b-col>
                                </b-row>

                                <b-row class="my-5">
                                    <b-col sm="2"></b-col>
                                    <b-col sm="10">
                                        <b-button type="submit" variant="primary">Submit</b-button>
                                    </b-col>
                                </b-row>
                            </b-container>
                        </b-form>
                    </div>
                </div>
        </div>
    </div>
</template>

<script>
import VueHeader from '@/components/common/VueHeader.vue';
import axios from "axios";
import { mapGetters } from 'vuex'

// const SERVER_URL = "http://j8b106.p.ssafy.io:8000/users";
const SERVER_URL = "http://localhost:8080/users";
const MY_USER_ID = "2703564897";
// const token = this.$store.state.token;

export default {
    name: "ExtraInfoFormView",

    components: {
        VueHeader,
    },

    data() {
        return {
            VueHeaderTitle: "VITA에 오신 것을 환영합니다!",
            VueHeaderContent: "서비스 사용을 위해 추가 정보를 입력해주세요.",

            form: {
                nickname: '',
                age: 0,
                height: 0,
                weight: 0,
                gender: 'male',
                phoneType: 'SAMSUNG',
                userPublic: true
            },
            optionsGender: [
                { text: '남자', value: 'male' },
                { text: '여자', value: 'female' },
            ],
            optionsPhoneType: [
                { text: '삼성', value: 'SAMSUNG' },
                { text: '애플', value: 'APPLE' },
            ],
            publics: [
                { text: 'public', value: true },
                { text: 'private', value: false },
            ],
            show: true
        };
    },

    computed: {
        ...mapGetters(['token', 'user']),
 
        validationNickname() {
            return this.form.nickname != ""
        },

        validationAge() {
            return this.form.age > 0 && this.form.age < 150
        },
        
        validationHeight() {
            return this.form.height > 0
        },

        validationWeight() {
            return this.form.weight > 0
        },
    },

    methods: {
        onSubmit(event) {
            event.preventDefault();
            console.log(JSON.stringify(this.form));
            console.log(this.token);
            axios
                .put(SERVER_URL + `/extrainfo`,
                    {
                        // extraInfoDto: JSON.stringify(this.form),
                        nickname: this.form.nickname,
                        age: this.form.age,
                        height: this.form.height,
                        weight: this.form.weight,
                        gender: this.form.gender,
                        phoneType: this.form.phoneType,
                        userPublic: this.form.userPublic,
                        userId: MY_USER_ID,

                    },
                    {
                        headers: {
                        Authorization: `Bearer ${this.token}`,
                    },

                }).then((response) => {
                    console.log(response)
                })
        }
    }
};
</script>

<style scoped>
    #backgroundcolor {
        background: linear-gradient(
            146.55deg,
            rgba(174, 162, 220, 0.2) -70.09%,
            rgba(140, 112, 253, 0) 55.52%
        ),
        linear-gradient(
            0deg,
            rgba(243, 255, 228, 0.2) -26.8%,
            rgba(178, 215, 149, 0.012) 16.1%,
            rgba(220, 243, 253, 0) 131.6%
        ),
        linear-gradient(239.1deg, #e2faff -29.57%, rgba(222, 243, 248, 0) 131.52%);
        height: 1000vh;
        overflow: hidden;
        margin:0;
        background-size: cover;
        background-repeat: no-repeat;
        background-position: center;
    }
    .body {
        display: flex;
        justify-content: center;
    }

    #form {
        width: 60%;
        background: #FFFFFFCC;
        box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
        border-radius: 20px;
        margin: 1rem;
        color: rgb(0, 0, 0);
        display: flex;
        justify-content:space-around;
        align-items: center;
    }

    #input-form {
        width: 90%;
    }

    label {
        float: right;
        font-weight: 600;
        color: #172176;
    }
</style>