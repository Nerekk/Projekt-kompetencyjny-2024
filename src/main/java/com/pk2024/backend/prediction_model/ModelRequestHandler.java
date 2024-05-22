package com.pk2024.backend.prediction_model;

import com.pk2024.backend.prediction_model.parameters.CityParameter;
import com.pk2024.backend.prediction_model.parameters.DetailedParameter;
import com.pk2024.backend.prediction_model.parameters.ModelParameters;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

import static com.pk2024.backend.settings.Settings.MODEL_FILENAMES_MAP;

@Service
public class ModelRequestHandler {
    private final HashMap<ModelType, PredictionModel> predictionModelMap;

    public ModelRequestHandler() {
        this.predictionModelMap = new HashMap<>();
        initializePredictionModels();
    }


    private void initializePredictionModels() {
        for (ModelType modelType : ModelType.values()) {
            try {
                String filename = MODEL_FILENAMES_MAP.get(modelType);

                System.out.println("Loading model: " + modelType + " " + filename);
                predictionModelMap.put(modelType, new PredictionModel(filename));
                System.out.println("Loaded: " + modelType);
            } catch (IOException ignored) {
                System.err.println("Unable to load model: " + modelType);

//                System.exit(0); TODO: SYSTEM EXIT AFTER FAIL AT LEAST ONE MODEL LOADING
            }
        }


    }


    private PredictionModel getPredictionModel(@NonNull ModelType modelType) {
        return predictionModelMap.get(modelType);
    }


    public ResponseEntity<Integer> getPredictedValue(ModelRequest modelRequest, @NonNull ModelType modelType) {
        ModelParameters modelParameters = prepareParameters(modelRequest, modelType);

        PredictionModel predictionModel = getPredictionModel(modelType);
        Double predictedPrice = predictionModel.predict(modelParameters);

        return new ResponseEntity<>((int) Math.ceil(predictedPrice), HttpStatus.OK);
    }


    private ModelParameters prepareParameters(ModelRequest modelRequest, @NonNull ModelType modelType) {
        return switch (modelType) {
            case SMALL -> getSmallModelParameters(modelRequest);
            case MEDIUM -> getMediumModelParameters(modelRequest);
            case BIG -> getBigModelParameters(modelRequest);
        };

    }


    private ModelParameters getSmallModelParameters(ModelRequest modelRequest) {
        ModelParameters modelParameters = new ModelParameters();

        modelParameters.putParameter(CityParameter.getFromString(modelRequest.getCity()), 1d);
        modelParameters.putParameter(DetailedParameter.SQUARE_METERS, modelRequest.getSquareMeters());
        modelParameters.putParameter(DetailedParameter.LONGITUDE, modelRequest.getLongitude());
        modelParameters.putParameter(DetailedParameter.LATITUDE, modelRequest.getLatitude());

        return modelParameters;
    }


    private ModelParameters getMediumModelParameters(ModelRequest modelRequest) {
        ModelParameters modelParameters = getSmallModelParameters(modelRequest);

        modelParameters.putParameter(DetailedParameter.CENTRE_DISTANCE, modelRequest.getCentreDistance());
        modelParameters.putParameter(DetailedParameter.FLOOR_COUNT, modelRequest.getFloorCount().doubleValue());
        modelParameters.putParameter(DetailedParameter.ROOMS, modelRequest.getRooms().doubleValue());

        return modelParameters;
    }


    private ModelParameters getBigModelParameters(ModelRequest modelRequest) {
        ModelParameters modelParameters = getMediumModelParameters(modelRequest);

        modelParameters.putParameter(DetailedParameter.KINDER_GARDEN_DISTANCE, modelRequest.getKindergartenDistance());
        modelParameters.putParameter(DetailedParameter.RESTAURANT_DISTANCE, modelRequest.getRestaurantDistance());
        modelParameters.putParameter(DetailedParameter.COLLEGE_DISTANCE, modelRequest.getCollegeDistance());
        modelParameters.putParameter(DetailedParameter.POST_OFFICE_DISTANCE, modelRequest.getPostOfficeDistance());
        modelParameters.putParameter(DetailedParameter.CLINIC_DISTANCE, modelRequest.getClinicDistance());
        modelParameters.putParameter(DetailedParameter.SCHOOL_DISTANCE, modelRequest.getSchoolDistance());
        modelParameters.putParameter(DetailedParameter.PHARMACY_DISTANCE, modelRequest.getPharmacyDistance());
        modelParameters.putParameter(DetailedParameter.POI_COUNT, modelRequest.getPoiCount().doubleValue());

        return modelParameters;
    }

}
