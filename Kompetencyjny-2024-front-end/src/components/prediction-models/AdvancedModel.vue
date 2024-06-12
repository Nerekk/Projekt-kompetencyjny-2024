<template>
  <v-card class="bg-lightGray mx-auto pa-7" width="100%">
    <v-row no-gutters>
      <v-col cols="12" md="6" class="py-5 pr-md-3">
        <div class="text-h3 pb-2">Model zaawansowany</div>
        <div class="caption text-white">
          Najbardziej zaawansowany model, który pozwala na uzyskanie najbardziej precyzyjnych prognoz.
        </div>
        <div class="caption text-white font-weight-bold">
          Nie trzeba podawać wszystkich danych, ale im więcej informacji, tym dokładniejsze wyniki.
        </div>
      </v-col>
      <v-col cols="12" md="6" class="pl-md-3">
        <v-select v-model="predictionStore.selectedCity" :items="cities" class="my-5"></v-select>
      </v-col>
    </v-row>

    <v-row no-gutters>
      <v-col cols="12" md="6" class="pr-md-3 order-md-1 order-2">
        <v-tooltip text="Te dane wypełnią się po wybraniu punktu na mapie" location="top">
          <template v-slot:activator="{ props }">
            <v-text-field v-bind="props" v-model="predictionStore.parameters.longitude" label="Długość geograficzna"
              readonly />
            <v-text-field v-bind="props" v-model="predictionStore.parameters.latitude" label="Szerokość geograficzna"
              readonly />
          </template>
        </v-tooltip>
        <v-row>
          <v-col cols="1" >
            <v-checkbox v-model="predictionStore.enabled.squareMeters" class="mt-1"/>
          </v-col>
          <v-col cols="11" md="8">
            <v-slider :disabled="!predictionStore.enabled.squareMeters" v-model="predictionStore.parameters.squareMeters"
              label="Metry kwadratowe" min="0" max="300" step="1" class="mt-4" />
          </v-col>
          <v-col cols="12" md="3">
            <v-text-field :disabled="!predictionStore.enabled.squareMeters" v-model="predictionStore.parameters.squareMeters"
              hide-details type="number" suffix="m²" />
          </v-col>
        </v-row>

        <v-row class="mt-n9">
          <v-col cols="1">
            <v-checkbox v-model="predictionStore.enabled.rooms" class="mt-3"/>
          </v-col>
          <v-col cols="11">
            <v-slider :disabled="!predictionStore.enabled.rooms" v-model="predictionStore.parameters.rooms" label="Ilość pokoi" min="1" max="6" step="1"
              class="mt-6" show-ticks thumb-label="always" />
          </v-col>
        </v-row>

        <v-row class="mt-n9">
          <v-col cols="1">
            <v-checkbox v-model="predictionStore.enabled.floorCount" class="mt-3"/>
          </v-col>
          <v-col cols="11">
          <v-slider :disabled="!predictionStore.enabled.floorCount" v-model="predictionStore.parameters.floorCount" label="Ilość pięter" min="1" max="29" step="1"
            class="mt-6" show-ticks thumb-label="always" />
          </v-col>
        </v-row>

        <v-row class="mt-n9">
          <v-col cols="1">
            <v-checkbox v-model="predictionStore.enabled.centreDistance" class="mt-1"/>
          </v-col>
          <v-col cols="11" md="8">
            <v-slider :disabled="!predictionStore.enabled.centreDistance" v-model="predictionStore.parameters.centreDistance" label="Odl. od centrum" min="0.0" max="17.0"
              step="0.5" class="mt-4" />
          </v-col>
          <v-col cols="12" md="3">
            <v-text-field :disabled="!predictionStore.enabled.centreDistance" v-model="predictionStore.parameters.centreDistance" hide-details type="number" suffix="km" />
          </v-col>
        </v-row>
      </v-col>

      <v-col cols="12" md="6" class="pl-md-3 order-md-2 order-1 map-column">
        <MapComponent :height="475" />
      </v-col>

      <v-col cols="12" md="12" class="mt-4 order-md-3 order-3">
        <v-row>
          <v-col cols="12" md="6">
            <v-row class="mt-n6">
              <v-col cols="1">
              <v-checkbox v-model="predictionStore.enabled.kindergartenDistance" class="mt-1"/>
              </v-col>
              <v-col cols="11" md="8">
                <v-slider :disabled="!predictionStore.enabled.kindergartenDistance" v-model="predictionStore.parameters.kindergartenDistance" label="Odl. od przedszkola"
                  min="0.0" max="5.0" step="0.5" class="mt-4" />
              </v-col>
              <v-col cols="12" md="3">
                <v-text-field :disabled="!predictionStore.enabled.kindergartenDistance" v-model="predictionStore.parameters.kindergartenDistance" hide-details type="number"
                  suffix="km" />
              </v-col>
            </v-row>
          </v-col>

          <v-col cols="12" md="6">
            <v-row class="mt-n6">
              <v-col cols="1">
                <v-checkbox v-model="predictionStore.enabled.schoolDistance" class="mt-1"/>
              </v-col>
              <v-col cols="11" md="8">
                <v-slider :disabled="!predictionStore.enabled.schoolDistance" v-model="predictionStore.parameters.schoolDistance" label="Odl. od szkoły" min="0.0" max="5.0"
                  step="0.5" class="mt-4" />
              </v-col>
              <v-col cols="12" md="3">
                <v-text-field :disabled="!predictionStore.enabled.schoolDistance" v-model="predictionStore.parameters.schoolDistance" hide-details type="number"
                  suffix="km" />
              </v-col>
            </v-row>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12" md="6">
            <v-row class="mt-n6">
              <v-col cols="1">
                <v-checkbox v-model="predictionStore.enabled.collegeDistance" class="mt-1"/>
              </v-col>
              <v-col cols="11" md="8">
                <v-slider :disabled="!predictionStore.enabled.collegeDistance" v-model="predictionStore.parameters.collegeDistance" label="Odl. od uniwersytetu" min="0.0"
                  max="5.0" step="0.5" class="mt-4" />
              </v-col>
              <v-col cols="12" md="3">
                <v-text-field :disabled="!predictionStore.enabled.collegeDistance" v-model="predictionStore.parameters.collegeDistance" hide-details type="number"
                  suffix="km" />
              </v-col>
            </v-row>
          </v-col>

          <v-col cols="12" md="6">
            <v-row class="mt-n6">
              <v-col cols="1">
                <v-checkbox v-model="predictionStore.enabled.postOfficeDistance" class="mt-1"/>
              </v-col>
              <v-col cols="11" md="8">
                <v-slider :disabled="!predictionStore.enabled.postOfficeDistance" v-model="predictionStore.parameters.postOfficeDistance" label="Odl. od poczty" min="0.0"
                  max="5.0" step="0.5" class="mt-4" />
              </v-col>
              <v-col cols="12" md="3">
                <v-text-field :disabled="!predictionStore.enabled.postOfficeDistance" v-model="predictionStore.parameters.postOfficeDistance" hide-details type="number"
                  suffix="km" />
              </v-col>
            </v-row>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12" md="6">
            <v-row class="mt-n6">
              <v-col cols="1">
            <v-checkbox v-model="predictionStore.enabled.clinicDistance" class="mt-1"/>
          </v-col>
              <v-col cols="11" md="8">
                <v-slider :disabled="!predictionStore.enabled.clinicDistance" v-model="predictionStore.parameters.clinicDistance" label="Odl. od przychodni" min="0.0"
                  max="5.0" step="0.5" class="mt-4" />
              </v-col>
              <v-col cols="12" md="3">
                <v-text-field :disabled="!predictionStore.enabled.clinicDistance" v-model="predictionStore.parameters.clinicDistance" hide-details type="number"
                  suffix="km" />
              </v-col>
            </v-row>
          </v-col>

          <v-col cols="12" md="6">
            <v-row class="mt-n6">
              <v-col cols="1">
                <v-checkbox v-model="predictionStore.enabled.pharmacyDistance" class="mt-1"/>
              </v-col>
              <v-col cols="11" md="8">
                <v-slider :disabled="!predictionStore.enabled.pharmacyDistance" v-model="predictionStore.parameters.pharmacyDistance" label="Odl. od apteki" min="0.0"
                  max="5.0" step="0.5" class="mt-4" />
              </v-col>
              <v-col cols="12" md="3">
                <v-text-field :disabled="!predictionStore.enabled.pharmacyDistance" v-model="predictionStore.parameters.pharmacyDistance" hide-details type="number"
                  suffix="km" />
              </v-col>
            </v-row>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12" md="6">
            <v-row class="mt-n6">
              <v-col cols="1">
                <v-checkbox v-model="predictionStore.enabled.restaurantDistance" class="mt-1"/>
              </v-col>
              <v-col cols="11" md="8">
                <v-slider :disabled="!predictionStore.enabled.restaurantDistance" v-model="predictionStore.parameters.restaurantDistance" label="Odl. od restauracji" min="0.0"
                  max="5.0" step="0.5" class="mt-4" />
              </v-col>
              <v-col cols="12" md="3">
                <v-text-field :disabled="!predictionStore.enabled.restaurantDistance" v-model="predictionStore.parameters.restaurantDistance" hide-details type="number"
                  suffix="km" />
              </v-col>
            </v-row>
          </v-col>

          <v-col cols="12" md="6">
            <v-row class="mt-n6">
              <v-col cols="1">
                <v-checkbox v-model="predictionStore.enabled.poiCount" class="mt-1"/>
              </v-col>
              <v-col cols="11" md="8">
                <v-slider :disabled="!predictionStore.enabled.poiCount" v-model="predictionStore.parameters.poiCount" label="Il. punktów zainteresowania" min="0"
                  max="210" step="1" class="mt-4" />
              </v-col>
              <v-col cols="12" md="3">
                <v-text-field :disabled="!predictionStore.enabled.poiCount" v-model="predictionStore.parameters.poiCount" hide-details type="number" />
              </v-col>
            </v-row>
          </v-col>
        </v-row>
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
  centreDistance: { min: 0, max: 17 },
  kindergartenDistance: { min: 0, max: 5 },
  schoolDistance: { min: 0, max: 5 },
  collegeDistance: { min: 0, max: 5 },
  clinicDistance: { min: 0, max: 5 },
  pharmacyDistance: { min: 0, max: 5 },
  postOfficeDistance: { min: 0, max: 5 },
  restaurantDistance: { min: 0, max: 5 },
  poiCount: { min: 0, max: 210 }
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