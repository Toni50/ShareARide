package com.alektoni.sharearide.model;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Entity
public class RideRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "routeId")
    private List<RouteLocation> locations;

    public RideRoute() {
    }

    public RideRoute(List<RouteLocation> locations) {
        this.locations = locations;
    }

    public Long getId() {
        return id;
    }

    public List<RouteLocation> getLocations() {
        return locations;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocations(List<RouteLocation> locations) {
        this.locations = locations;
    }
}
