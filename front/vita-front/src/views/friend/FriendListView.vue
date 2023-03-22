<template>
  <div class="text-center">
    <div class="container">
      <div id="header">
        <div id="header-title">프렌즈</div>
        <div id="header-content">친구들의 정보를 확인해보세요.</div>
      </div>
      <div id="middle">
        <b-button v-b-modal.modal-scrollable id="btn-add-friend"
          >친구 추가</b-button
        >
        <!-- 모달창 -->
        <friend-add-modal></friend-add-modal>
        <!-- 모달창 -->
        <b-form-input
          id="nick-search"
          placeholder="닉네임을 입력하세요."
        ></b-form-input>
        <button>검색</button>
      </div>
      <div id="list">
        <h3 id="list-h3">친구리스트</h3>
        <div id="list-add-post">
          <div
            id="list-post-div"
            v-for="lists in friendpostlist"
            :key="lists.id"
          >
            <span style="font-weight: 600">
              {{ lists.user_id }}
            </span>
            <b-avatar
              variant="info"
              :src="lists.user_img"
            ></b-avatar>
            <span style="font-weight: 600">{{ lists.user_nickname }} </span>
            <button id="btn-post-add" @click="acceptFriend(lists.user_id)">수락</button>
            <button id="btn-post-delete" @click="deleteOrRejectFriend(lists.user_id)">거절</button>
          </div>
        </div>

        <div id="list-div" v-for="lists in friendlist" :key="lists.id">
          <span style="font-weight: 600">
            {{ lists.user_id }}
            <b-avatar
              variant="info"
              :src="lists.user_img"
            ></b-avatar
          ></span>
          <span style="font-weight: 600">{{ lists.user_nickname }} </span>
          <button id="btn-delete-friend" @click="deleteOrRejectFriend(lists.user_id)">
            친구 삭제
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FriendAddModal from "@/components/friend/FriendAddModal.vue";
import axios from "axios";

const SERVER_URL = "http://localhost:8080/friend";
const MY_USER_ID = 1;
export default {
  name: "FriendListView",
  components: {
    FriendAddModal,
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
  }),
  methods: {
    getFriendList() {
      axios
        .get(SERVER_URL, {
          headers: {
            userID: MY_USER_ID,
          },
        })
        .then((response) => {
          console.log(response)
          response.data.map((data) => {
            this.friendlist.push(data);
          });
        });
    },
    deleteOrRejectFriend(user_id) {
      axios
        .delete(SERVER_URL, {
          headers: {
            sendingUserId: MY_USER_ID,
            receivingUserId: user_id,
          },
        })
        .then((response) => {
          console.log(response);
          this.friendpostlist = []
          this.getFriendPostList();
          this.friendlist = []
          this.getFriendList();
        });
    },
    getFriendPostList() {
      axios
        .get(SERVER_URL + `/applyList`, {
          headers: {
            userID: MY_USER_ID,
          },
        })
        .then((response) => {
          console.log(response)
          response.data.map((data) => {
            this.friendpostlist.push(data);
          });
        });
    },
    acceptFriend(sendingUserId) {
      axios
        .put(SERVER_URL, null ,{
          headers: {
            sendingUserId: sendingUserId,
            receivingUserId: MY_USER_ID
          },
        }).then((response) => {
          console.log(response)
          this.friendpostlist = []
          this.getFriendPostList();
          this.friendlist = []
          this.getFriendList();
        })
    }
  },
  created() {
    this.getFriendList();
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
#header-title {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 3rem;
  line-height: 97px;
  color: #172176;
}
#header-content {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 1rem;
  line-height: 24px;
  text-align: center;
  margin: -1%;
  color: #47474b;
}
#middle {
  height: 4rem;
  margin: 0 auto;
  vertical-align: middle;
}
#btn-add-friend {
  width: 10%;
  height: 40px;
  border: none;
  color: rgb(255, 255, 255);
  font-weight: 600;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 15px;
  margin-left: 26rem;
  margin-right: 1rem;
  float: left;
}
#nick-search {
  box-sizing: border-box;
  width: 25%;
  height: 40px;
  font-weight: 600;
  color: #3695be;
  border: 2px solid #3695be;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border-radius: 15px;
  float: left;
  padding-top: 0.3rem;
}
#list {
  padding: 0.5rem;
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
}
#list-h3 {
  font-family: "Exo 2";
  font-style: normal;
  font-weight: 700;
  font-size: 26px;
  color: #172176;
}
#list-div {
  width: 90%;
  height: 3.5rem;
  background: #e0f4fd;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  margin: 1rem;
  padding-top: 0.9rem;
}
#btn-delete-friend {
  width: 89px;
  height: 24px;
  font-weight: 600;
  color: white;
  background: #ffa755;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  border: none;
}

#btn-post-add {
  width: 55px;
  height: 24px;

  background: #79b6fd;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  color: white;
  font-weight: 600;
  border: none;
  margin-right: 1rem;
}
#btn-post-delete {
  width: 55px;
  height: 24px;
  font-weight: 600;
  color: white;
  background: #ffa755;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  border: none;
}

#list-post-div {
  width: 90%;
  height: 3.5rem;
  background: #3695be;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 20px;
  margin: 1rem;
  color: white;
  padding-top: 0.9rem;
}
</style>