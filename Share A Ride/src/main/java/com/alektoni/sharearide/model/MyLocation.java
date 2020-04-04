package com.alektoni.sharearide.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class MyLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    private Long userId;
    private Double latitude;
    private Double longitude;
    private String label;


    public MyLocation() { }

    public MyLocation(Long userId, Double latitude, Double longitude, String label) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.label = label;
    }

    public Long getLocationId() {
        return locationId;
    }

    public Long getUserId() {
        return userId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getLabel() {
        return label;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
