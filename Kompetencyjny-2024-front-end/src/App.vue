<template>
  <v-app>
  <AppBar />
  <router-view />
</v-app>
</template>

<script>
import AppBar from "./components/AppBar.vue";
export default {
  name: "App",
  components: {
    AppBar,
  },
};
</script>

<style>
.v-btn {
    text-transform: unset !important;
}
</style>