package es.cesguiro.movies.unit;

import es.cesguiro.movies.controller.MovieController;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
public class MovieControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    /*@Autowired
    private Mock mock;*/

    //@Mock
    @MockBean
    private MovieService movieService;

    private List<Movie> movies = List.of(
            new Movie(1, "Cadena perpetua", 1994, 142),
            new Movie(2, "El padrino", 1972, 175)
            );
    /*
    * ¿Sustituye el Bean MovieService por éste?
    */
    /*@MockBean
    private MovieService movieService;*/

    private Movie movie;

    /*@InjectMocks
    private MovieService movieService = new MovieServiceImpl();*/

    @DisplayName("Test getAll()")
    @Test
    public void testGetMoviesList() throws Exception {
        //when(movieService.getAll()).thenReturn(Collections.singletonList(movie));
        when(movieService.getAll()).thenReturn(this.movies);
        //mock.
        mockMvc.perform(get("/movies"))
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"));
        //assertEquals(movies, this.movieService.getAll());
    }

    /*@Test
    public void testGetOrdersList() throws Exception {
        when(orderService.getOrders()).thenReturn(Collections.singletonList(order));
        mockMvc.perform(get("/api/orders"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$").isArray());
    }*/

}
