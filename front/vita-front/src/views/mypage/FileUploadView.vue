<template>
  <div class="text-center">
    <div class="container">
      <div id="header">
        <VueHeader
          :VueHeaderTitle="VueHeaderTitle"
          :VueHeaderContent="VueHeaderContent"
        />
      </div>
      <!-- 설명란 -->
      <div id="fileupload">
        <div id="fileuploadLeft">
          <div>
            <hooper style="height: 50%">
              <slide>
                <div>
                  <p style="font-size: 1rem; font-weight: 800; color: #4e8aff">
                    Step 1.
                  </p>
                  <img
                    width="80%"
                    src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FccVdIO%2Fbtr41J0KddD%2Fir81Nks5a9nLkqLlkCYTKk%2Fimg.png"
                  />
                </div>
              </slide>
              <slide>
                <div>
                  <p style="font-size: 1rem; font-weight: 800; color: #4e8aff">
                    Step 2.
                  </p>
                  <img
                    width="80%"
                    src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFVrAD%2Fbtr45lx4C4m%2FLKmrLEn0bWNwj0f3q9qDD1%2Fimg.png"
                  />
                </div>
              </slide>
              <slide>
                <div>
                  <p style="font-size: 1rem; font-weight: 800; color: #4e8aff">
                    Step 3.
                  </p>
                  <img
                    width="80%"
                    src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FsW7IR%2Fbtr42z4Ewb9%2F8jZo4oAZ8BZQEDp5SqFfhk%2Fimg.png"
                  />
                </div>
              </slide>

              <hooper-navigation slot="hooper-addons"></hooper-navigation>
            </hooper>
          </div>
        </div>
        <!-- 파일 업로드 -->
        <div id="fileuploadRight">
          <b-form-file
            id="bootfile"
            v-model="file1"
            :state="Boolean(file1)"
            placeholder="Choose a file or drop it here"
            drop-placeholder="Drop file here..."
          ></b-form-file>
          <div class="mt-3">선택된 파일: {{ file1 }}</div>
          <!-- 파일 업로드 버튼 -->
          <b-button id="btn-fileuploads" @click="uploadFile(file1)"
            >파일 업로드</b-button
          >
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
  }),
  computed: {
    ...mapGetters(["token", "user"]),
  },
  methods: {
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
              params: { userId: userId },
            })
            .then((response) => {
              console.log(response);
            });
        });
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
</style>