<template>
  <v-main>
  <div class="bg" >
    <div class="text-h3 text-white text-center pt-2">Kupuj, planuj, inwestuj...</div>
    <div class="text-h2 font-weight-bold text-white text-center pt-6">Nazwa Aplikacji</div>

  </div>
  <div class="prediction-section">
    <PredictionComponent />
  </div>
  <overlayPrice />
  </v-main>
</template>

<script setup>
import PredictionComponent from "@/components/PredictionComponent.vue";
import overlayPrice from "@/components/overlayPrice.vue";
</script>

<style scoped>
.bg {
  background-image: url("@/assets/background.png");
  height: 100vh;
  background-color: #7c847f;
  background-position: top 100px center;
  background-repeat: no-repeat;
  background-size: cover;
}
</style>
