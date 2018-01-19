package com.movies.repository;

import com.movies.model.ListOfMovies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListOfMoviesRepository extends JpaRepository<ListOfMovies, Long> {
}
