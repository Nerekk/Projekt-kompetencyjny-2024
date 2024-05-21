package com.pk2024.backend.prediction_model;

import com.pk2024.backend.DTO.MediumModelDTO;

public class PredictionModelMedium extends PredictionModel {

    public PredictionModelMedium(String filename) {
        super(filename);
    }

    // TODO walidacja danych
    public void setParameters(MediumModelDTO model) {
        putParameter(model.getCity(), 1d);
        putParameter("squareMeters", model.getSquareMeters());
        putParameter("longitude", model.getLongitude());
        putParameter("latitude", model.getLatitude());
        putParameter("centreDistance", model.getCentreDistance());
        putParameter("floorCount", model.getFloorCount().doubleValue());
        putParameter("rooms", model.getRooms().doubleValue());
    }

//    public Double predict() {
//        Double price = getValue(parameters);
//        resetParameters();
//        return price;
//    }
}
