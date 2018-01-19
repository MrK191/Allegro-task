package com.movies.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Movies")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne(cascade = CascadeType.ALL)
  @PrimaryKeyJoinColumn
  private ListOfMovies listOfMovies;

  private String title;

  private String movieLength;

  private String movieDescription;

  public Movie() {
  }

  public Movie(String title, String movieLength, String movieDescription) {
    this.title = title;
    this.movieLength = movieLength;
    this.movieDescription = movieDescription;
  }

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getMovieLength() {
    return movieLength;
  }

  public void setMovieLength(String movieLength) {
    this.movieLength = movieLength;
  }

  public String getMovieDescription() {
    return movieDescription;
  }

  public void setMovieDescription(String movieDescription) {
    this.movieDescription = movieDescription;
  }
}
