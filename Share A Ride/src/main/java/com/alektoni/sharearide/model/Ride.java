package com.alektoni.sharearide.model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideId;

    @ManyToOne(cascade = CascadeType.ALL)
    private CarRideDetails carRideDetails;


    private Date startTime;
    private Integer pricePerSeat;
    private Boolean finished;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "routeId")
    private RideRoute rideRoute;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> passengers;



    public Ride() {
    }

    public Ride(CarRideDetails carRideDetails, Date startTime, Integer pricePerSeat, Boolean finished, RideRoute rideRoute, List<User> passengers) {
        this.carRideDetails = carRideDetails;
        this.startTime = startTime;
        this.pricePerSeat = pricePerSeat;
        this.finished = finished;
        this.rideRoute = rideRoute;
        this.passengers = passengers;
    }

    public Long getRideId() {
        return rideId;
    }

    public CarRideDetails getCarRideDetails() {
        return carRideDetails;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Integer getPricePerSeat() {
        return pricePerSeat;
    }

    public Boolean getFinished() {
        return finished;
    }

    public RideRoute getRideRoute() {
        return rideRoute;
    }

    public List<User> getPassengers() {
        return passengers;
    }



    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public void setCarRideDetails(CarRideDetails carRideDetails) {
        this.carRideDetails = carRideDetails;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setPricePerSeat(Integer pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public void setRideRoute(RideRoute rideRoute) {
        this.rideRoute = rideRoute;
    }

    public void setPassengers(List<User> passengers) {
        this.passengers = passengers;
    }


}
