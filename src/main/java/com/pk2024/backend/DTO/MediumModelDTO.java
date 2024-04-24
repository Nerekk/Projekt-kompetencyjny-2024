package com.pk2024.backend.DTO;

public class MediumModelDTO {
    private String city;
    private Double squareMeters;
    private Double longitude;
    private Double latitude;
    private Double centreDistance;
    private Integer floorCount;
    private Integer rooms;

    public MediumModelDTO(String city, Double squareMeters, Double longitude, Double latitude, Double centreDistance, Integer floorCount, Integer rooms) {
        this.city = city;
        this.squareMeters = squareMeters;
        this.longitude = longitude;
        this.latitude = latitude;
        this.centreDistance = centreDistance;
        this.floorCount = floorCount;
        this.rooms = rooms;
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
}
