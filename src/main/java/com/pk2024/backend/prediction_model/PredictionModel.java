package com.pk2024.backend.prediction_model;

import org.pmml4s.model.Model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PredictionModel {
    protected final Model model;
    protected Map<String, Double> parameters;

    public PredictionModel(String filename) {
        this.model = Model.fromFile(filename);
        parameters = new HashMap<>();
        initCities();
    }

    protected void initCities() {
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

    protected void resetParameters() {
        for (String key : parameters.keySet()) {
            parameters.put(key, 0d);
        }
    }

    protected void putParameter(String parameter, Double value) {
        parameters.put(parameter, value);
    }

    protected Double getValue(Map<String, Double> values) {
        Object[] valuesMap = Arrays.stream(model.inputNames())
                .map(values::get)
                .toArray();

        Object[] result = model.predict(valuesMap);
        return (Double) result[0];
    }

    public Double predict() {
        Double price = getValue(parameters);
        resetParameters();
        return price;
    }

}
