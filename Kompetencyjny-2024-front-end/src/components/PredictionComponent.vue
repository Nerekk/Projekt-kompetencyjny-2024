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
                            <v-text-field label="Długość geograficzna"></v-text-field>
                            <v-text-field label="Szerokość geograficzna"></v-text-field>
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
        <div class="text-center"><v-btn>Rozpocznij analizę</v-btn></div>
    </v-container>
</template>

<script>
import { ref, onMounted } from 'vue';
import L from 'leaflet';
export default {
    name: "PredictionComponent",
    setup() {
        const map = ref();
        const mapContainer = ref();
        onMounted(() => {
            map.value = L.map(mapContainer.value).setView([52.2297, 21.0122], 10);
            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 19,
                attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
            }).addTo(map.value);
        });
        const items = ref([
            { title: 'Click Me' },
            { title: 'Click Me' },
            { title: 'Click Me' },
            { title: 'Click Me 2' },
        ]);
        const selected = ref("Miasto");
        return {
            items,
            selected,
            mapContainer
        };
    },
};
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
</style>