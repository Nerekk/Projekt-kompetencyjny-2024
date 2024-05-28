package com.pk2024.backend.prediction_model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.pk2024.backend.settings.Settings.MODEL_MAPPING;

@Validated
@RestController
@RequestMapping(path = MODEL_MAPPING)
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping(path = "/small/prediction")
    public ResponseEntity<Integer> getPredictedValueSmall(@Valid @RequestBody ModelRequest modelRequest) {
        return modelService.getPredictedValue(modelRequest, ModelType.SMALL);
    }

    @PostMapping(path = "/medium/prediction")
    public ResponseEntity<Integer> getPredictedValueMedium(@Valid @RequestBody ModelRequest modelRequest) {
        return modelService.getPredictedValue(modelRequest, ModelType.MEDIUM);
    }

    @PostMapping(path = "/big/prediction")
    public ResponseEntity<Integer> getPredictedValueBig(@Valid @RequestBody ModelRequest modelRequest) {
        return modelService.getPredictedValue(modelRequest, ModelType.BIG);
    }


    private void validateModelRequest(ModelRequest modelRequest) {
        Set<ConstraintViolation<ModelRequest>> violations =
                Validation.buildDefaultValidatorFactory().getValidator().validate(modelRequest);

        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);

    }
}
