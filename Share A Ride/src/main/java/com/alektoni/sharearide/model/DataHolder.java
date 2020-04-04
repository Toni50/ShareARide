/*
package com.alektoni.sharearide.model;

import com.alektoni.sharearide.model.*;
import lombok.Getter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
@Getter
public class DataHolder {


    public static final ArrayList<MyLocation> myLocations = new ArrayList<>();
    public static final ArrayList<MyNotification> myNotifications = new ArrayList<>();
    public static final ArrayList<Car> cars = new ArrayList<>();
    public static final ArrayList<Review> reviews = new ArrayList<>();
    public static final ArrayList<Ride> rides = new ArrayList<>();
    public static final ArrayList<RideRequest> rideRequests = new ArrayList<>();
    public static final ArrayList<RideRoute> rideRoute = new ArrayList<>();
    public static final ArrayList<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {


        createMyLocations();
        createMyNotifications();
        createCars();
        createReviews();
        createRides();
        createRideRequests();
        createRideRoutes();
        createUsers();




    }


    private void createUsers() {
        users.add(
                new User(1L, "Antonio", "Krstevski", "test@mail.com", "071200294")
        );
    }
    private void createRideRoutes() {
        rideRoute.add(new RideRoute(
       1L,
       1L,
                myLocations
        ));
    }

    private void createRideRequests() {
        rideRequests.add(new RideRequest(
        1L,
                new Ride(1L,
                        new Car (1L,
                                "Toyota",
                                1L,
                                true,
                                "black",
                                4,
                                true,
                                true,
                                true),
                        "25.06.2019",
                        "10:30",
                        new RideRoute(1L,
                                1L,
                                new ArrayList<MyLocation>()),
                        350,
                        new ArrayList<User>(),
                        false,
                        null),
     1L,
        2,
       "25.06.2019 10:30",
            ResponseStatus.ACCEPTED,
        "car broke down"
        ));
    }
    private void createRides() {
        rides.add(
                new Ride(1L,
                        new Car (1L,
                                "Toyota",
                                1L,
                                true,
                                "black",
                                4,
                                true,
                                true,
                                true),
                        "25.06.2019",
                        "10:30",
                        new RideRoute(1L,
                                1L,
                                new ArrayList<MyLocation>()),
                        350,
                        new ArrayList<User>(),
                        false,
                        null)
        );
    }

    private void createReviews() {
        reviews.add(new Review(
       1L,
                new User(1L, "Antonio", "Krstevski", "test@mail.com", "071200294"),
                new User(1L, "Antonio", "Krstevski", "test@mail.com", "071200294"),
       1L,
        "this is ride review",
      2,
        "23.06.2019"
        ));
    }

    private void createCars() {
        cars.add(new Car (
                1L,
                "Toyota",
                1L,
                true,
                "black",
                4,
                true,
                true,
                true
                ));
    }

    private void createMyNotifications() {
        myNotifications.add(new MyNotification(
        1L,
       "25.06.2019 10:30",
                new User(1L, "Antonio", "Krstevski", "test@mail.com", "071200294"),
       "Josh has accepted the ride request"
        ));
    }

    private void createMyLocations() {
        myLocations.add(new MyLocation(
                       1L,
                        1L,
                        41.995777,
                        21.431328,
       "MKD-Skopje-Centar"
                ));
    }
}
*/
