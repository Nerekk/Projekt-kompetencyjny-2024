<template>
   <v-navigation-drawer app v-model="drawer" temporary color="dark" location="top">
      <v-list dense>
        <v-list-item @click="router.push('/')">
          <v-list-item-title class="font-weight-bold">PrediHome</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="user">
          <v-list-item-title>{{ user.email }}</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="user" @click="router.push('/user-history')">
          <v-list-item-title>Historia predykcji</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="!user" @click="router.push('/login')">
          <v-list-item-title>Zaloguj się</v-list-item-title>
        </v-list-item>
        <v-list-item v-else @click="logout">
          <v-list-item-title>Wyloguj się</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar app color="dark" dark>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer" v-if="isMobile"></v-app-bar-nav-icon>
      <v-app-bar-title @click="router.push('/')" class="font-weight-bold">PrediHome</v-app-bar-title>
      <v-spacer></v-spacer>
      <div class="mx-3" v-if="!isMobile">
        <v-btn v-if="user" to="/user-history" class="bg-darkGray mr-4">Historia predykcji</v-btn>
        <v-btn v-if="!user" to="/login" class="bg-darkGray">Zaloguj się</v-btn>
        <v-btn v-else class="bg-darkGray" @click="logout()">Wyloguj się</v-btn>
      </div>
      <div v-if="user && !isMobile" class="email pr-6">{{ user.email }}</div>
    </v-app-bar>
</template>
<script setup>
import { computed } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/store/AuthStore";
import { useSnack } from '@/composables/useSnack';
import { ref, onMounted, onBeforeUnmount } from 'vue';

const drawer = ref(false);
const isMobile = ref(false);
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

const checkIfMobile = () => {
  isMobile.value = window.innerWidth < 600;
};

onMounted(() => {
  checkIfMobile();
  window.addEventListener('resize', checkIfMobile);
});

onBeforeUnmount(() => {
  window.removeEventListener('resize', checkIfMobile);
});
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