package com.zvezdval.movies_api.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody ReviewDto payload) {
        return new ResponseEntity<>(reviewService.createReview(payload.reviewBody(), payload.imdbId()), HttpStatus.CREATED);
    }
}
