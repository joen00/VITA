<template>
    <v-app>
        <header-view></header-view>
        <v-main>
            <router-view />
        </v-main>
    </v-app>
</template>

<script>
import HeaderView from "@/views/HeaderView.vue";
export default {
  name: "App",
  components: {
    HeaderView,
  },
  data: () => ({
    //
  }),
};
</script>
<style>

</style>