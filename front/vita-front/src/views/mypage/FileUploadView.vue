<template>
  <div class="text-center">
    <div class="container">
      <div id="header">
        <!-- <div id="header-title">파일 업로드</div>
        <div id="header-content">웨어러블기능을 이용하기 위해 수집된 데이터를 업로드 해보세요.</div> -->
        <VueHeader
          :VueHeaderTitle="VueHeaderTitle"
          :VueHeaderContent="VueHeaderContent"
        />
      </div>
      <div id="fileuploadLeft">
        <div>
          <b-carousel
            id="carousel-1"
            v-model="slide"
            controls
            indicators
            img-width="1000"
            img-height="400"
            style="text-shadow: 1px 1px 2px #333"
            @sliding-start="onSlideStart"
            @sliding-end="onSlideEnd"
          >
            <!-- Slides with image only -->
            <b-carousel-slide
              img-src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FFVrAD%2Fbtr45lx4C4m%2FLKmrLEn0bWNwj0f3q9qDD1%2Fimg.png"
            ></b-carousel-slide>
            <b-carousel-slide
              img-src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FsW7IR%2Fbtr42z4Ewb9%2F8jZo4oAZ8BZQEDp5SqFfhk%2Fimg.png"
            ></b-carousel-slide>
            <b-carousel-slide
              img-src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FccVdIO%2Fbtr41J0KddD%2Fir81Nks5a9nLkqLlkCYTKk%2Fimg.png"
            ></b-carousel-slide>
          </b-carousel>
        </div>
      </div>
      <div id="fileuploadRight">
        <b-form-file
          v-model="file1"
          :state="Boolean(file1)"
          placeholder="Choose a file or drop it here..."
          drop-placeholder="Drop file here..."
        ></b-form-file>
        <div class="mt-3">Selected file: {{ file1 }}</div>
        <b-button @click="uploadFile(file1)">Button</b-button>
      </div>
    </div>
  </div>
</template>
  
<script>
import VueHeader from "@/components/common/VueHeader.vue";
import axios from "axios";
import { mapGetters } from "vuex";

export default {
  name: "FileUploadView",
  components: {
    VueHeader,
  },
  data: () => ({
    VueHeaderTitle: "파일 업로드",
    VueHeaderContent:
      "웨어러블기능을 이용하기 위해 수집된 데이터를 업로드 해보세요.",
    file1: null,
  }),
  computed: {
    ...mapGetters(["token", "user"]),
  },
  methods: {
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
        });
    },
    confirmFile() {},
    created() {
      console.log(this.serverBaseUrl);
      console.log(this.myUserId);
    },
  },
};
</script>

<style>
/* ----------------------------------------- */
#fileuploadLeft {
  width: 65%;
  height: 42rem;
  float: left;
  margin-top: 1rem;
}
#fileuploadRight {
  width: 35%;
  height: 42rem;
  float: left;
}
</style>