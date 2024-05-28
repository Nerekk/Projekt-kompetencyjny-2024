<template>
    <v-container class="pt-12">
        <v-row align="center" justify="space-between" class="px-12">
            <v-col cols="6">
                <div class="text-h3">Predykcje</div>
                <div class="caption">Wybierz model dostosowany do swoich potrzeb</div>
            </v-col>
            <v-col cols="6">
                <div class="d-flex justify-space-between mr-n12">
                    <v-btn> Zaawansowany </v-btn>
                    <v-btn> Uproszczony </v-btn>
                    <v-btn> Minimalny </v-btn>
                </div>
            </v-col>
            <v-col>
                <v-card class="bg-lightGray mx-auto" height="50vh" width="75vw">
                    <v-row>
                        <v-col cols="6" class="px-12 py-12">
                            <div class="text-h3">Model uproszczony</div>
                            <div class="caption text-white">
                                Potrzebujemy kilku podstawowych
                                informacji aby przeanalizować
                                rynek pod kątem twoich potrzeb
                            </div>

                        </v-col>
                        <v-col cols="6" class="py-3">
                            <v-select v-model="selected" :items="items" class="mx-12 my-12"></v-select>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="6" class="pt-8 py-3">
                            <v-text-field v-model="clickedPosition.lat" label="Długość geograficzna"></v-text-field>
                            <v-text-field v-model="clickedPosition.lng" label="Szerokość geograficzna"></v-text-field>
                            <v-text-field label="Metry kwadratowe"></v-text-field>
                        </v-col>
                        <v-col cols="6" class=" pr-6">
                            <div ref="mapContainer" style="width: auto; height: 250px;"></div>
                        </v-col>
                    </v-row>
                </v-card>
            </v-col>
        </v-row>
        <div class="text-h4 text-center py-2">Najczęściej wybierane parametry</div>
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
        <div class="mt-8 analysis-line text-center"><v-btn class="mt-n6" @click="store.dispatch('showOverlay')">Rozpocznij analizę</v-btn></div>
    </v-container>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useStore } from 'vuex';
import L from 'leaflet';

const store = useStore();
const map = ref();
const mapContainer = ref();
onMounted(() => {
    map.value = L.map(mapContainer.value).setView([52.2297, 21.0122], 10);
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map.value);

    map.value.on('click', (e) => {
        clickedPosition.value.lat = e.latlng.lat;
        clickedPosition.value.lng = e.latlng.lng;
        console.log('Clicked position:', clickedPosition.value);

        if (currentMarker.value) {
            map.value.removeLayer(currentMarker.value);
        }

        currentMarker.value= L.marker([clickedPosition.value.lat, clickedPosition.value.lng]).addTo(map.value);
    });
});

const clickedPosition = ref({ lat: null, lng: null });
const currentMarker = ref(null);
const items = ref([
    { title: 'Łódź', coords: [51.7592, 19.4560] },
    { title: 'Warszawa', coords: [52.2297, 21.0122] },
    { title: 'Gdańsk', coords: [54.3520, 18.6466] },
    { title: 'Poznań', coords: [52.4064, 16.9252] },
]);
const selected = ref("Miasto");


watch(selected, (newVal) => {
    const city = items.value.find(item => item.title === newVal);
    if (city) {
        map.value.setView(city.coords, 12);
    }
});
</script>

<style scoped>
.v-container {
    /* background-color: red; */
    height: 90vh;
}

.caption {
    font-size: 12px;
    color: gray;
    margin-top: 4px;
}
.analysis-line {
  width: 100vw; /* Długość linii */
  height: 2px; /* Grubość linii */
  background-color: black; /* Czarny kolor */
  left: 0; /* Umieszczenie na lewej krawędzi ekranu */
  position: absolute;
}
</style>