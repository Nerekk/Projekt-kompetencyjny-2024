<template>
    <v-main>
      <v-card class="flex-1-0 ma-16 pa-2" :elevation="24" rounded>
        <v-card-title>
          <div class="text-h3 text-center font-weight-bold">
            Szczegółowa historia predykcji
          </div>
        </v-card-title>
        <v-card-text v-if="historyStore.paginatedHistory.length !== 0">
          <div v-if="historyStore.paginatedHistory[currentPage - 1] === undefined">
            <v-skeleton-loader type="list-item, list-item, list-item"></v-skeleton-loader>
          </div>
          <div v-if="historyStore.paginatedHistory[0].length === 0">
            <div class="text-center text-h4 mt-4">
              Brak historii do wyświetlenia.
            </div>
          </div>
          <v-row>
            <v-col cols="4">
              <v-expansion-panels
                variant="accordion"
                v-model="openedPanels"
                v-if="historyStore.paginatedHistory[0].length !== 0"
              >
                <v-expansion-panel
                  v-for="history in currentPaginatedHistory"
                  :key="history.model_id"
                >
                  <v-expansion-panel-title>
                    {{ history.predictionDate.substring(0, history.predictionDate.lastIndexOf(',')) }} -
                    {{ history.city }}
                  </v-expansion-panel-title>
                  <v-expansion-panel-text>
                    <div class="font-weight-bold">Model: {{ history.modelType }}</div>
                    <div v-if="history.modelType === 'Minimalny'">
                      <div>Parametry:</div>
                      <ul class="ml-6">
                      <li>Metraż: {{ history.squareMeters }} m2</li>
                      <li>Miasto: {{ history.city }}</li>
                    </ul>
                      <div>Cena: {{ history.predictedValue }} zł</div>
                    </div>
                    <div v-else-if="history.modelType === 'Uproszczony'">
                      <div>Parametry:</div>
                      <ul class="ml-6">
                      <li>Metraż: {{ history.squareMeters }} m2</li>
                      <li>Miasto: {{ history.city }}</li>
                      <li>Liczba pokoi: {{ history.rooms }}</li>
                      <li>Ilość pięter: {{ history.floorCount }}</li>
                      <li>Odległość od centrum: {{ history.centreDistance }} km</li>
                    </ul>
                      <div>Cena: {{ history.predictedValue }} zł</div>
                    </div>
                    <div v-else>
      <div>Parametry:</div>
      <ul class="ml-6">
        <li>Metraż: {{ history.squareMeters !== 1 ? history.squareMeters + ' m2' : '1 m2 lub nie podano' }}</li>
        <li>Miasto: {{ history.city || 'nie podano' }}</li>
        <li>Liczba pokoi: {{ history.rooms !== 1 ? history.rooms : '1 lub nie podano' }}</li>
        <li>Ilość pięter: {{ history.floorCount !== 1 ? history.floorCount : '1 lub nie podano' }}</li>
        <li>Odległość od centrum: {{ history.centreDistance ? history.centreDistance + ' km' : '0 km lub nie podano' }}</li>
        <li>Odległość do przedszkola: {{ history.kindergartenDistance ? history.kindergartenDistance + ' km' : '0 km lub nie podano' }}</li>
        <li>Odległość do restauracji: {{ history.restaurantDistance ? history.restaurantDistance + ' km' : '0 km lub nie podano' }}</li>
        <li>Odległość do uczelni: {{ history.collegeDistance ? history.collegeDistance + ' km' : '0 km lub nie podano' }}</li>
        <li>Odległość do poczty: {{ history.postOfficeDistance ? history.postOfficeDistance + ' km' : '0 km lub nie podano' }}</li>
        <li>Odległość do kliniki: {{ history.clinicDistance ? history.clinicDistance + ' km' : '0 km lub nie podano' }}</li>
        <li>Odległość do szkoły: {{ history.schoolDistance ? history.schoolDistance + ' km' : '0 km lub nie podano' }}</li>
        <li>Odległość do apteki: {{ history.pharmacyDistance ? history.pharmacyDistance + ' km' : '0 km lub nie podano' }}</li>
        <li>Liczba punktów zainteresowania: {{ history.poiCount || '0 lub nie podano' }}</li>
      </ul>

      <div>Cena: {{ history.predictedValue }} zł</div>
    </div>
                  </v-expansion-panel-text>
                </v-expansion-panel>
              </v-expansion-panels>
            </v-col>
            <v-col cols="8">
              <div ref="mapContainer" style="width: auto; height: 500px;"></div>
            </v-col>
          </v-row>
          <div class="d-flex justify-center mt-4">
            <v-pagination
              v-model="currentPage"
              :length="historyStore.totalPages"
              :total-visible="5"
              @update:modelValue="onPageChange"
            ></v-pagination>
          </div>
        </v-card-text>
        <v-card-text v-else>
          <v-skeleton-loader type="list-item, list-item, list-item"></v-skeleton-loader>
        </v-card-text>
      </v-card>
    </v-main>
  </template>
  
  <script setup>
  import { ref, watch, onMounted } from 'vue';
  import { useHistoryStore } from '@/store/HistoryStore';
  import { useAuthStore } from '@/store/AuthStore';
  import { useRouter } from 'vue-router';
  import { useDate } from 'vuetify';
  import L from 'leaflet';
  
  const date = useDate();
  const historyStore = useHistoryStore();
  const authStore = useAuthStore();
  const router = useRouter();
  
  const map = ref();
  const mapContainer = ref();
  const currentMarkers = ref([]);
  
  const currentPage = ref(1);
  const currentPaginatedHistory = ref([]);
  const openedPanels = ref([]);
  const modelType = {
    SMALL: 'Minimalny',
    MEDIUM: 'Uproszczony',
    BIG: 'Zaawansowany',
  };
  
  watch(
    () => historyStore.paginatedHistory[currentPage.value - 1],
    (newValue) => {
      if (newValue) {
        const formattedHistory = newValue.map((history) => {
          return {
            ...history,
            modelType: modelType[history.modelType] || history.modelType,
            predictedValue: history.predictedValue.toLocaleString('pl-PL'),
            predictionDate: date.format(history.predictionDate, 'fullDateTime24h'),
          };
        });
        currentPaginatedHistory.value = formattedHistory;
        openedPanels.value = [];
      }
    },
    { immediate: true } 
  );
  
  const onPageChange = async (page) => {
    currentPage.value = page;
    await historyStore.fetchPaginatedHistory(page - 1);
    currentMarkers.value.forEach((marker) => {
      map.value.removeLayer(marker);
    });
    currentMarkers.value = [];
    for (let i = 0; i < historyStore.paginatedHistory[page - 1].length; i++) {
      const history = historyStore.paginatedHistory[page - 1][i];
      if (history.latitude && history.longitude) {
        const marker = L.marker([history.latitude, history.longitude]).addTo(map.value);
        currentMarkers.value.push(marker);
  
        marker.on('click', () => {
          const index = currentMarkers.value.indexOf(marker);
          if (index !== -1) {
            openedPanels.value = [index];
          }
        });
      }
    }
    map.value.setView([52.2297, 21.0122], 6);
  };
  
  const onPanelClick = (index) => {
    const marker = currentMarkers.value[index];
    if (marker) {
      map.value.setView(marker.getLatLng(), 17);
    }
  };
  watch(() => openedPanels.value, (newVal) => {
    if(newVal !== undefined)
        onPanelClick(newVal);
  })
  onMounted(async () => {
    if (authStore.user === null) {
      router.push('/');
      return;
    }
    historyStore.paginatedHistory = [];
    await historyStore.fetchPaginatedHistory(0);
    if (historyStore.paginatedHistory[0].length !== 0) {
      map.value = L.map(mapContainer.value).setView([52.2297, 21.0122], 6);
      L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>',
      }).addTo(map.value);
  
      for (let i = 0; i < historyStore.paginatedHistory[0].length; i++) {
        const history = historyStore.paginatedHistory[0][i];
        if (history.latitude && history.longitude) {
          const marker = L.marker([history.latitude, history.longitude]).addTo(map.value);
          currentMarkers.value.push(marker);
  
          marker.on('click', () => {
            const index = currentMarkers.value.indexOf(marker);
            if (index !== -1) {
              openedPanels.value = [index];
            }
          });
        }
      }
    }
  });
  </script>
  
  <style scoped>
  .v-main {
    background-color: #7c847f;
  }
  .ul {
  list-style-type: disc;
  padding-left: 1em;
}

.li {
  margin-bottom: 0.5em;
}

.li span.placeholder {
  color: rgba(0, 0, 0, 0.6);
}
  </style>
  