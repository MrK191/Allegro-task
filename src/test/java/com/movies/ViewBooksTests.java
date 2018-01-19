package com.movies;

import com.movies.controller.ViewBooksController;
import com.movies.model.ListOfMovies;
import com.movies.model.Movie;
import com.movies.service.MoviesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ViewBooksController.class)
public class ViewBooksTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MoviesService moviesService;

  @Test
  public void testGetMovie() throws Exception {
    given(this.moviesService.getMovie((long) 1))
        .willReturn(new Movie("Star Wars", "2h", "Nice movie"));

    this.mockMvc.perform(MockMvcRequestBuilders.get("/movies/1"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("title").value("Star Wars"));
  }

  @Test
  public void testGetAllMovies() throws Exception {
    ArrayList<ListOfMovies> list = this.createListOfMovies();

    given(this.moviesService.getMovies())
        .willReturn(list);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].movieTitle").value("Star Wars"));
  }

  private ArrayList<ListOfMovies> createListOfMovies() {
    ListOfMovies firstMovie = new ListOfMovies();
    firstMovie.setId(1L);
    firstMovie.setMovieTitle("Star Wars");

    ListOfMovies secondMovie = new ListOfMovies();
    secondMovie.setId(2L);
    secondMovie.setMovieTitle("Conan");

    ArrayList<ListOfMovies> listOfMovies = new ArrayList<>();
    listOfMovies.add(firstMovie);
    listOfMovies.add(secondMovie);
    return listOfMovies;
  }

}
