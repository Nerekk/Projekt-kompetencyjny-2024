package com.pk2024.backend.Utils;

import org.pmml4s.model.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PredictionModel {
    private final Model model;
    private Map<String, Double> parameters;

    public PredictionModel(String filename) {
        this.model = Model.fromFile(filename);
        parameters = new HashMap<>();
    }

    private void prepareParameters() {
        parameters.put("longitude", 0d);
        parameters.put("squareMeters", 0d);
        parameters.put("latitude", 0d);
        parameters.put("wroclaw", 0d);
        parameters.put("poznan", 0d);
        parameters.put("gdansk", 0d);
        parameters.put("katowice", 0d);
        parameters.put("lodz", 0d);
        parameters.put("bydgoszcz", 0d);
        parameters.put("czestochowa", 0d);
        parameters.put("szczecin", 0d);
        parameters.put("gdynia", 0d);
        parameters.put("warszawa", 0d);
        parameters.put("lublin", 0d);
        parameters.put("krakow", 0d);
        parameters.put("rzeszow", 0d);
        parameters.put("radom", 0d);
        parameters.put("bialystok", 0d);
    }

    // TODO walidacja danych
    public void setParameters(Double meters, Double longitude, Double latitude, String city) {
        parameters.put("squareMeters", meters);
        parameters.put("longitude", longitude);
        parameters.put("latitude", latitude);
        parameters.put(city, 1d);
    }

    public Double predict() {
        Double price = getValue(parameters);
        prepareParameters();
        return price;
    }

    private Double getValue(Map<String, Double> values) {
        Object[] valuesMap = Arrays.stream(model.inputNames())
                .map(values::get)
                .toArray();

        Object[] result = model.predict(valuesMap);
        return (Double) result[0];
    }

}
