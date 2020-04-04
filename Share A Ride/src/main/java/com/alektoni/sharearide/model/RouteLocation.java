package com.alektoni.sharearide.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;



@Entity
public class RouteLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  id;

    private Long routeId;
    private Double latitude;
    private Double longitude;
    private String locationCode;//(countryCode-city-locality-street)

    public RouteLocation() {
    }

    public RouteLocation(Long routeId,String locationCode, Double latitude, Double longitude) {
        this.routeId = routeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationCode=locationCode;
    }

    public Long getId() {
        return id;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public Long getRouteId() {
        return routeId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
