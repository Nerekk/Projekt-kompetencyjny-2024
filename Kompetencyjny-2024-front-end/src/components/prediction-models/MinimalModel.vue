<template>
    <v-card class="bg-lightGray mx-auto" height="50vh" width="100%">
        <v-row>
            <v-col cols="6" class="px-12 py-12">
                <div class="text-h3">Model minimalny</div>
                <div class="caption text-white">
                    Potrzebujemy kilku podstawowych
                    informacji aby przeanalizować
                    rynek pod kątem twoich potrzeb.
                </div>
            </v-col>
            <v-col cols="6" class="py-3">
                <v-select v-model="predictionStore.selectedCity" :items="cities" class="mx-12 my-12"></v-select>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="6" class="pt-8 py-3 pl-6">
                <v-tooltip text="Te dane wypełnią się po wybraniu punktu na mapie" location="top">
                    <template v-slot:activator="{ props }">
                        <v-text-field v-bind="props" v-model="predictionStore.parameters.longitude"
                            label="Długość geograficzna" readonly />
                        <v-text-field v-bind="props" v-model="predictionStore.parameters.latitude"
                            label="Szerokość geograficzna" readonly />
                    </template>
                </v-tooltip>
                <v-row>
                    <v-col cols="9">
                        <v-slider v-model="predictionStore.parameters.squareMeters" label="Metry kwadratowe" min="0"
                            max="300" step="1" class="mt-4" />
                    </v-col>
                    <v-col cols="3">
                        <v-text-field v-model="predictionStore.parameters.squareMeters" label="" hide-details
                            type="number" suffix="m²" />
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="6" class="pr-6">
                <MapComponent :height="250" />
            </v-col>
        </v-row>
    </v-card>
</template>

<script setup>
import { usePredictionStore } from '@/store/PredictionStore';
import { useValidation } from '@/composables/useValidation';
import MapComponent from '@/components/MapComponent.vue';
import { useCityList } from '@/composables/cityList';

const { cities } = useCityList();
const predictionStore = usePredictionStore();

const validationRules = {
    squareMeters: { min: 0, max: 300 },
};

useValidation(validationRules);

</script>

<style scoped></style>