package com.pk2024.backend.prediction_model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelRequest {

    @NotBlank(message = "City cannot be null or blank")
    @ValidCity
    private String city;

    @NotNull(message = "Square meters cannot be null")
    @Positive(message = "Square meters must be positive")
    private Double squareMeters = 1d;

    @NotNull(message = "Longitude cannot be null")
    @Min(value = -180, message = "Longitude must be between -180 and 180")
    @Max(value = 180, message = "Longitude must be between -180 and 180")
    private Double longitude = 0d;

    @NotNull(message = "Latitude cannot be null")
    @Min(value = -90, message = "Latitude must be between -90 and 90")
    @Max(value = 90, message = "Latitude must be between -90 and 90")
    private Double latitude = 0d;

    @NotNull(message = "Centre distance cannot be null")
    @PositiveOrZero(message = "Centre distance must be zero or positive")
    private Double centreDistance = 0d;

    @NotNull(message = "Floor count cannot be null")
    @Min(value = 1, message = "Floor count must be at least 1")
    private Integer floorCount = 1;

    @NotNull(message = "Rooms count cannot be null")
    @Min(value = 1, message = "Rooms count must be at least 1")
    private Integer rooms = 1;

    @NotNull(message = "Kindergarten distance cannot be null")
    @PositiveOrZero(message = "Kindergarten distance must be zero or positive")
    private Double kindergartenDistance = 0d;

    @NotNull(message = "Restaurant distance cannot be null")
    @PositiveOrZero(message = "Restaurant distance must be zero or positive")
    private Double restaurantDistance = 0d;

    @NotNull(message = "College distance cannot be null")
    @PositiveOrZero(message = "College distance must be zero or positive")
    private Double collegeDistance = 0d;

    @NotNull(message = "Post office distance cannot be null")
    @PositiveOrZero(message = "Post office distance must be zero or positive")
    private Double postOfficeDistance = 0d;

    @NotNull(message = "Clinic distance cannot be null")
    @PositiveOrZero(message = "Clinic distance must be zero or positive")
    private Double clinicDistance = 0d;

    @NotNull(message = "School distance cannot be null")
    @PositiveOrZero(message = "School distance must be zero or positive")
    private Double schoolDistance = 0d;

    @NotNull(message = "Pharmacy distance cannot be null")
    @PositiveOrZero(message = "Pharmacy distance must be zero or positive")
    private Double pharmacyDistance = 0d;

    @NotNull(message = "POI count cannot be null")
    @Min(value = 0, message = "POI count must be zero or positive")
    private Integer poiCount = 0;
}
