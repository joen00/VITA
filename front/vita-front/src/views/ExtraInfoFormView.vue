<template>
    <div id="backgroundcolor">
        <div class="text-center">
            <div class="container">
                <div class="header">
                    <VueHeader :VueHeaderTitle = VueHeaderTitle :VueHeaderContent = VueHeaderContent />
                </div>
                <div class="body">
                    <div id="form">
                        <b-form @submit="onSubmit" id="input-form">
                            <b-container fluid>
                                <!-- 닉네임 -->
                                <b-row class="my-1">
                                    <b-col sm="3">
                                        <label for="input-nickname">닉네임:</label>
                                    </b-col>
                                    <b-col sm="9">
                                        <b-form-input id="input-nickname" v-model="form.nickname" required></b-form-input>
                                    </b-col>
                                </b-row> 

                                <!-- 나이 -->
                                <b-row class="my-1">
                                    <b-col sm="3">
                                        <label for="input-age">나이:</label>
                                    </b-col>
                                    <b-col sm="9">
                                        <b-form-input id="input-age" type="number" v-model="form.age" required></b-form-input>
                                    </b-col>
                                </b-row>

                                <!-- 성별 -->
                                <b-row class="my-1">
                                    <b-form-group label="성별:">
                                        <b-form-radio-group
                                            id="radio-group"
                                            v-model="form.gender"
                                            :options="optionsGender"
                                        ></b-form-radio-group>
                                    </b-form-group>
                                </b-row>

                                <!-- 키 -->
                                <b-row class="my-1">
                                    <b-col sm="3">
                                        <label for="input-height">키:</label>
                                    </b-col>
                                    <b-col sm="9">
                                        <b-form-input id="input-height" type="number" step="any" v-model="form.height" required></b-form-input>
                                    </b-col>
                                </b-row>

                                <!-- 체중 -->
                                <b-row class="my-1">
                                    <b-col sm="3">
                                        <label for="input-weight">체중:</label>
                                    </b-col>
                                    <b-col sm="9">
                                        <b-form-input id="input-weight" type="number" step="any" v-model="form.weight" required></b-form-input>
                                    </b-col>
                                </b-row>

                                <!-- 기종 --->
                                <b-row class="my-1">
                                    <b-form-group label="휴대폰 기종:">
                                        <b-form-radio-group
                                            id="radio-group-1"
                                            v-model="form.phoneType"
                                            :options="optionsPhoneType"
                                        ></b-form-radio-group>
                                    </b-form-group>
                                </b-row>

                                <!-- 공개여부 -->
                                <b-form-group id="input-group-3" label="정보 공개 여부:" label-for="input-3">
                                    <b-form-select
                                    id="input-3"
                                    v-model="form.userPublic"
                                    :options="publics"
                                    required
                                    ></b-form-select>
                                </b-form-group>
                                <b-button type="submit" variant="primary">Submit</b-button>
                            </b-container>
                        </b-form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import VueHeader from '@/components/common/VueHeader.vue';
import axios from "axios";
import { mapGetters, mapActions } from 'vuex'

// const SERVER_URL = "http://j8b106.p.ssafy.io:8000/users";
const SERVER_URL = "http://localhost:8080/users";
const MY_USER_ID = "2703564897";
// const token = this.$store.state.token;

export default {
    name: "ExtraInfoFormView",

    components: {
        VueHeader,
    },

    computed: {
        ...mapGetters(['token', 'user']),
    },

    data() {
        return {
            VueHeaderTitle: "Vita에 오신 것을 환영합니다!",
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
                    
                    // nickname: this.form.nickname,
                    // age: this.form.age,
                    // height: this.form.height,
                    // weight: this.form.weight,
                    // gender: this.form.gender,
                    // phoneType: this.form.phoneType,
                    // userPublic: this.form.userPublic,
                    // userId: MY_USER_ID,
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
        width: 90%;
        /* background: #E0F4FD; */
        background: #FFFFFFCC;
        box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
        border-radius: 20px;
        margin: 1rem;
        color: rgb(0, 0, 0);
        display: flex;
        justify-content:space-around;
        align-items: center;
        padding: 2rem;
    }

</style>