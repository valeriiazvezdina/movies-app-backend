package com.zvezdval.movies_api.review;

import com.zvezdval.movies_api.movie.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final MongoTemplate mongoTemplate;

    public Review createReview(String review, String imdbId) {
        Review newReview = Review.builder().body(review).build();
        Review reviewObject = reviewRepository.insert(newReview);

        mongoTemplate.update(Movie.class)
                     .matching(Criteria.where("imdbId").is(imdbId))
                     .apply(new Update().push("reviewIds").value(reviewObject))
                     .first();

        return reviewObject;
    }

    public Optional<Review> findReviewByImdbId(String imdbId) {
        return reviewRepository.findById(imdbId);
    }
}
