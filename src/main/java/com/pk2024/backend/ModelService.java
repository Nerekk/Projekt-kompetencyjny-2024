package com.pk2024.backend;

import com.pk2024.backend.Utils.PredictionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
    private static ModelRepository modelRepository;
    private final PredictionModel predictionModel4f;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        ModelService.modelRepository = modelRepository;
        this.predictionModel4f = new PredictionModel("reg_rf_4f.pmml");
    }

    public ResponseEntity<Integer> getPredictedValue(Double meters, Double longitude, Double latitude, String city) {
        predictionModel4f.setParameters(meters, longitude, latitude, city);
        Double price = predictionModel4f.predict();
        Integer fixedPrice = (int) Math.ceil(price);
        return new ResponseEntity<>(fixedPrice, HttpStatus.OK);
    }
}
