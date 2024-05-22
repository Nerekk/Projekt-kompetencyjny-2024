package com.pk2024.backend.prediction_model.parameters;

public enum DetailedParameter implements Parameter {
    SQUARE_METERS("squareMeters"),
    LONGITUDE("longitude"),
    LATITUDE("latitude"),
    CENTRE_DISTANCE("centreDistance"),
    FLOOR_COUNT("floorCount"),
    ROOMS("rooms"),

    KINDER_GARDEN_DISTANCE("kindergartenDistance"),
    RESTAURANT_DISTANCE("restaurantDistance"),
    COLLEGE_DISTANCE("collegeDistance"),
    POST_OFFICE_DISTANCE("postOfficeDistance"),
    CLINIC_DISTANCE("clinicDistance"),
    SCHOOL_DISTANCE("schoolDistance"),
    PHARMACY_DISTANCE("pharmacyDistance"),
    POI_COUNT("poiCount");

    private final String parameterName;

    DetailedParameter(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String getName() {
        return parameterName;
    }

}