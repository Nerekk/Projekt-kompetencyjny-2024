package com.pk2024.backend.prediction_model;

public class PredictionModelSmall extends PredictionModel {

    public PredictionModelSmall(String filename) {
        super(filename);
    }

    // TODO walidacja danych
    public void setParameters(Double meters, Double longitude, Double latitude, String city) {
        parameters.put("squareMeters", meters);
        parameters.put("longitude", longitude);
        parameters.put("latitude", latitude);
        parameters.put(city, 1d);
    }
}
