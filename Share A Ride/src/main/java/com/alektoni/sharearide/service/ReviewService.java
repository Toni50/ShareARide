package com.alektoni.sharearide.service;



import com.alektoni.sharearide.model.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Review save(Review review);

    Optional<Review> findById(Long aLong);

    void deleteById(Long aLong);

    ArrayList<Review> getAll();

    List<Review> findAllReviewLeftForUserWithId(Long userId);

    Review getReviewByUserForRide(Long userId, Long rideId);
}
