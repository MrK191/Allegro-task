package com.movies.service;

import com.movies.model.Movie;
import com.movies.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {

  @Autowired
  MoviesRepository moviesRepository;

  public Movie getMovie(Long id) {

    return moviesRepository.findOne(id);
  }

  public List<String> getMovies() {
    ArrayList<String> movieList = new ArrayList<>();
    moviesRepository.findAll().forEach(movie -> movieList.add(movie.getTitle()));

    return movieList;
  }

  @Transactional
  public Long addMovie(Movie movie) {
    return moviesRepository.save(movie).getId();
  }

  public boolean isMovieInDatabase(String title) {
    return Optional.ofNullable(moviesRepository.findByTitle(title))
        .isPresent();
  }
}
