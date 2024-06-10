import { defineStore } from 'pinia';
import PredictionService from '@/services/PredictionService';

export const usePredictionStore = defineStore('predictionStore', {
    state: () => ({
        overlay: false,
        selectedCity: 'Warszawa',
        parameters: getDefaultParameters(),
        predictedPrice: 0,
        selectedModel: 'minimal',
        currentMapZoom: 10,
        enabled: disableAllParameters()
    }),
    actions: {
        setShowOverlay() {
            this.overlay = true;
        },
        async showOverlay() {
            this.setShowOverlay();
        },
        setSelectedCity(city) {
            this.selectedCity = city;
        },
        async smallModelPredict() {
            this.parameters.city = removeDiacritics(this.selectedCity);
            const smallParameters = {
                city: this.parameters.city,
                squareMeters: this.parameters.squareMeters,
                longitude: this.parameters.longitude,
                latitude: this.parameters.latitude
            };
            this.showOverlay();
            await PredictionService.smallModelPredict(smallParameters)
                .then((response) => {
                    this.predictedPrice = response.data;
                })
                .catch((error) => {
                    console.error('Error in smallModelPredict:', error, smallParameters);
                    this.overlay = false;
                });

        },
        async mediumModelPredict() {
            this.parameters.city = removeDiacritics(this.selectedCity);
            const mediumParameters = {
                city: this.parameters.city,
                squareMeters: this.parameters.squareMeters,
                longitude: this.parameters.longitude,
                latitude: this.parameters.latitude,
                centreDistance: this.parameters.centreDistance,
                floorCount: this.parameters.floorCount,
                rooms: this.parameters.rooms,
            };
            this.showOverlay();
            await PredictionService.mediumModelPredict(mediumParameters)
                .then((response) => {
                    this.predictedPrice = response.data;
                })
                .catch((error) => {
                    console.error('Error in mediumModelPredict:', error, mediumParameters);
                    this.overlay = false;
                });

        },
        async bigModelPredict() {
            this.parameters.city = removeDiacritics(this.selectedCity);
        
            const bigParameters = {
                city: this.parameters.city,
                longitude: this.parameters.longitude,
                latitude: this.parameters.latitude,
            };
        
            if (this.enabled.squareMeters) bigParameters.squareMeters = this.parameters.squareMeters;
            if (this.enabled.centreDistance) bigParameters.centreDistance = this.parameters.centreDistance;
            if (this.enabled.floorCount) bigParameters.floorCount = this.parameters.floorCount;
            if (this.enabled.rooms) bigParameters.rooms = this.parameters.rooms;
            if (this.enabled.kindergartenDistance) bigParameters.kindergartenDistance = this.parameters.kindergartenDistance;
            if (this.enabled.schoolDistance) bigParameters.schoolDistance = this.parameters.schoolDistance;
            if (this.enabled.collegeDistance) bigParameters.collegeDistance = this.parameters.collegeDistance;
            if (this.enabled.postOfficeDistance) bigParameters.postOfficeDistance = this.parameters.postOfficeDistance;
            if (this.enabled.clinicDistance) bigParameters.clinicDistance = this.parameters.clinicDistance;
            if (this.enabled.pharmacyDistance) bigParameters.pharmacyDistance = this.parameters.pharmacyDistance;
            if (this.enabled.restaurantDistance) bigParameters.restaurantDistance = this.parameters.restaurantDistance;
            if (this.enabled.poiCount) bigParameters.poiCount = this.parameters.poiCount;
            this.showOverlay();
            await PredictionService.bigModelPredict(bigParameters)
                .then((response) => {
                    this.predictedPrice = response.data;
                })
                .catch((error) => {
                    console.error('Error in bigModelPredict:', error, bigParameters);
                    this.overlay = false;
                });
        },
        
        resetParameters() {
            this.overlay = false;
            this.parameters = getDefaultParameters();
            this.predictedPrice = 0;
            this.enabled = disableAllParameters();
        },
    },
});

// Funkcja do usuwania polskich znaków
function removeDiacritics(str) {
    const diacriticsMap = {
        'ą': 'a', 'ć': 'c', 'ę': 'e', 'ł': 'l', 'ń': 'n', 'ó': 'o', 'ś': 's', 'ź': 'z', 'ż': 'z',
        'Ą': 'A', 'Ć': 'C', 'Ę': 'E', 'Ł': 'L', 'Ń': 'N', 'Ó': 'O', 'Ś': 'S', 'Ź': 'Z', 'Ż': 'Z'
    };
    return str.replace(/[ąćęłńóśźżĄĆĘŁŃÓŚŹŻ]/g, match => diacriticsMap[match] || match);
}

// Funkcja pomocnicza do domyślnych parametrów
function getDefaultParameters() {
    return {
        city: '',
        squareMeters: 0,
        longitude: null,
        latitude: null,
        centreDistance: 0,
        floorCount: 1,
        rooms: 1,
        kindergartenDistance: 0,
        restaurantDistance: 0,
        collegeDistance: 0,
        postOfficeDistance: 0,
        clinicDistance: 0,
        schoolDistance: 0,
        pharmacyDistance: 0,
        poiCount: 0
    };
}

function disableAllParameters(){
    return {
        squareMeters: false,
        rooms: false,
        floorCount: false,
        centreDistance: false,
        kindergartenDistance: false,
        schoolDistance: false,
        collegeDistance: false,
        postOfficeDistance: false,
        clinicDistance: false,
        pharmacyDistance: false,
        restaurantDistance: false,
        poiCount: false
      };
}