<template>
 <v-app>
    <AppBar />
      <router-view />
    </v-app>
    <vue3-snackbar bottom right :duration="2500" groups></vue3-snackbar>

</template>

<script setup>
import AppBar from "@/components/AppBar.vue";
import { onMounted } from 'vue';
import { useAuthStore } from "./store/AuthStore";
import { Vue3Snackbar } from "vue3-snackbar";
import { useSnack } from '@/composables/useSnack';
const authStore = useAuthStore();
const { snackbarSuccess, snackbarError } = useSnack();
onMounted(async () => {
  if(localStorage.getItem('token') !== null) {
    await authStore.getUserByToken()
    .then(() => {
      snackbarSuccess('Zalogowano pomyślnie.')
    }).catch(() => {
      snackbarError('Twoja sesja wygasła. Zaloguj się ponownie.')
    })
  }
})
</script>

<style>
.v-btn {
  text-transform: unset !important;
}

* {
  font-family: poppins;
}
.text-h1, .text-h2, .text-h3, .text-h4, .text-h5 {
  font-family: 'Poppins', sans-serif !important;
}
</style>