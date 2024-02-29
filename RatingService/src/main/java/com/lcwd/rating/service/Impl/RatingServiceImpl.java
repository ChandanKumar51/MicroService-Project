package com.lcwd.rating.service.Impl;

import com.lcwd.rating.entity.Rating;
import com.lcwd.rating.exception.ResourceNotFoundException;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Override
    public Rating create(Rating rating) {
        rating.setRatingId(UUID.randomUUID().toString());
        Rating save = ratingRepository.save(rating);
        return save;
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
       return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public void deleteRating(String ratingId) {
        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("Rating with given id not found !!"));
        ratingRepository.delete(rating);

    }

    @Override
    public Rating updateRating(String ratingId, Rating rating) {
        return ratingRepository.findById(ratingId)
                .map(existingRating -> {
                    existingRating.setRatingId(rating.getRatingId());
                    existingRating.setRating(rating.getRating());
                    existingRating.setFeedback(rating.getFeedback());

                    return ratingRepository.save(existingRating);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Hotel with id " + ratingId + " not found"));
    }
}
