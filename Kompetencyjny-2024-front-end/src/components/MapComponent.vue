<template>
    <div ref="mapContainer" :style="{ width: 'auto', height: props.height + 'px' }"></div>
</template>

<script setup>
import { usePredictionStore } from '@/store/PredictionStore';
import { ref, onMounted, watch } from 'vue';
import { useCityList } from '@/composables/cityList';
import L from 'leaflet';
const { cities } = useCityList();

const predictionStore = usePredictionStore();
const map = ref();
const mapContainer = ref();

const props = defineProps({
    height: {
        type: Number,
        required: true,
        default: 250
    }
});

onMounted(() => {
    if (predictionStore.parameters.latitude && predictionStore.parameters.longitude) {
        map.value = L.map(mapContainer.value).setView([predictionStore.parameters.latitude, predictionStore.parameters.longitude], predictionStore.currentMapZoom);
        currentMarker.value = L.marker([predictionStore.parameters.latitude, predictionStore.parameters.longitude]).addTo(map.value);
    } else if (predictionStore.selectedCity) {
        const city = cities.value.find(item => item.title === predictionStore.selectedCity);
        map.value = L.map(mapContainer.value).setView(city.coords, 12);
        predictionStore.currentMapZoom = 12;
    }
    else {
        map.value = L.map(mapContainer.value).setView([52.2297, 21.0122], 12);
        predictionStore.currentMapZoom = 12;
    }
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map.value);

    map.value.on('click', (e) => {
        clickedPosition.value.lat = e.latlng.lat;
        clickedPosition.value.lng = e.latlng.lng;
        predictionStore.parameters.latitude = e.latlng.lat;
        predictionStore.parameters.longitude = e.latlng.lng;

        if (currentMarker.value) {
            map.value.removeLayer(currentMarker.value);
        }
        currentMarker.value = L.marker([clickedPosition.value.lat, clickedPosition.value.lng]).addTo(map.value);
    });
    map.value.on('zoomend', () => {
        predictionStore.currentMapZoom = map.value.getZoom();
    });
});

const clickedPosition = ref({ lat: null, lng: null });
const currentMarker = ref(null);

watch(() => predictionStore.selectedCity, (newVal) => {
    const city = cities.value.find(item => item.title === newVal);
    if (city) {
        map.value.setView(city.coords, 12);
        predictionStore.currentMapZoom = 12;
    }
});
</script>

<style scoped></style>