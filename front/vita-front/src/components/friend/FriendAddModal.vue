<template>
  <div>
    <b-modal id="modal-scrollable" scrollable title="친구 추가">
      <div id="modal-middle">
        <b-form-input
          id="modal-nick-search"
          placeholder="닉네임을 입력하세요."
        ></b-form-input>
        <b-button>검색</b-button>
      </div>
      <div id="modal-content">
        <div id="list-div" v-for="lists in searchlist" :key="lists.id">
          <b-avatar
            variant="info"
            src="https://placekitten.com/300/300"
          ></b-avatar>
          <span style="font-weight: 600">{{ lists.name }} </span>
          <button id="btn-delete-friend">친구 신청</button>
        </div>
      </div>
    </b-modal>
  </div>
</template>

<script>
export default {
  name: "FriendAddModal",
  data: () => ({
    searchlist: [
      { id: 1, name: "김광배" },
      { id: 2, name: "이광배" },
      { id: 3, name: "차광배" },
    ],
  }),
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