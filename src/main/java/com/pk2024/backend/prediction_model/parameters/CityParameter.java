package com.pk2024.backend.prediction_model.parameters;

public enum CityParameter implements Parameter {

    WROCLAW,
    POZNAN,
    GDANSK,
    KATOWICE,
    LODZ,
    BYDGOSZCZ,
    CZESTOCHOWA,
    SZCZECIN,
    GDYNIA,
    WARSZAWA,
    LUBLIN,
    KRAKOW,
    RZESZOW,
    RADOM,
    BIALYSTOK;

    @Override
    public String getName() {
        return name().toLowerCase();
    }

    public static CityParameter getFromString(String cityName) {
        return valueOf(cityName.toUpperCase());
    }

}
