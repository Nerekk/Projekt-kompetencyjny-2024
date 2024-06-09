<template>
  <v-app-bar class="bg-dark" :elevation="8" fixed app>
    <v-app-bar-title class="font-weight-bold">PrediHome</v-app-bar-title>
    <v-spacer></v-spacer>
    <v-btn-group class="pr-6 gr-12">
      <v-btn v-for="(button, index) in topButtons" :key="button.id" variant="text" class="text-white">
        {{ button.name }} {{ index + 1 }}
      </v-btn>
      <v-btn v-if="!user" to="/login" rounded="xl" class="bg-darkGray">Zaloguj się</v-btn>
      <v-btn v-else rounded="xl" class="bg-darkGray" @click="logout()">Wyloguj się</v-btn>
      <div v-if="user" class="email">{{ user.email }}</div>
    </v-btn-group>
  </v-app-bar>
</template>
<script setup>
import { ref, computed } from "vue";
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
const topButtons = ref([
  { id: 1, name: "Opcja", },
  { id: 2, name: "Opcja", },
  { id: 3, name: "Opcja", },
]);
// const downButtons = ref([
//   { id: 1, name: "Zakładka", },
//   { id: 2, name: "Zakładka", },
//   { id: 3, name: "Zakładka", },
//   { id: 4, name: "Zakładka", },
//   { id: 5, name: "Zakładka", },
//   { id: 6, name: "Zakładka", },
// ]);





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