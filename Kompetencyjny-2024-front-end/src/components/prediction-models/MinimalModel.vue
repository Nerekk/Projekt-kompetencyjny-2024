<template>
    <v-card class="bg-lightGray mx-auto pa-7" width="100%">
        <v-row no-gutters>
            <v-col cols="12" md="6" class="py-5 pr-md-3">
                <div class="text-h3 pb-2">Model minimalny</div>
                <div class="caption text-white">
                    Potrzebujemy kilku podstawowych
                    informacji aby przeanalizować
                    rynek pod kątem twoich potrzeb.
                </div>
            </v-col>
            <v-col cols="12" md="6" class="pl-md-3">
                <v-select v-model="predictionStore.selectedCity" :items="cities" class="my-5"></v-select>
            </v-col>
        </v-row>
        <v-row no-gutters>
            <v-col cols="12" md="6" class="order-md-1 order-2 pr-md-3 pr-0">
                <v-tooltip text="Te dane wypełnią się po wybraniu punktu na mapie" location="top">
                    <template v-slot:activator="{ props }">
                        <v-text-field v-bind="props" v-model="predictionStore.parameters.longitude"
                            label="Długość geograficzna" readonly />
                        <v-text-field v-bind="props" v-model="predictionStore.parameters.latitude"
                            label="Szerokość geograficzna" readonly />
                    </template>
                </v-tooltip>
                <v-row>
                    <v-col cols="12" md="9">
                        <v-slider v-model="predictionStore.parameters.squareMeters" label="Metry kwadratowe" min="0" location="top"
                            max="300" step="1" class="mt-4" />
                    </v-col>
                    <v-col cols="12" md="3">
                        <v-text-field v-model="predictionStore.parameters.squareMeters" label="" hide-details
                            type="number" suffix="m²" class="no-arrows"/>
                    </v-col>
                </v-row>
            </v-col>
            <v-col cols="12" md="6" class="order-md-2 order-1 pl-md-3 map-column">
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

<style scoped>
    @media (max-width: 600px) {
        .text-h3{
        font-size: 38px !important;
        }
    
        .caption{
        font-size: 14px;
        }

        .map-column{
            padding-bottom: 20px !important;
        }
    }
    
</style>