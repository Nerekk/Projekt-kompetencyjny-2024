package com.pk2024.backend.prediction_model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static com.pk2024.backend.settings.Settings.MODEL_MAPPING;

@Validated
@RestController
@RequestMapping(path = MODEL_MAPPING)
public class ModelController {

    private final ModelRequestHandler modelRequestHandler;

    @Autowired
    public ModelController(ModelRequestHandler modelRequestHandler) {
        this.modelRequestHandler = modelRequestHandler;
    }

    @GetMapping(path = "/{meters}/{longitude}/{latitude}/{city}")
    public ResponseEntity<Integer> getPredictedValue(@PathVariable("meters") Double meters,
                                                     @PathVariable("longitude") Double longitude,
                                                     @PathVariable("latitude") Double latitude,
                                                     @PathVariable("city") String city) {

        ModelRequest modelRequest = new ModelRequest();
        modelRequest.setCity(city);
        modelRequest.setSquareMeters(meters);
        modelRequest.setLatitude(latitude);
        modelRequest.setLongitude(longitude);

        validateModelRequest(modelRequest);

        return modelRequestHandler.getPredictedValue(modelRequest, ModelType.SMALL);
    }

    @GetMapping(path = "/{meters}/{longitude}/{latitude}/{city}/{centreDistance}/{floorCount}/{rooms}")
    public ResponseEntity<Integer> getPredictedValue(@PathVariable("meters") Double meters,
                                                     @PathVariable("longitude") Double longitude,
                                                     @PathVariable("latitude") Double latitude,
                                                     @PathVariable("city") String city,
                                                     @PathVariable("centreDistance") Double centreDistance,
                                                     @PathVariable("floorCount") Integer floorCount,
                                                     @PathVariable("rooms") Integer rooms) {

        ModelRequest modelRequest = new ModelRequest();
        modelRequest.setCity(city);
        modelRequest.setSquareMeters(meters);
        modelRequest.setLatitude(latitude);
        modelRequest.setLongitude(longitude);
        modelRequest.setCentreDistance(centreDistance);
        modelRequest.setFloorCount(floorCount);
        modelRequest.setRooms(rooms);

        validateModelRequest(modelRequest);

        return modelRequestHandler.getPredictedValue(modelRequest, ModelType.MEDIUM);
    }


    private void validateModelRequest(ModelRequest modelRequest) {
        Set<ConstraintViolation<ModelRequest>> violations =
                Validation.buildDefaultValidatorFactory().getValidator().validate(modelRequest);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
