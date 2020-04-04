package com.alektoni.sharearide.model.dto;

public class Location {
    private Double latitude;
    private Double longitude;
    private String locationCode;//(countryCode-city-locality-street)

    public Location() {
    }

    public Location(Double latitude, Double longitude, String locationCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationCode = locationCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }
}