package com.alektoni.sharearide.model;

import javax.persistence.*;



@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String profilePictureUrl;
    private String phoneNumber;
    private String homeTown;
    private String country;
    private Boolean active;
    private String roles;

    public User() { }

    public User(String email, String firstName, String lastName, String password,
                String profilePictureUrl, String phoneNumber,
                String homeTown, String country,Boolean active,String roles) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
        this.phoneNumber = phoneNumber;
        this.homeTown = homeTown;
        this.country = country;
        this.active=active;
        this.roles=roles;
    }



    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public String getCountry() {
        return country;
    }

    public Boolean getActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
