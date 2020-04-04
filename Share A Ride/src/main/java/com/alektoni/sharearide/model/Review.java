package com.alektoni.sharearide.model;


import javax.persistence.*;
import java.util.Date;


@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @OneToOne
    private User leftByUser;
    private Long rideId;
    private String description;
    private Integer numOfStars;
    private Date dateCreated;

    public Review() { }

    public Review(User leftByUser, Long rideId, String description, Integer numOfStars, Date dateCreated) {
        this.leftByUser = leftByUser;
        this.rideId = rideId;
        this.description = description;
        this.numOfStars = numOfStars;
        this.dateCreated = dateCreated;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public User getLeftByUser() {
        return leftByUser;
    }

    public Long getRideId() {
        return rideId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getNumOfStars() {
        return numOfStars;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public void setLeftByUser(User leftByUser) {
        this.leftByUser = leftByUser;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumOfStars(Integer numOfStars) {
        this.numOfStars = numOfStars;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
