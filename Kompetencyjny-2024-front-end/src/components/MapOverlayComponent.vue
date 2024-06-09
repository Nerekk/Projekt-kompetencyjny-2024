<template>
    <div ref="mapContainer" style="width: auto; height: 250px;"></div>
  </template>
  
  <script setup>
  import { ref, onMounted, watch } from 'vue';
  import L from 'leaflet';
  
  const props = defineProps({
    latitude: {
      type: Number,
      required: true,
      default: 0
    },
    longitude: {
      type: Number,
      required: true,
      default: 0
    }
  });
  
  const map = ref();
  const mapContainer = ref();
  const currentMarker = ref(null);
  
  onMounted(() => {
    map.value = L.map(mapContainer.value).setView([props.latitude, props.longitude], 17);
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map.value);
  
    currentMarker.value = L.marker([props.latitude, props.longitude]).addTo(map.value);
  });
  
  watch(() => [props.latitude, props.longitude], ([newLat, newLng]) => {
    if (map.value) {
      map.value.setView([newLat, newLng], 17);
      if (currentMarker.value) {
        map.value.removeLayer(currentMarker.value);
      }
      currentMarker.value = L.marker([newLat, newLng]).addTo(map.value);
    }
  });
  </script>
  
  <style scoped></style>
  