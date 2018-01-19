package com.movies.service;

import com.movies.model.ListOfMovies;
import com.movies.model.Movie;
import com.movies.repository.ListOfMoviesRepository;
import com.movies.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {

  @Autowired
  MoviesRepository moviesRepository;
  @Autowired
  ListOfMoviesRepository listOfMoviesRepository;

  public Movie getMovie(Long id) {

    return moviesRepository.findById(id);
  }

  public List<ListOfMovies> getMovies() {

    return listOfMoviesRepository.findAll();
  }

  @Transactional
  public Long addMovie(Movie movie) {

    ListOfMovies newMovie = new ListOfMovies();
    newMovie.setMovieTitle(movie.getTitle());
    newMovie.setMovie(movie);

    moviesRepository.save(movie);
    listOfMoviesRepository.save(newMovie);

    return movie.getId();
  }

  public boolean isMovieInDatabase(String title) {
    return Optional.ofNullable(moviesRepository.findByTitle(title))
        .isPresent();
  }
}
