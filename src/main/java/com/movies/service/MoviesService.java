package com.movies.service;

import com.movies.model.Movie;
import com.movies.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Optional;

@Service
public class MoviesService {

  @Autowired
  MoviesRepository moviesRepository;

  public Movie getMovie(Long id) {

    return moviesRepository.findById(id);
  }

  public HashMap<Long, String> getMovies() {
    HashMap<Long, String> movies = new HashMap<>();

    moviesRepository.findAll().forEach(movie -> movies.put(movie.getId(), movie.getTitle()));

    if (movies.isEmpty()) {
      return null;
    }
    return movies;
  }

  @Transactional
  public Long addMovie(Movie movie) {
    moviesRepository.save(movie);

    return movie.getId();
  }

  public boolean isMovieInDatabase(String title) {
    return Optional.ofNullable(moviesRepository.findByTitle(title))
        .isPresent();
  }
}
