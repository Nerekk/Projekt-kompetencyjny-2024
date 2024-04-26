<template>
  <v-toolbar class="bg-dark" :elevation="8" app extension-height="45" extended>
    <v-toolbar-title class="font-weight-bold">Nazwa Aplikacji</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-btn-group class="pr-6 gr-12">
      <v-btn v-for="(button, index) in topButtons" :key="button.id" variant="text" class="text-white">
        {{ button.name }} {{ index + 1 }}
      </v-btn>
      <v-btn rounded="xl" class="bg-darkGray">Zaloguj się</v-btn>
    </v-btn-group>
    <v-avatar class="mr-6">
      <v-img src="https://cdn.vuetifyjs.com/images/john.jpg"></v-img>
    </v-avatar>
    <template v-slot:extension>
      <div class="d-flex justify-space-between" style="width: 100%;">
        <v-btn v-for="(button, index) in downButtons" :key="button.id" variant="text" class="text-white flex-grow-1">
          {{ button.name }} {{ index + 1 }}
        </v-btn>
      </div>

    </template>
  </v-toolbar>
</template>
<script>
import { ref } from "vue";
export default {
  name: "AppBar",
  setup() {
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


    return {
      topButtons,
      downButtons
    }

  }
};
</script>



<style scoped>
.v-toolbar {
  font-family: poppins;
}

.v-toolbar-title {
  font-size: 24px;
}
</style>