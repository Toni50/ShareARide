package com.alektoni.sharearide.model.dto;


import java.util.Date;
import java.util.List;

public class FindARideModel {

    private Date startTime;
    List<Location> locations;

    public FindARideModel() {
    }

    public FindARideModel(Date startTime, List<Location> locations) {
        this.startTime = startTime;
        this.locations = locations;
    }

    public Date getStartTime() {
        return startTime;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }


}
