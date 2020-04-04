package com.alektoni.sharearide.web.rest;

import com.alektoni.sharearide.exceptions.NotFoundException;

import com.alektoni.sharearide.model.*;

import com.alektoni.sharearide.model.vm.MyPage;
import com.alektoni.sharearide.security.MyUserDetails;
import com.alektoni.sharearide.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sharearide/rest/rideRequest")
public class RideRequestController {

    @Autowired
    private RideRequestService rideRequestService;

    @Autowired
    private UserService userService;

    @Autowired
    private RideService rideService;

    @Autowired
    private CarRideDetailsService carRideDetailsService;

    @Autowired
    private MyNotificationService myNotificationService;

    public RideRequestController(){ }




//TODO-----PROD--------------------------------------------------------------------------------------------------------------------


    //Find A Ride-> Request Ride -> Confirm Request (passengers makes ride request)
    @RequestMapping(value = "/addRideRequest", method = RequestMethod.POST, produces = "application/json")
    public RideRequest addRideRequest(@RequestBody @Valid RideRequest rideRequest) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());

        if(optUser.isPresent()){
            Optional<Ride> optRide = rideService.findById(rideRequest.getRide().getRideId());
            if(optRide.isPresent()){
                rideRequest.setRequestByUser(optUser.get());
                rideRequest.setResponseStatus(RideRequestResponseStatus.PENDING);
                return rideRequestService.save(rideRequest);
            }else{
                throw  new NotFoundException("Ride with id "+rideRequest.getRide().getRideId()+" is not found");
            }
        }else {
            throw new NotFoundException("User with mail "+myUserDetails.getUsername()+" is not found");
        }
    }



    //My Rides-> History -> Shows all requests that this user(passenger) has made but rides are in the past
    @RequestMapping(value="/getHistoryRides", method= RequestMethod.GET)
    @ResponseBody
    public MyPage<RideRequest> getHistoryRides(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());

        if(optUser.isPresent()){
            return rideRequestService.getHistoryRidesForUser(optUser.get().getUserId(),page,pageSize);
        }else {
            throw new NotFoundException("User with mail "+myUserDetails.getUsername()+" is not found");
        }
    }



    //My Rides-> Upcoming   -> Shows all requests that this user(passenger) has made
    @RequestMapping(value="/getUpcomingRides", method= RequestMethod.GET)
    @ResponseBody
    public MyPage<RideRequest> getUpcomingRides(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());
        if(optUser.isPresent()){
            return rideRequestService.getUpcomingRidesForUser(optUser.get().getUserId(),page,pageSize);
        }else {
            throw new NotFoundException("User with mail "+myUserDetails.getUsername()+" is not found");
        }
    }


    //My Rides-> Requests   -> Shows all requests that are sent to this user(driver)
    @RequestMapping(value="/getRideRequests", method= RequestMethod.GET)
    @ResponseBody
    public MyPage<RideRequest> getRideRequests(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());

        if(optUser.isPresent()){
            return rideRequestService.getRideRequestToUserId(optUser.get().getUserId(),page,pageSize);
        }else {
            throw new NotFoundException("User with mail "+myUserDetails.getUsername()+" is not found");
        }
    }

    // //My Rides-> Requests -> Respond -> Reject (driver rejects the passenger's ride request)
    @RequestMapping(value = "/reject", method = RequestMethod.PUT)
    public void rejectRideRequest(@RequestParam(name="requestId") Long requestId) {
        //todo ne mora volku proverki
        Optional<RideRequest> optRideRequest = rideRequestService.findById(requestId);
        if(optRideRequest.isPresent()){
            Optional<User> optUser = userService.findById(optRideRequest.get().getRequestByUser().getUserId());
            if(optUser.isPresent()){
                Optional<Ride> optRide = rideService.findById(optRideRequest.get().getRide().getRideId());
                if(optRide.isPresent()){
                    if(optRideRequest.get().getResponseStatus().equals(RideRequestResponseStatus.PENDING)){
                        optRideRequest.get().setResponseStatus(RideRequestResponseStatus.REJECTED);
                        rideRequestService.save(optRideRequest.get());

                        myNotificationService.save(optRideRequest.get());
                    }else{
                        throw new NotFoundException("Can not change status of accepted/rejected request");
                    }
                }else{
                    throw new NotFoundException("Ride with id "+optRideRequest.get().getRide().getRideId()+" is not found");
                }
            }else{
                throw new NotFoundException("User with id "+optRideRequest.get().getRequestByUser().getUserId()+" is not found");
            }

        }else{
            throw new NotFoundException("RideRequest with id "+requestId+" is not found");
        }
    }

    // //My Rides-> Requests -> Respond -> Accept (driver accepts the passenger's ride request)
    @RequestMapping(value = "/accept", method = RequestMethod.PUT)
    public void acceptRideRequest(@RequestParam(name="requestId") Long requestId) {
        //todo ne mora volku proverki
        Optional<RideRequest> optRideRequest = rideRequestService.findById(requestId);
        if(optRideRequest.isPresent()){
            Optional<User> optUser = userService.findById(optRideRequest.get().getRequestByUser().getUserId());
            if(optUser.isPresent()){
                Optional<Ride> optRide = rideService.findById(optRideRequest.get().getRide().getRideId());
                if(optRide.isPresent()){
                    if(optRideRequest.get().getSeatsRequested() <= optRide.get().getCarRideDetails().getFreeSeats()){
                        if(optRideRequest.get().getResponseStatus().equals(RideRequestResponseStatus.PENDING)){

                            List<User> passengers = optRideRequest.get().getRide().getPassengers();
                            boolean passengersIncluded = false;
                            for(User p:passengers){
                                if(p.getUserId()==optRideRequest.get().getRequestByUser().getUserId()){
                                    passengersIncluded=true;
                                }
                            }

                            if(passengersIncluded==false){
                                optRide.get().getPassengers().add(optRideRequest.get().getRequestByUser());
                                rideService.save(optRide.get());
                            }

                            CarRideDetails carRideDetails = optRide.get().getCarRideDetails();
                            carRideDetails.setFreeSeats(carRideDetails.getFreeSeats()-optRideRequest.get().getSeatsRequested());
                            carRideDetailsService.save(carRideDetails);

                            optRideRequest.get().setResponseStatus(RideRequestResponseStatus.ACCEPTED);
                            rideRequestService.save(optRideRequest.get());

                            myNotificationService.save(optRideRequest.get());
                        }else{
                            throw new NotFoundException("Can not change status of accepted/rejected request");
                        }
                    } else {
                        throw new NotFoundException("Not enough free seats");
                    }
                }else{
                    throw new NotFoundException("Ride with id "+optRideRequest.get().getRide().getRideId()+" is not found");
                }
            }else{
                throw new NotFoundException("User with id "+optRideRequest.get().getRequestByUser().getUserId()+" is not found");
            }
        }else{
            throw new NotFoundException("RideRequest with id "+requestId+" is not found");
        }
    }


//TODO-----PROD--------------------------------------------------------------------------------------------------------------------


//TODO-----TEST--------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value = "/getAllRideRequest", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<RideRequest> getAllRideRequest() {
        return rideRequestService.getAll();
    }


    @RequestMapping(value="/getRideRequestById", method= RequestMethod.GET)
    @ResponseBody
    public RideRequest getRideRequestById(@RequestParam(name="id") Long id) {
        Optional<RideRequest> optRideRequest = rideRequestService.findById(id);
        if(optRideRequest.isPresent()) {
            return optRideRequest.get();
        }else{
            throw new NotFoundException("RideRequest with id "+id+" is not found");
        }
    }

    @RequestMapping(value = "/deleteRideRequest", method = RequestMethod.DELETE)
    public void deleteRideRequest(@RequestParam(name="id", required=true) Long id) {
        Optional<RideRequest> optRideRequest = rideRequestService.findById(id);
        if(optRideRequest.isPresent()) {
            rideRequestService.deleteById(id);
        }else {
            throw  new NotFoundException("RideRequest with id "+id+" is not found");
        }
    }
//TODO-----TEST--------------------------------------------------------------------------------------------------------------------


}
