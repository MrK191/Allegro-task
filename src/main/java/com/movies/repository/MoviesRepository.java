package com.movies.repository;

import com.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movie, Long> {
  public Movie findById(Long id);

  public Movie findByTitle(String title);
}
