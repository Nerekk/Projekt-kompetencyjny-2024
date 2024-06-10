<template>
    <v-overlay class="centered-overlay" v-model="predictionStore.overlay" :persistent="canClose" :opacity="0.99">
        <div v-if="loading">
            <div class="text-gold text-h1">Obliczamy cenę...
                <v-progress-circular indeterminate :size="150" :width="10" color="gold" />
            </div>
        </div>
        <div v-else>
            <v-card class="pa-16">
    <v-card-text>
      <div class="font-weight-bold text-h3 text-center mb-6">Wycena</div>
      <div class="scrollable-list">
        <v-list lines="two">
          <v-list-item 
            v-for="(value, key) in filteredParameters" 
            :key="key"
          >
            <v-list-item-title>{{ translations[key] }}</v-list-item-title>
            <v-list-item-subtitle>{{ value }}</v-list-item-subtitle>
          </v-list-item>
        </v-list>
      </div>
      <div class="my-6">
        <MapOverlayComponent :latitude="predictionStore.parameters.latitude || 0" 
        :longitude="predictionStore.parameters.longitude || 0"  />
      </div>
      <div class="text-h4 text-center">Cena wynosi: {{ formattedPrice }} zł</div>
      <div class="text-center mt-6">
        <v-btn @click="predictionStore.resetParameters">Zamknij</v-btn>
      </div>
    </v-card-text>
  </v-card>
        </div>
    </v-overlay>
</template>

<script setup>
import { ref, watch, computed } from "vue";
import { usePredictionStore } from "@/store/PredictionStore";
import MapOverlayComponent from "@/components/MapOverlayComponent.vue";
const predictionStore = usePredictionStore();

const loading = ref(false);
const canClose = ref(true);

watch(() => predictionStore.overlay, (newVal) => {
    if (newVal) {
        loading.value = true;
        canClose.value = true;
    }
});
watch(() => predictionStore.predictedPrice, () => {
    loading.value = false;
    canClose.value = false;
});

const translations = {
  userId: 'ID użytkownika',
  city: 'Miasto',
  squareMeters: 'Metry kwadratowe',
  longitude: 'Długość geograficzna',
  latitude: 'Szerokość geograficzna',
  centreDistance: 'Odległość do centrum',
  floorCount: 'Liczba pięter',
  rooms: 'Liczba pokoi',
  kindergartenDistance: 'Odległość do przedszkola',
  restaurantDistance: 'Odległość do restauracji',
  collegeDistance: 'Odległość do uczelni',
  postOfficeDistance: 'Odległość do poczty',
  clinicDistance: 'Odległość do przychodni',
  schoolDistance: 'Odległość do szkoły',
  pharmacyDistance: 'Odległość do apteki',
  poiCount: 'Liczba punktów zainteresowania'
};
const filteredParameters = computed(() => {
  const params = predictionStore.parameters;
  const enabled = predictionStore.enabled;
  return Object.keys(params)
    .filter(key => params[key] !== 0 && params[key] !== null && enabled[key] !== false)
    .reduce((obj, key) => {
      obj[key] = params[key];
      return obj;
    }, {});
});
const formattedPrice = computed(() => {
  if (predictionStore.predictedPrice !== null && predictionStore.predictedPrice !== undefined) {
    return predictionStore.predictedPrice.toLocaleString('pl-PL');
  }
  return '';
});
</script>


<style scoped>
.centered-overlay {
    display: flex;
    justify-content: center;
    align-items: center;
}
.scrollable-list {
  max-height: 30vh;
  overflow-y: auto;
}
</style>
