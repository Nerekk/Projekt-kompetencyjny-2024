<template>
    <v-container class="pt-12" style="max-width: 75vw;">
      <v-row align="center" justify="space-between">
        <v-col cols="6">
          <div class="text-h3 font-weight-bold">Predykcje</div>
          <div class="caption">Wybierz model dostosowany do swoich potrzeb</div>
        </v-col>
        <v-col cols="6">
          <div class="d-flex justify-end">
            <v-btn-toggle v-model="selectedModel" mandatory>
              <v-btn value="minimal" @click="predictionStore.selectedModel='minimal'">Minimalny</v-btn>
              <v-btn value="simplified" @click="predictionStore.selectedModel='simplified'">Uproszczony</v-btn>
              <v-btn value="advanced" @click="predictionStore.selectedModel='advanced'">Zaawansowany</v-btn>
            </v-btn-toggle>
          </div>
        </v-col>
        <v-col>
          <component :is="selectedComponent"></component>
        </v-col>
      </v-row>
      <div class="text-h4 text-center py-5">Najczęściej wybierane parametry</div>
      <div class="d-flex justify-space-between text-center">
        <v-row>
          <v-col>
            <v-progress-circular :model-value="60" :rotate="320" :size="85" :width="15" color="gold">
              <template v-slot:default> 60 % </template>
            </v-progress-circular>
            <div class="caption">Uproszczony</div>
          </v-col>
          <v-col>
            <v-progress-circular :model-value="60" :rotate="360" :size="85" :width="15" color="gold">
              <template v-slot:default> 60 % </template>
            </v-progress-circular>
            <div class="caption">60 m2</div>
          </v-col>
          <v-col>
            <v-progress-circular :model-value="60" :rotate="180" :size="85" :width="15" color="gold">
              <template v-slot:default> 60 % </template>
            </v-progress-circular>
            <div class="caption">Warszawa</div>
          </v-col>
        </v-row>
      </div>
      <div class="mt-8 analysis-line text-center">
        <v-btn class="mt-n6" @click="handlePrediction">Rozpocznij analizę</v-btn>
      </div>
    </v-container>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue';
  // import { useAuthStore } from '@/store/AuthStore';
  import { usePredictionStore } from '@/store/PredictionStore';
  import { useSnack } from '@/composables/useSnack';
  import MinimalModel from './prediction-models/MinimalModel.vue';
  import SimplifiedModel from './prediction-models/SimplifiedModel.vue';
  import AdvancedModel from './prediction-models/AdvancedModel.vue';
  // const authStore = useAuthStore();
  const predictionStore = usePredictionStore();
  const { snackbarError } = useSnack();

  const selectedModel = ref('minimal')
  
  const selectedComponent = computed(() => {
    switch (selectedModel.value) {
      case 'simplified':
        return SimplifiedModel;
      case 'advanced':
        return AdvancedModel;
      default:
        return MinimalModel;
    }
  });
  const handlePrediction = () => {
    predictionStore.parameters.city = predictionStore.selectedCity;
  if (validateInputs()) {
    if(selectedModel.value === 'minimal')
        predictionStore.smallModelPredict();
    else if(selectedModel.value === 'simplified')
        predictionStore.mediumModelPredict();
    else if(selectedModel.value === 'advanced')
        predictionStore.bigModelPredict();
  } else {
    snackbarError("Proszę uzupełnić wszystkie wymagane pola.");
  }
};

const validateInputs = () => {
  const params = predictionStore.parameters;
  const requiredFields = {
    minimal: ['squareMeters', 'latitude', 'longitude', 'city'],
    simplified: ['squareMeters', 'rooms', 'floorCount', 'latitude', 'longitude', 'centreDistance', 'city'],
    advanced: ['latitude', 'longitude', 'city']
  };

  return requiredFields[selectedModel.value].every(field => {
    if (Array.isArray(params[field])) {
      return params[field].length > 0;
    }
    return params[field] !== null && params[field] !== '' && params[field] !== 0;
  });
};
  </script>
  
  <style scoped>
  .v-container {
    height: 95vh;
  }
  
  .caption {
    font-size: 16px;
    color: gray;
    margin-top: 4px;
  }
  
  .analysis-line {
    width: 100vw;
    height: 2px;
    background-color: black;
    left: 0;
    position: absolute;
  }
  </style>
  