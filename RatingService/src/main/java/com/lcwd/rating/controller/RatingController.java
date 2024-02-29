package com.lcwd.rating.controller;

import com.lcwd.rating.entity.Rating;
import com.lcwd.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/save")
    public ResponseEntity<Rating> create(@RequestBody Rating rating){
        Rating rating1 = ratingService.create(rating);
        //return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
        return new ResponseEntity<>(rating1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(String userId) {
        List<Rating> ratingByUserId = ratingService.getRatingByUserId(userId);
        return new ResponseEntity<>(ratingByUserId, HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId) {
        List<Rating> ratingByHotelId = ratingService.getRatingByHotelId(hotelId);
        return new ResponseEntity<>(ratingByHotelId,HttpStatus.OK);
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<Rating> updateRating(@PathVariable String ratingId, @RequestBody Rating rating) {
        Rating updatedRating = ratingService.updateRating(ratingId, rating);
        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void> deleteRating(@PathVariable String ratingId) {
        ratingService.deleteRating(ratingId);
        return ResponseEntity.noContent().build();
    }

}
