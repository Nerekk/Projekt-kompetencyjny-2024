<template>
    <v-overlay class="centered-overlay" v-model="store.state.overlay" :persistent="canClose" :opacity="0.99">
        <div v-if="loading">
            <div class="text-gold text-h1">Obliczamy cenę...
                <v-progress-circular indeterminate :size="150" :width="10" color="gold" />
            </div>
        </div>
        <div v-else>
            <v-card class="pa-16">

                <v-card-text>
                    <div class="font-weight-bold text-h3 text-center mb-6">Wycena</div>
                    <v-list lines="two">
                        <v-list-item v-for="n in 3" :key="n" :title="'Informacja ' + n"
                            subtitle="Łódź lub inne kryterium"></v-list-item>
                    </v-list>
                    <div class="text-h4 text-center">Cena wynosi: 1000 zł</div>
                    <div class="text-center mt-6">
                        <v-btn @click="store.state.overlay = false">Zamknij</v-btn>
                    </div>
                </v-card-text>
            </v-card>
        </div>
    </v-overlay>
</template>

<script setup>
import { ref } from "vue";
import { useStore } from "vuex";
const store = useStore();

const loading = ref(false);
const canClose = ref(true);

store.watch(() => store.state.overlay, () => {
    if (store.state.overlay) {
        loading.value = true;
        canClose.value = true;
    }
    setTimeout(() => {
        loading.value = false;
        canClose.value = false;
    }, 3000);
});
</script>

<style scoped>
.centered-overlay {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
