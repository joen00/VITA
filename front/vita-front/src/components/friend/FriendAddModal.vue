<template>
  <div>
    <b-modal id="modal-scrollable" scrollable title="친구 추가">
      <div id="modal-middle">
        <b-form-input v-model="inputValue" @keyup="getSearchFriendList(inputValue)"
          id="modal-nick-search"
          placeholder="닉네임을 입력하세요."
        ></b-form-input>
        <b-button>검색</b-button>
      </div>
      <div id="modal-content">
        <div id="list-div" v-for="lists in searchlist" :key="lists.id">
          <b-avatar
            variant="info"
            :src="lists.user_img"
          ></b-avatar>
          <span style="font-weight: 600">{{ lists.user_nickname }} </span>
          <button id="btn-delete-friend" @click="requestFriend(lists.user_nickname)">친구 신청</button>
        </div>
      </div>
    </b-modal>
  </div>
</template>

<script>
import axios from 'axios';

const SERVER_URL = "http://j8b106.p.ssafy.io:8000/friend";
// 유저 검색하거나 친구추가 테스트용
// user_id : 2703564897, user_name: 박서윤, user_nickname: bboong 
// user_id : 2715879100, user_name: 이강윤, user_nickname: asdf
const MY_USER_ID = 2703564897;

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
  methods: {
    getSearchFriendList(inputValue) {
      console.log(inputValue)
      axios
        .get(SERVER_URL + `/apply/` + `${inputValue}`, {
          headers: {
            userID: MY_USER_ID,
          },
        })
        .then((response) => {
          console.log(response)
          this.searchlist = []
          response.data.map((data) => {
            this.searchlist.push(data);
          });
        })
        .catch(this.searchlist = []);
    },
    requestFriend(user_nickname) {
      axios
        .post(SERVER_URL + `/apply`, { user_nickname: user_nickname },{
          headers: {
            userID: MY_USER_ID
          },
        }).then((response) => {
          console.log(response)
        })
    },
  },
  created() {
    this.getSearchFriendList();
  }
};
</script>

<style>
#modal-middle {
  height: 4rem;
  margin: 0 auto;
  vertical-align: middle;
}
#modal-nick-search {
  box-sizing: border-box;
  width: 80%;
  height: 40px;
  font-weight: 600;
  color: #3695be;
  border: 2px solid #3695be;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border-radius: 15px;
  float: left;
  padding-top: 0.3rem;
}
#modal-content {
  box-sizing: border-box;
  width: 100%;
  height: 415px;
  border: 2px solid #172176;
  border-radius: 30px;
}
</style>