<template>
  <div class="bg" ref="bgRef">
    <!-- <v-img src="@/assets/background.png" ></v-img> -->
    <div class="text-h3 text-white text-center pt-2">Kupuj, planuj, inwestuj...</div>
    <div class="text-h2 font-weight-bold text-white text-center pt-6">Nazwa Aplikacji</div>
  </div>
  <div class="prediction-section" ref="predictionSection">
    <PredictionComponent />
  </div>
</template>

<script>
import { onMounted, onUnmounted, ref } from 'vue';
import PredictionComponent from "@/components/PredictionComponent.vue";

export default {
  name: "HomeView",
  components: {
    PredictionComponent,
  },
  setup() {
    const bgRef = ref(null);
    const predictionSection = ref(null);

    const onScroll = () => {
      // console.log(window.scrollY);
      // scroll to prediction component
    };

    onMounted(() => {
      window.addEventListener("scroll", onScroll);
    });

    onUnmounted(() => {
      window.removeEventListener("scroll", onScroll);
    });

    return { bgRef, predictionSection };
  },
};
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
