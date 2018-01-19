package com.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movies.controller.MoviesController;
import com.movies.model.Movie;
import com.movies.service.MoviesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(MoviesController.class)
public class AddBooksTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MoviesService moviesService;

  @Autowired
  private ObjectMapper mapper;

  @Test
  public void addBookToRepository() throws Exception {
    String jsonMovie = mapper.writeValueAsString(new Movie("Star Wars", "2h", "Nice movie"));

    this.mockMvc.perform(MockMvcRequestBuilders.post("/add-movie")
        .content(jsonMovie)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

}
