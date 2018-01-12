package com.movies.controller;

import com.movies.model.Movie;
import com.movies.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@RestController
public class ViewBooksController {

  @Autowired
  MoviesService moviesService;

  @GetMapping("/movies/{id}")
  public ResponseEntity<Movie> getMovie(@PathVariable("id") Long movieId) {

    return Optional.ofNullable(moviesService.getMovie(movieId))
        .map(movie -> ResponseEntity.ok().body(movie))
        .orElse(ResponseEntity.notFound().build());
  }

  @GetMapping("/movies")
  public ResponseEntity<HashMap<Long, String>> getMovies() {

    return Optional.ofNullable(moviesService.getMovies())
        .map(movie -> ResponseEntity.ok().body(movie))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

}