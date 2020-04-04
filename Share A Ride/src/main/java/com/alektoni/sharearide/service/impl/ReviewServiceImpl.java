package com.alektoni.sharearide.service.impl;

import com.alektoni.sharearide.model.Review;
import com.alektoni.sharearide.repository.ReviewRepository;
import com.alektoni.sharearide.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {


    @Autowired
    ReviewRepository reviewRepository;


    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Optional<Review> findById(Long rideId) {
        return reviewRepository.findById(rideId);
    }

    @Override
    public void deleteById(Long rideId) {
        reviewRepository.deleteById(rideId);
    }

    @Override
    public ArrayList<Review> getAll() {
        Iterator<Review> it = reviewRepository.findAll().iterator();
        ArrayList<Review> tmp = new ArrayList<>();
        while (it.hasNext()){
            tmp.add(it.next());
        }
        return tmp;
    }

    @Override
    public List<Review> findAllReviewLeftForUserWithId(Long userId) {
        return reviewRepository.findAllReviewLeftForUserWithId(userId);
    }

    @Override
    public Review getReviewByUserForRide(Long userId, Long rideId) {
        return reviewRepository.getReviewByUserForRide(userId,rideId);
    }
}
