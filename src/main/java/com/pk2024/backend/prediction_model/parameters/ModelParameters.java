package com.pk2024.backend.prediction_model.parameters;

import lombok.NonNull;

import java.util.*;

public class ModelParameters {

    private final Map<String, Double> parameters;


    public ModelParameters() {
        this.parameters = new HashMap<>();
        fillParameters();
    }


    private void fillParameters() {
        Arrays.stream(DetailedParameter.values()).forEach(detailedParameter -> putParameter(detailedParameter, 0.0));
        Arrays.stream(CityParameter.values()).forEach(cityParameter -> putParameter(cityParameter, 0.0));
    }


    public void putParameter(@NonNull Parameter parameter, Double value) {
        parameters.put(parameter.getName(), value);
    }


    public Map<String, Double> getParameters() {
        return parameters;
    }
}
