package com.lcwd.rating.service;

import com.lcwd.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating create(Rating rating);

    List<Rating> getRatings();

    List<Rating> getRatingByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

    void deleteRating(String id);

    Rating updateRating(String ratingId, Rating rating);

}
