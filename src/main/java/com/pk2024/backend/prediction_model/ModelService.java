package com.pk2024.backend.prediction_model;

import com.pk2024.backend.DTO.MediumModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.pk2024.backend.settings.Settings.RESOURCES_PATH;

@Service
public class ModelService {
    private static ModelRepository modelRepository;

    private PredictionModelSmall predictionModelSmall;

    private PredictionModelMedium predictionModelMedium;

    private PredictionModelBig predictionModelBig;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        ModelService.modelRepository = modelRepository;

//        this.predictionModelSmall = new PredictionModelSmall(RESOURCES_PATH + "reg_rf_4f.pmml");
//        this.predictionModelMedium = new PredictionModelMedium(RESOURCES_PATH + "reg_rf_7f.pmml");
//        this.predictionModelBig = new PredictionModelBig(RESOURCES_PATH + "reg_rf_14f.pmml");
    }

    public ResponseEntity<Integer> getPredictedValueSmall(Double meters, Double longitude, Double latitude, String city) {
        predictionModelSmall.setParameters(meters, longitude, latitude, city);

        Double price = predictionModelSmall.predict();
        Integer fixedPrice = (int) Math.ceil(price);

        return new ResponseEntity<>(fixedPrice, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getPredictedValueMedium(MediumModelDTO model) {
        predictionModelMedium.setParameters(model);

        Double price = predictionModelMedium.predict();
        Integer fixedPrice = (int) Math.ceil(price);

        return new ResponseEntity<>(fixedPrice, HttpStatus.OK);
    }
}
