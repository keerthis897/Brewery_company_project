package com.tc.brewery.service;

import com.tc.brewery.entity.Beer;
import com.tc.brewery.entity.Rating;
import com.tc.brewery.entity.User;
import com.tc.brewery.repository.BeerRepository;
import com.tc.brewery.repository.RatingRepository;
import com.tc.brewery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Void> addRating(int beerId, long userId, int ratingValue, String review) {
        Beer beer = beerRepository.findById(beerId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (beer == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Rating rating = new Rating();
        rating.setBeer(beer);
        rating.setUser(user);
        rating.setRating(ratingValue);
        rating.setReview(review);

        ratingRepository.save(rating);

        return ResponseEntity.ok().build();
    }
}

