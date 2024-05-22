package com.pk2024.backend.prediction_model;

import com.pk2024.backend.prediction_model.parameters.ModelParameters;
import lombok.NonNull;
import org.pmml4s.model.Model;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class PredictionModel {

    protected final ResourceLoader resourceLoader;
    protected final Model model;


    public PredictionModel(String filename) throws IOException {
        this.resourceLoader = new DefaultResourceLoader();
        this.model = Model.fromInputStream(getInputStream(filename));
    }


    private InputStream getInputStream(String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:/" + filename);

        if (!resource.exists())
            throw new FileNotFoundException(filename + " not found");

        return resource.getInputStream();
    }


    private void printEachParameter(Map<String, Double> values) {
        values.forEach((key, value) -> System.out.println(key + ": " + value));
    }


    private Double predictedValue(Map<String, Double> values) {
        Object[] valuesMap = Arrays.stream(model.inputNames())
                .map(values::get)
                .toArray();

        Object[] result = model.predict(valuesMap);
        return (Double) result[0];
    }


    public Double predict(@NonNull ModelParameters modelParameters) {
        return predictedValue(modelParameters.getParameters());
    }

}
