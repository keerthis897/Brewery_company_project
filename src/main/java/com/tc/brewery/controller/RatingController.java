package com.tc.brewery.controller;

import com.tc.brewery.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/ratings/{beerId}/{userId}")
    public ResponseEntity<Void> addRating(
            @PathVariable int beerId,
            @PathVariable int userId,
            @RequestBody Map<String, Object> ratingData) {

        int ratingValue = (int) ratingData.get("ratingValue");
        String review = (String) ratingData.get("review");

        return ratingService.addRating(beerId, userId, ratingValue, review);
    }
}
