package com.movies.controller;

import com.movies.model.Movie;
import com.movies.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddBooksController {

  @Autowired
  MoviesService moviesService;

  @PostMapping("/add-movie")
  public ResponseEntity addMovie(@RequestBody Movie movie) {

    if (moviesService.isMovieInDatabase(movie.getTitle())) {
      return new ResponseEntity(HttpStatus.CONFLICT);
    }
    moviesService.addMovie(movie);

    return ResponseEntity.status(HttpStatus.CREATED).body("Movie was added with id: " + movie.getId());
  }
}
