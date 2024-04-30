<template>
  <v-layout>
    <AppBar />
    <v-main>
      <router-view />
    </v-main>
  </v-layout>


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

* {
  font-family: poppins;
}

</style>