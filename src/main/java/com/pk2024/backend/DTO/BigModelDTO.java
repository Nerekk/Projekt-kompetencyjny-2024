package com.pk2024.backend.DTO;


public class BigModelDTO {
    private String city;
    private Double squareMeters;
    private Double longitude;
    private Double latitude;
    private Double centreDistance;
    private Integer floorCount;
    private Integer rooms;

    private Double kindergartenDistance;
    private Double restaurantDistance;
    private Double collegeDistance;
    private Double postOfficeDistance;
    private Double clinicDistance;
    private Double schoolDistance;
    private Double pharmacyDistance;
    private Integer poiCount;

    public BigModelDTO(Integer poiCount,
                       Double pharmacyDistance,
                       Double schoolDistance,
                       Double clinicDistance,
                       Double postOfficeDistance,
                       Double collegeDistance,
                       Double restaurantDistance,
                       Double kindergartenDistance,
                       Integer rooms,
                       Integer floorCount,
                       Double centreDistance,
                       Double latitude,
                       Double longitude,
                       Double squareMeters,
                       String city) {
        this.poiCount = poiCount;
        this.pharmacyDistance = pharmacyDistance;
        this.schoolDistance = schoolDistance;
        this.clinicDistance = clinicDistance;
        this.postOfficeDistance = postOfficeDistance;
        this.collegeDistance = collegeDistance;
        this.restaurantDistance = restaurantDistance;
        this.kindergartenDistance = kindergartenDistance;
        this.rooms = rooms;
        this.floorCount = floorCount;
        this.centreDistance = centreDistance;
        this.latitude = latitude;
        this.longitude = longitude;
        this.squareMeters = squareMeters;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(Double squareMeters) {
        this.squareMeters = squareMeters;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getCentreDistance() {
        return centreDistance;
    }

    public void setCentreDistance(Double centreDistance) {
        this.centreDistance = centreDistance;
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Double getKindergartenDistance() {
        return kindergartenDistance;
    }

    public void setKindergartenDistance(Double kindergartenDistance) {
        this.kindergartenDistance = kindergartenDistance;
    }

    public Double getRestaurantDistance() {
        return restaurantDistance;
    }

    public void setRestaurantDistance(Double restaurantDistance) {
        this.restaurantDistance = restaurantDistance;
    }

    public Double getCollegeDistance() {
        return collegeDistance;
    }

    public void setCollegeDistance(Double collegeDistance) {
        this.collegeDistance = collegeDistance;
    }

    public Double getPostOfficeDistance() {
        return postOfficeDistance;
    }

    public void setPostOfficeDistance(Double postOfficeDistance) {
        this.postOfficeDistance = postOfficeDistance;
    }

    public Double getClinicDistance() {
        return clinicDistance;
    }

    public void setClinicDistance(Double clinicDistance) {
        this.clinicDistance = clinicDistance;
    }

    public Double getSchoolDistance() {
        return schoolDistance;
    }

    public void setSchoolDistance(Double schoolDistance) {
        this.schoolDistance = schoolDistance;
    }

    public Double getPharmacyDistance() {
        return pharmacyDistance;
    }

    public void setPharmacyDistance(Double pharmacyDistance) {
        this.pharmacyDistance = pharmacyDistance;
    }

    public Integer getPoiCount() {
        return poiCount;
    }

    public void setPoiCount(Integer poiCount) {
        this.poiCount = poiCount;
    }
}
