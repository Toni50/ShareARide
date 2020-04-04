package com.alektoni.sharearide.web.rest;



import com.alektoni.sharearide.exceptions.NotFoundException;
import com.alektoni.sharearide.model.*;
import com.alektoni.sharearide.model.dto.FindARideModel;

import com.alektoni.sharearide.security.MyUserDetails;
import com.alektoni.sharearide.service.*;
import com.alektoni.sharearide.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sharearide/rest/ride")
public class RideController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private RideRouteService rideRouteService;

    @Autowired
    private RideService rideService;

    public RideController(){ }

//TODO-----PROD--------------------------------------------------------------------------------------------------------------------

    //Offer A Ride-> Offer Ride->  adds ride to db without Passengers
    @RequestMapping(value = "/addRide", method = RequestMethod.POST, produces = "application/json")
    public void addRide(@RequestBody @Valid Ride ride) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());

        if (optUser.isPresent()) {
            Optional<Car> optCar = carService.getActiveCarForUserWithId(optUser.get().getUserId());
            if(optCar.isPresent()) {
                CarRideDetails carRideDetails = new CarRideDetails(
                        optCar.get().getCarId(),
                        optCar.get().getDriver(),
                        optCar.get().getName(),
                        optCar.get().getActive(),
                        optCar.get().getColor(),
                        optCar.get().getFreeSeats(),
                        optCar.get().getPetsAllowed(),
                        optCar.get().getSmokingAllowed(),
                        optCar.get().getAirConditioner(),
                        optCar.get().getPictureUrl()
                );
                ride.setCarRideDetails(carRideDetails);
                rideService.save(ride);
            }else{
                throw new NotFoundException("User does not have active car");
            }
        } else {
            throw new NotFoundException("User with mail " + myUserDetails.getUsername() + " is not found");
        }
    }

    @RequestMapping(value="/findaride", method= RequestMethod.POST)
    @ResponseBody
    public List<Ride> findARide(@RequestBody @Valid FindARideModel findARideModel) {
        //todo test this method
        //todo prebaraj po mesto a ne samo po vreme
        /*
        ride.startTime = 2020 01 01 13:00
        starttime = 2020 01 01 00:00
        endTime= 2020 01 01 23:59
        */
        Calendar cal = Calendar.getInstance();
        cal.setTime(findARideModel.getStartTime());

        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        Date starttime = cal.getTime();

        cal.set(Calendar.HOUR_OF_DAY,23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);

        Date endTime = cal.getTime();;

        List<Ride> result = new ArrayList<>();
        List<Ride> dateRides = rideService.findRides(starttime, endTime);


        com.alektoni.sharearide.model.dto.Location modelLocStart = findARideModel.getLocations().get(0);
        com.alektoni.sharearide.model.dto.Location modelLocEnd = findARideModel.getLocations().get(findARideModel.getLocations().size()-1);


        String []modelLocStartData = modelLocStart.getLocationCode().split("&");
        String []modelLocEndtData = modelLocEnd.getLocationCode().split("&");

        String locStartCountryCode = modelLocStartData[0];
        String locStartCity = modelLocStartData[1];
        String locStartLocality = modelLocStartData[2];
        String locStartStreet = modelLocStartData[3];

        String locEndCountryCode = modelLocEndtData[0];
        String locEndCity = modelLocEndtData[1];
        String locEndLocality = modelLocEndtData[2];
        String locEndStreet = modelLocEndtData[3];

        for(int i=0;i<dateRides.size();i++){
            Ride r = dateRides.get(i);
            RouteLocation startLoc = r.getRideRoute().getLocations().get(0);
            RouteLocation endLoc = r.getRideRoute().getLocations().get(r.getRideRoute().getLocations().size()-1);


            String []rideLocStartData = startLoc.getLocationCode().split("&");
            String []rideLocEndtData = endLoc.getLocationCode().split("&");

            String rideLocStartCountryCode = rideLocStartData[0];
            String rideLocStartCity = rideLocStartData[1];
            String rideLocStartLocality = rideLocStartData[2];
            String rideLocStartStreet = rideLocStartData[3];

            String rideLocEndCountryCode = rideLocEndtData[0];
            String rideLocEndCity = rideLocEndtData[1];
            String rideLocEndLocality = rideLocEndtData[2];
            String rideLocEndtStreet = rideLocEndtData[3];


            //pocetokot i krajot na patuvanjeto se isti
            //odnosno CountryCode i City da bidat isti
            if(locStartCountryCode.equals(rideLocStartCountryCode) &&
                    locStartCity.equals(rideLocStartCity)          &&
                    locEndCountryCode.equals(rideLocEndCountryCode) &&
                    locEndCity.equals(rideLocEndCity)){
                result.add(r);
            } }



        return result;
    }

//TODO-----PROD--------------------------------------------------------------------------------------------------------------------


//TODO-----TEST--------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value="/getAllRides", method= RequestMethod.GET)
    @ResponseBody
    public ArrayList<Ride> getAllRides() {
        return  rideService.getAll();
    }




//TODO-----TEST--------------------------------------------------------------------------------------------------------------------




}
