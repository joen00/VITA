<template>
  <div class="text-center">
    <div class="container">
      <div id="header">
        <VueHeader
          :VueHeaderTitle="VueHeaderTitle"
          :VueHeaderContent="VueHeaderContent"
        />
      </div>

      <div class="wrapper">
        <span v-show="!convertPhoneType" id="label-samsung-active">SAMSUNG</span>
        <span v-show="convertPhoneType" id="label-samsung-inactive">SAMSUNG</span>
        <div class="btn-switch">
          <input type="checkbox" id="switch"  @click="convertType">
          <label for="switch" class="switch_label">
            <span class="onf_btn"></span>
          </label>
        </div>
        <span v-show="convertPhoneType" id="label-apple-active">APPLE</span>
        <span v-show="!convertPhoneType" id="label-apple-inactive">APPLE</span>
      </div>
      <!-- 설명란 -->
      <div id="fileupload">
        <div id="fileuploadLeft">
            <hooper style="height: 50%">
              
                <!-- SAMSUNG 가이드 -->
                <slide v-if="convertPhoneType == false">
                  <div>
                    <p class="slide-step">
                      [  Step 1  ] &nbsp; 삼성 'Samsung-Health' 앱 실행 후 개인 데이터 다운로드
                    </p>
                    <img
                      width="80%"
                      :src="require(`/public/upload-guide/samsung1.png`)"
                    />
                  </div>
                </slide>
                <slide v-if="convertPhoneType == false">
                  <div>
                    <p class="slide-step">
                      [  Step 2  ] &nbsp; 내 파일의 'Samsung Health' 폴더에서 다운로드된 건강 데이터 확인
                    </p>
                    <img
                      width="80%"
                      :src="require(`/public/upload-guide/samsung2.png`)"
                    />
                  </div>
                </slide>
                <slide v-if="convertPhoneType == false">
                  <div>
                    <p class="slide-step">
                      [  Step 3  ] &nbsp; 다운로드된 건강 데이터 Vita에 업로드
                    </p>
                    <img
                      width="80%"
                      :src="require(`/public/upload-guide/samsung3.png`)"
                    />
                  </div>
                </slide>

                <!-- APPLE 가이드 -->
                <slide v-if="convertPhoneType == true">
                  <div>
                    <p class="slide-step">
                      [  Step 1  ] &nbsp; 애플 '건강' 앱 실행 후 건강 데이터 내보내기
                    </p>
                    <img
                      width="80%"
                      :src="require(`/public/upload-guide/apple1.png`)"
                    />
                  </div>
                </slide>
                <slide v-if="convertPhoneType == true">
                  <div>
                    <p class="slide-step">
                      [  Step 2  ] &nbsp; 저장된 건강 데이터 Vita에 업로드
                    </p>
                    <img
                      width="80%"
                      :src="require(`/public/upload-guide/apple2.png`)"
                    />
                  </div>
                </slide>

                <hooper-navigation slot="hooper-addons"></hooper-navigation>
              </hooper>
          </div>

        <!-- 파일 업로드 -->
        <div id="fileuploadRight">
          <p class="file-upload-desc">⭐ .zip 파일 형태로 업로드 해주세요!  ⭐</p>
          <b-form-file
            id="bootfile"
            v-model="file1"
            :state="Boolean(file1)"
            placeholder="Choose a file or drop it here"
            drop-placeholder="Drop file here..."
          ></b-form-file>
          <!-- <div class="mt-3">선택된 파일: {{ file1 }}</div> -->
          <!-- 파일 업로드 버튼(클릭 시 버튼에 processing 애니메이션이 적용됨) -->
          <b-button v-if="uploadBtnState == 'normal'" id="btn-fileuploads" @click="uploadFile(file1)">
            파일 업로드
          </b-button>
          <b-button v-else-if="uploadBtnState =='loading'" id="btn-fileuploads">
            업로드 중...
            <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
          </b-button>
          
        </div>
      </div>
    </div>
  </div>
</template>
  
<script>
import VueHeader from "@/components/common/VueHeader.vue";
import axios from "axios";
import { mapGetters } from "vuex";
import { Hooper, Slide, Navigation as HooperNavigation } from "hooper";
import "hooper/dist/hooper.css";

// 파일 업로드

export default {
  name: "FileUploadView",
  components: {
    VueHeader,
    Hooper,
    Slide,
    Hooper,
    Slide,
    HooperNavigation,
  },
  data: () => ({
    VueHeaderTitle: "파일 업로드",
    VueHeaderContent:
      "웨어러블기능을 이용하기 위해 수집된 데이터를 업로드 해보세요.",
    file1: null,
    slide: 0,
    sliding: null,
    uploadBtnState: "normal",
    convertPhoneType: false,
  }),
  computed: {
    ...mapGetters(["token", "user", "userPhoneType"]),
    uploadBtnNormal() {
      return this.uploadBtnState = "normal"
    },
    uploadBtnLoading() {
      return this.uploadBtnState = "loading";
    },
  },
  methods: {
    convertType() {
      this.convertPhoneType = !this.convertPhoneType;
    },
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
    uploadFile(file) {
      if (file == null) {
        alert("파일을 먼저 업로드 하세요!");
        return;
      }
      this.uploadBtnLoading;
      const formData = new FormData();
      formData.append("multipartFile", file);

      axios
        .post(
          this.$store.state.serverBaseUrl + `/users/mypage/upload/`,
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data",
              Authorization: `Bearer ${this.token}`,
            },
          }
        )
        .then((response) => {
          console.log(response);
          const userId = response.data;
          axios
            .get(`https://j8b106.p.ssafy.io:5000/upload`, {
              params: { userId: userId, device: this.convertPhoneType ? "APPLE" : "SAMSUNG" },
            })
            .then((response) => {
              console.log(response);
              if (response.data.substr(0, 1) == "S") {
                alert("파일 업로드가 완료되었습니다.")
              } else {
                alert("파일 업로드에 실패하였습니다. 올바른 파일을 업로드하세요.")
              }
              this.uploadBtnNormal;
            })
            .catch(() => {
              alert("파일 형식이 잘못되었습니다. 올바른 파일을 업로드하세요.")
              this.uploadBtnNormal;
            })
            ;
        })
        .catch(() => {
          this.uploadBtnNormal;
          alert("파일 형식이 잘못되었습니다. 올바른 파일을 업로드하세요.")
        })
        ;
    },
    created() {
      console.log(this.serverBaseUrl);
    },
  },
};
</script>

<style>
/* ----------------------------------------- */
#fileupload {
  display: flex;
  /* justify-content: space-around; */
  align-items: center;
}
#fileuploadLeft {
  width: 65%;
  margin-top: 1rem;
}
#fileuploadRight {
  height: 90%;
  width: 35%;
  display: flex;
  flex-direction: column;
  /* justify-content: space-around; */
  align-items: center;
}

#bootfile__BV_file_outer_ {
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  height: 200px;
}
.mt-3 {
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  height: 50px;
  width: 100%;
  padding: 1rem;
}
.custom-file-label {
  margin-top: 4.5rem;
}

/* 버튼 */
#btn-fileuploads {
  margin-top: 1rem;
  width: 50%;
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
}

#btn-fileuploads:hover {
  width: 50%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin-top: 1rem;
}

.slide-step {
  font-size: 1.2rem; 
  font-weight: 800; 
  color: #4e8aff; 
  margin-top: 2rem; 
  text-align: start;
  margin-left: 10%;
}

#fileupload {
  margin-bottom: 5rem;
}

/* 스위치 버튼 */
.wrapper {
  width: 50px;
  height: 50px;
  text-align: center;
  margin: 50px auto;
  display: flex;
  justify-content:  center;
}
#switch {
  position: absolute;
  /* hidden */
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
}

.switch_label {
  position: relative;
  cursor: pointer;
  display: inline-block;
  width: 58px;
  height: 28px;
  background: rgb(66, 99, 231);
  border: 2px solid rgb(66, 99, 231);
  /* background: #fff;
  border: 2px solid #daa; */
  border-radius: 20px;
  transition: 0.2s;
}
.switch_label:hover {
  background: #efefef;
}
.onf_btn {
  position: absolute;
  top: 2.5px;
  left: 3px;
  display: inline-block;
  width: 20px;
  height: 20px;
  border-radius: 20px;
  background: #fff;
  /* background: #daa; */
  transition: 0.2s;
}

/* checking style */
#switch:checked+.switch_label {
  /* background: rgb(66, 99, 231);
  border: 2px solid rgb(66, 99, 231); */
  background: rgb(165, 196, 145);
  border: 2px solid rgb(165, 196, 145);
}

#switch+.switch_label:hover {
  background: rgb(44, 70, 175);
}

#switch:checked+.switch_label:hover {
  background: rgb(142, 169, 125);
}

/* move */
#switch:checked+.switch_label .onf_btn {
  top: 2.5px;
  left: 34px;
  background: #fff;
  box-shadow: 1px 2px 3px #00000020;
}

.btn-switch {
  margin-inline: 15px;
}

#label-apple-active {
  color: rgb(87, 124, 64);
  font-size: 20px;
}

#label-samsung-active {
  color: rgb(44, 70, 175);
  font-size: 20px;
}

#label-apple-inactive {
  color: rgb(199, 199, 199);
  font-size: 20px;
}

#label-samsung-inactive {
  color: rgb(199, 199, 199);
  font-size: 20px;
}

.file-upload-desc {
  font-size: 1.2rem; 
  font-weight: 600; 
  color: #4e8aff; 
}
</style>