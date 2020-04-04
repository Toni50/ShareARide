package com.alektoni.sharearide.model;


import javax.persistence.*;
import java.util.List;



@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @ManyToOne
    @JoinColumn(name = "driverId")
    private User driver;
    private String name;
    private Boolean active;
    private String color;
    private Integer freeSeats;
    private Boolean petsAllowed;
    private Boolean smokingAllowed;
    private Boolean airConditioner;
    private String pictureUrl;



    public Car() { }

    public Car(User driver, String name, Boolean active, String color, Integer freeSeats, Boolean petsAllowed, Boolean smokingAllowed, Boolean airConditioner, String pictureUrl) {
        this.driver = driver;
        this.name = name;
        this.active = active;
        this.color = color;
        this.freeSeats = freeSeats;
        this.petsAllowed = petsAllowed;
        this.smokingAllowed = smokingAllowed;
        this.airConditioner = airConditioner;
        this.pictureUrl = pictureUrl;
    }

    public Long getCarId() {
        return carId;
    }

    public User getDriver() {
        return driver;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    public String getColor() {
        return color;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public Boolean getPetsAllowed() {
        return petsAllowed;
    }

    public Boolean getSmokingAllowed() {
        return smokingAllowed;
    }

    public Boolean getAirConditioner() {
        return airConditioner;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public void setPetsAllowed(Boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setSmokingAllowed(Boolean smokingAllowed) {
        this.smokingAllowed = smokingAllowed;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

}
