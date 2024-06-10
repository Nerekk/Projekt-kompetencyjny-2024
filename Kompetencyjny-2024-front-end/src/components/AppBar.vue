<template>
  <v-app-bar class="bg-dark" :elevation="8" fixed app>
    <v-app-bar-title @click="router.push('/')" class="font-weight-bold">PrediHome</v-app-bar-title>
    <v-spacer></v-spacer>
      <div class="mx-3">
        <v-btn v-if="user" to="/user-history" class="bg-darkGray mr-4">Historia predykcji</v-btn>
      <v-btn v-if="!user" to="/login" class="bg-darkGray">Zaloguj się</v-btn>
      <v-btn v-else class="bg-darkGray" @click="logout()">Wyloguj się</v-btn>
      </div>
    <div v-if="user" class="email pr-6">{{ user.email }}</div>
  </v-app-bar>
</template>
<script setup>
import { computed } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/store/AuthStore";
import { useSnack } from '@/composables/useSnack';

const { snackbarSuccess, snackbarError } = useSnack();
const authStore = useAuthStore();
const router = useRouter();
const user = computed(() => authStore.user);
const logout = async () => {
  await authStore.logoutUser()
    .then(() => {
      snackbarSuccess('Wylogowano pomyślnie.')
    })
    .catch(() => {
      snackbarError('Wystąpił błąd podczas wylogowywania.')
    });
  router.push('/')
}






</script>



<style scoped>
.v-toolbar {
  font-family: poppins;
}

.v-toolbar-title {
  font-size: 24px;
}

.email {
  color: #ffffff !important;
}
</style>