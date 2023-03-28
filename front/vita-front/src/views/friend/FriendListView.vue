<template>
  <div class="text-center">
    <div class="container">
      <div id="header">
        <VueHeader
          :VueHeaderTitle="VueHeaderTitle"
          :VueHeaderContent="VueHeaderContent"
        />
      </div>
      <div id="middle">
        <b-button class="btn" v-b-modal.modal-scrollable id="btn-add-friend"
          >친구 추가</b-button
        >
        <!-- 모달창 -->
        <friend-add-modal></friend-add-modal>
        <!-- 모달창 -->
        <b-form-input
          v-model="inputValue"
          @keyup="getFriendList(inputValue)"
          id="nick-search"
          placeholder="닉네임을 입력하세요."
        ></b-form-input>
        <b-button class="btn" v-b-modal.modal-scrollable id="btn-search-friend"
          >검색</b-button
        >
      </div>
      <div id="list">
        <h3 id="list-h3">친구리스트</h3>
        <div id="list-add-post">
          <div
            id="list-post-div"
            v-for="lists in friendpostlist"
            :key="lists.id"
          >
            <!-- <span style="font-weight: 600">
              {{ lists.user_id }}
            </span> -->
            <b-avatar
              variant="info"
              :src="lists.user_img"
            ></b-avatar>
            <span style="font-weight: 600">{{ lists.user_nickname }} </span>
            <button id="btn-post-add" @click="acceptFriend(lists.user_id)">
              수락
            </button>
            <button
              id="btn-post-delete"
              @click="deleteOrRejectFriend(lists.user_id)"
            >
              거절
            </button>
          </div>
        </div>

        <div
          id="list-div"
          class="scroll"
          v-for="lists in friendlist"
          :key="lists.id"
        >
          <span style="font-weight: 600">
            <!-- {{ lists.user_id }} -->
            <b-avatar
              variant="info"
              :src="lists.user_img"
            ></b-avatar
          ></span>
          <span style="font-weight: 600">{{ lists.user_nickname }} </span>
          <button
            id="btn-delete-friend"
            @click="deleteOrRejectFriend(lists.user_id)"
          >
            친구 삭제
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FriendAddModal from "@/components/friend/FriendAddModal.vue";
import VueHeader from "@/components/common/VueHeader.vue";
import axios from "axios";

// const SERVER_URL = "http://localhost:8080/friend";
// const SERVER_URL = "https://j8b106.p.ssafy.io:8084/friend";
// 유저 검색하거나 친구추가 테스트용
// user_id : 2703564897, user_name: 박서윤, user_nickname: bboong
// user_id : 2715879100, user_name: 이강윤, user_nickname: asdf
// const MY_USER_ID = 2703629614;

export default {
  name: "FriendListView",
  components: {
    FriendAddModal,
    VueHeader,
  },
  data: () => ({
    friendlist: [
      // { id: 1, name: "김광배", score: "10" },
      // { id: 2, name: "이광배", score: "20" },
      // { id: 3, name: "차광배", score: "30" },
    ],
    friendpostlist: [
      // { id: 1, name: "김광배", score: "10" },
      // { id: 2, name: "이광배", score: "20" },
    ],
    inputValue: "",
    VueHeaderTitle: "프렌즈",
    VueHeaderContent: "친구들의 정보를 확인해보세요.",
  }),
  methods: {
    getFriendList(inputValue) {
      console.log(inputValue);
      axios
        .get(this.$store.state.serverBaseUrl + `/friend` + `/${inputValue}`, {
          headers: {
            userID: this.$store.state.myUserId,
          },
        })
        .then((response) => {
          console.log(response);
          this.friendlist = [];
          response.data.map((data) => {
            this.friendlist.push(data);
          });
        });
    },
    deleteOrRejectFriend(user_id) {
      axios
        .delete(this.$store.state.serverBaseUrl + `/friend`, {
          headers: {
            sendingUserId: this.$store.state.myUserId,
            receivingUserId: user_id,
          },
        })
        .then((response) => {
          console.log(response);
          this.friendpostlist = [];
          this.getFriendPostList();
          this.friendlist = [];
          this.getFriendList();
        });
    },
    getFriendPostList() {
      axios
        .get(this.$store.state.serverBaseUrl + `/friend` + `/applyList`, {
          headers: {
            userID: this.$store.state.myUserId,
          },
        })
        .then((response) => {
          console.log(response);
          response.data.map((data) => {
            this.friendpostlist.push(data);
          });
        });
    },
    acceptFriend(sendingUserId) {
      axios
        .put(this.$store.state.serverBaseUrl + `/friend`, null, {
          headers: {
            sendingUserId: sendingUserId,
            receivingUserId: this.$store.state.myUserId,
          },
        })
        .then((response) => {
          console.log(response);
          this.friendpostlist = [];
          this.getFriendPostList();
          this.friendlist = [];
          this.getFriendList("");
          this.inputValue = ""
        });
    },
  },
  created() {
    this.getFriendList("");
    this.getFriendPostList();
  },
};
</script>

<style>
/* ----------------------------------------- */
#header {
  width: 100%;
  height: 16%;
  margin-bottom: 2rem;
}
#middle {
  height: 4rem;
  display: flex;
  justify-content: center;
}
#btn-add-friend {
  width: 8%;
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin: 0.5rem;
}
#btn-add-friend:hover {
  width: 8%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin: 0.5rem;
}

#btn-search-friend {
  width: 5%;
  height: 35px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin: 0.5rem;
}
#btn-search-friend:hover {
  width: 5%;
  height: 35px;
  border: none;
  color: #3695be;
  border: solid 2px #3695be;
  font-weight: 600;
  background: rgb(255, 255, 255);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 12px;
  margin: 0.5rem;
}

#nick-search {
  box-sizing: border-box;
  width: 24%;
  height: 35px;
  font-size: 14px;
  font-weight: 600;
  color: #3695be;
  border: 2px solid #3695be;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border-radius: 15px;
  margin: 0.5rem;
  /* float: left; */
  /* padding-top: 0.3rem; */
}

/* 리스트의 수락 거절 */

#btn-delete-friend {
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

#btn-post-add {
  width: 55px;
  height: 24px;
  background: #79b6fd;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  color: white;
  font-weight: 800;
  border: none;
  margin-right: -3.5rem;
  font-size: 0.9rem;
}
#btn-post-delete {
  width: 55px;
  height: 24px;
  font-weight: 800;
  color: white;
  background: #ffa755;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  border: none;
  font-size: 0.9rem;
}

/* 아래쪽 CSS scroll이 scroll없애는 부분 */
#list {
  padding-left: 2rem;
  padding-right: 2rem;
  width: 40%;
  height: 27rem;
  background-color: aquamarine;
  margin: 0 auto;
  margin-top: 0.5rem;
  box-sizing: border-box;
  background: rgba(255, 255, 255, 0.8);
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  clear: both;
  overflow: scroll;
}
#list-h3 {
  font-family: "Exo 2";
  font-style: normal;
  font-weight: 700;
  font-size: 26px;
  color: #172176;
  margin: 1rem;
}
/* 큰 list 목록 특 */
#list-div {
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
  /* padding-top: 0.9rem; */
}
#list-post-div {
  width: 90%;
  height: 3.3rem;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  margin: 1rem;
  color: white;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

#list::-webkit-scrollbar {
  display: none;
}

#list {
  -ms-overflow-style: none; /* 인터넷 익스플로러 */
  scrollbar-width: none; /* 파이어폭스 */
}
</style>