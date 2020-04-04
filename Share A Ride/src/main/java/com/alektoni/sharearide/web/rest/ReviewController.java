package com.alektoni.sharearide.web.rest;


import com.alektoni.sharearide.exceptions.NotFoundException;
import com.alektoni.sharearide.model.*;
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
@RequestMapping("/sharearide/rest/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private RideService rideService;

    @Autowired
    private RideRequestService rideRequestService;

    public ReviewController(){ }



//TODO-----PROD--------------------------------------------------------------------------------------------------------------------




    @RequestMapping(value = "/addReview", method = RequestMethod.POST, produces = "application/json")
    public void addReview(@RequestBody @Valid Review review) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optUser = userService.findByEmail(myUserDetails.getUsername());

        Review r = reviewService.getReviewByUserForRide(optUser.get().getUserId(),review.getRideId());
        if(r!=null){
            throw new NotFoundException("User has already left review for this ride");
        }
        if(optUser.isPresent()) {
            Optional<Ride> optRide = rideService.findById(review.getRideId());
            //Userot koj ostava review treba da e Passenger za toj ride
            if(optRide.get().getPassengers()
            .stream().filter(p -> p.getUserId().equals(optUser.get().getUserId())).findFirst().isPresent()
                    == false
            ){
                throw new NotFoundException("User was not passenger for ride with id "+review.getRideId());
            }

            if(optRide.isPresent()) {
                review.setLeftByUser(optUser.get());
                reviewService.save(review);
            }else{
                throw new NotFoundException("Ride with id "+review.getRideId()+" is not found");
            }
        }else{
            throw new NotFoundException("User with id "+review.getLeftByUser().getUserId()+" is not found");
        }
    }


    //still not used
    @RequestMapping(value="/getAllReviewsForUser", method= RequestMethod.GET)
    @ResponseBody
    public List<Review> getAllReviewsForUser(@RequestParam(name="userId") Long userId) {
        Optional<User> optUser = userService.findById(userId);
        if(optUser.isPresent()) {
            return reviewService.findAllReviewLeftForUserWithId(userId);
        }else{
            throw new NotFoundException("User with id "+userId+" is not found");
        }
    }




//TODO-----PROD--------------------------------------------------------------------------------------------------------------------



//TODO-----TEST--------------------------------------------------------------------------------------------------------------------

    @RequestMapping(value="/getAllReviews", method= RequestMethod.GET)
    @ResponseBody
    public ArrayList<Review> getAllReviews() {
        return reviewService.getAll();
    }


    @RequestMapping(value = "/deleteReview", method = RequestMethod.DELETE)
    public void deleteReview(@RequestParam(name="id", required=true) Long id) {
        Optional<Review> optReview = reviewService.findById(id);
        if(optReview.isPresent()) {
            reviewService.deleteById(id);
        }else {
            throw new NotFoundException("Review with id "+id+" is not found");
        }
    }

//TODO-----TEST--------------------------------------------------------------------------------------------------------------------






}
