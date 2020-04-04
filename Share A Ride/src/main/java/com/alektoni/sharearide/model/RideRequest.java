package com.alektoni.sharearide.model;


import javax.persistence.*;
import java.util.Date;


@Entity
public class RideRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @ManyToOne
    private Ride ride;

    @ManyToOne
    @JoinColumn(name = "requestByUserId")
    private User requestByUser;

    private Integer seatsRequested;
    private Date timeRequested;
    private RideRequestResponseStatus responseStatus;
    private String responseDescription;


    public RideRequest() { }

    public RideRequest(Ride ride, User requestByUser, Integer seatsRequested, Date timeRequested, RideRequestResponseStatus responseStatus, String responseDescription) {
        this.ride = ride;
        this.requestByUser = requestByUser;
        this.seatsRequested = seatsRequested;
        this.timeRequested = timeRequested;
        this.responseStatus = responseStatus;
        this.responseDescription = responseDescription;
    }

    public Long getRequestId() {
        return requestId;
    }

    public Ride getRide() {
        return ride;
    }

    public User getRequestByUser() {
        return requestByUser;
    }

    public Integer getSeatsRequested() {
        return seatsRequested;
    }

    public Date getTimeRequested() {
        return timeRequested;
    }

    public RideRequestResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public void setRequestByUser(User requestByUser) {
        this.requestByUser = requestByUser;
    }

    public void setSeatsRequested(Integer seatsRequested) {
        this.seatsRequested = seatsRequested;
    }

    public void setTimeRequested(Date timeRequested) {
        this.timeRequested = timeRequested;
    }

    public void setResponseStatus(RideRequestResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }
}
