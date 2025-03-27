package com.zvezdval.movies_api.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }
    public Optional<Movie> findMovieByImdbId(String imdbId) {
        return movieRepository.findByImdbId(imdbId);
    }

    @Async
    public CompletableFuture<Optional<Movie>> getMovieByIdAsync(String imdbId) {
        return CompletableFuture.completedFuture(movieRepository.findByImdbId(imdbId));
    }
}