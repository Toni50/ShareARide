package com.alektoni.sharearide.model;


import javax.persistence.*;
import java.util.Date;


@Entity
public class MyNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "byUserId")
    private User byUser;

    private Long toUserId;
    private String description;
    private Date timeDate;

    public MyNotification() { }

    public MyNotification(User byUser, Long toUserId, String description, Date timeDate) {
        this.byUser = byUser;
        this.toUserId = toUserId;
        this.description = description;
        this.timeDate = timeDate;
    }

    public Long getId() {
        return id;
    }


    public User getByUser() {
        return byUser;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public String getDescription() {
        return description;
    }

    public Date getTimeDate() {
        return timeDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setByUser(User byUser) {
        this.byUser = byUser;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimeDate(Date timeDate) {
        this.timeDate = timeDate;
    }
}
