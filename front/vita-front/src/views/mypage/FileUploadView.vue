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
      <div id="fileuploadLeft">
        <div>
          <hooper style="height: 50%">
            <slide>
              <div>
                <img
                  width="80%"
                  src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FccVdIO%2Fbtr41J0KddD%2Fir81Nks5a9nLkqLlkCYTKk%2Fimg.png"
                />
              </div>
            </slide>
            <slide>
              <div>
                <img
                  width="80%"
                  src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFVrAD%2Fbtr45lx4C4m%2FLKmrLEn0bWNwj0f3q9qDD1%2Fimg.png"
                />
              </div>
            </slide>
            <slide>
              <div>
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
          v-model="file1"
          :state="Boolean(file1)"
          placeholder="Choose a file or drop it here..."
          drop-placeholder="Drop file here..."
        ></b-form-file>
        <div class="mt-3">Selected file: {{ file1 }}</div>
        <!-- 파일 업로드 버튼 -->
        <b-button @click="uploadFile(file1)">파일 업로드</b-button>
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
#fileuploadLeft {
  width: 65%;

  float: left;
  margin-top: 1rem;
}
#fileuploadRight {
  width: 35%;

  float: left;
}
</style>