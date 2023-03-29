<template>
  <b-modal id="modal-scrollable" hide-footer scrollable>
    <!-- <template #modal-title>
        <div>친구 추가</div>
      </template> -->
    <template #modal-header="{ close }">
      <div id="modal-header-div">
        <h3 id="modal-header-font">친구 추가</h3>
        <b-button
          style="width: 36px; height: 36px; font-weight: 600"
          variant="outline-danger"
          @click="close()"
        >
          X
        </b-button>
      </div>
    </template>
    <div id="modal-middle">
      <b-form-input
        v-model="inputValue"
        @keyup="getSearchFriendList(inputValue)"
        id="modal-nick-search"
        placeholder="닉네임을 입력하세요."
      ></b-form-input>
    </div>
    <div id="modal-content">
      <div
        id="modal-content-list-post-div"
        v-for="lists in searchlist"
        :key="lists.id"
      >
        <b-avatar variant="info" :src="lists.user_img"></b-avatar>
        <span style="font-weight: 600">{{ lists.user_nickname }} </span>
        <button
          id="btn-modal-add-friend"
          @click="requestFriend(lists.user_nickname)"
        >
          친구 신청
        </button>
      </div>
    </div>
  </b-modal>
</template>

<script>
import axios from "axios";
import { mapGetters } from "vuex";

// const SERVER_URL = "http://localhost:8080/friend";
// const SERVER_URL = "https://j8b106.p.ssafy.io/api/friend";

export default {
  name: "FriendAddModal",
  data: () => ({
    searchlist: [
      // { id: 1, name: "김광배" },
      // { id: 2, name: "이광배" },
      // { id: 3, name: "차광배" },
    ],
    inputValue: "",
  }),

  computed: {
    ...mapGetters(["token", "user"]),
  },

  methods: {
    getSearchFriendList(inputValue) {
      console.log(inputValue);
      axios
        .get(this.$store.state.serverBaseUrl + `/friend` + `/apply/` + `${inputValue}`, {
          headers: {
            Authorization: `Bearer ${this.token}`,
          },
        })
        .then((response) => {
          console.log(response);
          this.searchlist = [];
          response.data.map((data) => {
            this.searchlist.push(data);
          });
        })
        .catch((this.searchlist = []));
    },
    requestFriend(user_nickname) {
      axios
        .post(
          this.$store.state.serverBaseUrl + `/friend` + `/apply`,
          { user_nickname: user_nickname },
          {
            headers: {
              Authorization: `Bearer ${this.token}`,
            },
          }
        )
        .then((response) => {
          console.log(response);
        });
    },
  },
  
  created() {
    this.getSearchFriendList("");
  },
};
</script>

<style>
#modal-header-div {
  display: flex;
  justify-content: space-between;
}
#modal-header-font {
  font-family: "Exo 2";
  font-style: normal;
  font-weight: 700;
  font-size: 30px;
  color: #172176;
  margin-left: 1rem;
  margin-right: 17rem;
  margin-bottom: -1rem;
}
#modal-middle {
  height: 4rem;
  margin: 0 auto;
  vertical-align: middle;
}
#modal-nick-search {
  box-sizing: border-box;
  width: 60%;
  height: 35px;
  font-size: 14px;
  font-weight: 600;
  color: #3695be;
  border: 2px solid #3695be;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border-radius: 15px;
  margin: 0 auto;
  display: flex;
}
#modal-content {
  box-sizing: border-box;
  width: 80%;
  height: 415px;
  border: 2px solid #172176;
  border-radius: 30px;
  margin: 0 auto;
  vertical-align: middle;
  margin-bottom: 3rem;
  clear: both;
  overflow: scroll;
}

/* 큰 list 목록 특 */
/* #list-div {
  width: 90%;
  height: 3rem;
  background: #e0f4fd;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  margin: 1rem;
} */
#modal-content-list-post-div {
  width: 90%;
  height: 3.3rem;
  background: #e0f4fd;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  margin: 1rem;
  color: rgb(0, 0, 0);
  display: flex;
  justify-content: space-around;
  align-items: center;
}

#modal-content::-webkit-scrollbar {
  display: none;
}

#modal-content {
  -ms-overflow-style: none; /* 인터넷 익스플로러 */
  scrollbar-width: none; /* 파이어폭스 */
}

#btn-modal-add-friend {
  width: 89px;
  height: 24px;
  font-weight: 600;
  color: white;
  background: #ffa755;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  border: none;
  font-size: 0.9rem;
}
</style>