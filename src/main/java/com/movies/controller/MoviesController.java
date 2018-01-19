package com.movies.controller;

import com.movies.model.Movie;
import com.movies.service.MoviesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MoviesController {

  private final MoviesService moviesService;

  public MoviesController(MoviesService moviesService) {
    this.moviesService = moviesService;
  }

  @PostMapping("/add-movie")
  public ResponseEntity addMovie(@RequestBody Movie movie) {

    if (moviesService.isMovieInDatabase(movie.getTitle())) {
      return new ResponseEntity(HttpStatus.CONFLICT);
    }
    moviesService.addMovie(movie);

    return ResponseEntity.status(HttpStatus.CREATED).body("Movie was added with id: " + movie.getId());
  }

  @GetMapping(value = "/movies/{id}", produces = "application/json")
  public ResponseEntity<Movie> getMovie(@PathVariable("id") Long movieId) {

    return Optional.ofNullable(moviesService.getMovie(movieId))
        .map(movie -> ResponseEntity.ok().body(movie))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping(value = "/movies", produces = "application/json")
  public ResponseEntity<List<String>> getMovies() {

    return Optional.ofNullable(moviesService.getMovies())
        .map(movie -> ResponseEntity.ok().body(movie))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

}
