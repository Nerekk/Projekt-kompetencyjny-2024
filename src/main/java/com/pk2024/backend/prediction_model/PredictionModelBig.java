package com.pk2024.backend.prediction_model;

import com.pk2024.backend.DTO.BigModelDTO;

public class PredictionModelBig extends PredictionModel {

    public PredictionModelBig(String filename) {
        super(filename);
    }

    // TODO walidacja danych
    public void setParameters(BigModelDTO model) {
        putParameter(model.getCity(), 1d);
        putParameter("squareMeters", model.getSquareMeters());
        putParameter("longitude", model.getLongitude());
        putParameter("latitude", model.getLatitude());
        putParameter("centreDistance", model.getCentreDistance());
        putParameter("floorCount", model.getFloorCount().doubleValue());
        putParameter("rooms", model.getRooms().doubleValue());

        putParameter("kindergartenDistance", model.getKindergartenDistance());
        putParameter("restaurantDistance", model.getRestaurantDistance());
        putParameter("collegeDistance", model.getCollegeDistance());
        putParameter("postOfficeDistance", model.getPostOfficeDistance());
        putParameter("clinicDistance", model.getClinicDistance());
        putParameter("schoolDistance", model.getSchoolDistance());
        putParameter("pharmacyDistance", model.getPharmacyDistance());
        putParameter("poiCount", model.getPoiCount().doubleValue());
    }

//    public Double predict() {
//        Double price = getValue(parameters);
//        resetParameters();
//        return price;
//    }
}
