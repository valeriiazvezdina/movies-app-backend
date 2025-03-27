package com.zvezdval.movies_api.movie_details;

import com.zvezdval.movies_api.movie.Movie;
import com.zvezdval.movies_api.movie.MovieService;
import com.zvezdval.movies_api.review.Review;
import com.zvezdval.movies_api.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/movie-details")
public class MovieDetailsController {

    private final MovieService movieService;
    private final ReviewService reviewService;

    @Autowired
    public MovieDetailsController(MovieService movieService, ReviewService reviewService) {
        this.movieService = movieService;
        this.reviewService = reviewService;
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<?> getMovieWithReviews(@PathVariable String imdbId) throws Exception {

        CompletableFuture<Optional<Movie>> movieFuture = movieService.getMovieByIdAsync(imdbId);
        CompletableFuture<List<Review>> reviewsFuture = reviewService.getReviewsByMovieAsync(imdbId);

        CompletableFuture.allOf(movieFuture, reviewsFuture).join();

        Optional<Movie> movieOpt = movieFuture.get();
        List<Review> reviews = reviewsFuture.get();

        if (movieOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("movie", movieOpt.get());
        result.put("reviews", reviews);

        return ResponseEntity.ok(result);
    }
}
