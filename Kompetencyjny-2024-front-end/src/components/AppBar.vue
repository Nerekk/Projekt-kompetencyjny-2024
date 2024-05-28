<template>
  <v-app-bar class="bg-dark" :elevation="8" fixed app extension-height="45" extended>
    <v-app-bar-title class="font-weight-bold">Nazwa Aplikacji</v-app-bar-title>
    <v-spacer></v-spacer>
    <v-btn-group class="pr-6 gr-12">
      <v-btn v-for="(button, index) in topButtons" :key="button.id" variant="text" class="text-white">
        {{ button.name }} {{ index + 1 }}
      </v-btn>
      <v-btn v-if="!user" to="/login" rounded="xl" class="bg-darkGray">Zaloguj się</v-btn>
      <v-btn v-else rounded="xl" class="bg-darkGray" @click="logout()">Wyloguj się</v-btn>
      <div v-if="user" class="email">{{ user.email }}</div>
    </v-btn-group>
    <!-- <v-avatar class="mr-6">
      <v-img src="https://cdn.vuetifyjs.com/images/john.jpg"></v-img>
    </v-avatar> -->
    <template v-slot:extension>
      <div class="d-flex justify-space-between" style="width: 100%;">
        <v-btn v-for="(button, index) in downButtons" :key="button.id" variant="text" class="text-white flex-grow-1">
          {{ button.name }} {{ index + 1 }}
        </v-btn>
      </div>

    </template>
  </v-app-bar>
</template>
<script setup>
import { ref, computed } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { useSnack } from '@/composables/useSnack';

const { snackbarSuccess, snackbarError } = useSnack();
const store = useStore();
const router = useRouter();
const user = computed(() => store.state.user);
const logout = () => {
  store.dispatch('logoutUser')
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
const downButtons = ref([
  { id: 1, name: "Zakładka", },
  { id: 2, name: "Zakładka", },
  { id: 3, name: "Zakładka", },
  { id: 4, name: "Zakładka", },
  { id: 5, name: "Zakładka", },
  { id: 6, name: "Zakładka", },
]);





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