package es.cesguiro.movies.unit;

import es.cesguiro.movies.controller.MovieController;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_errors.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
public class MovieControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    /*@Autowired
    private Mock mock;*/

    /*
     * ¿Sustituye el Bean MovieService por éste?
     */
    @MockBean
    private MovieService movieService;

    private List<Movie> movies = new ArrayList<>();

    @BeforeEach
    void setUp(){
        movies.add(new Movie(1, "Cadena perpetua", 1994, 142));
        movies.add(new Movie(2, "El padrino", 1972, 175));
        movies.add(new Movie(3, "El caballero Oscuro", 2008, 152));
    }

    @DisplayName("Test getAll()")
    @Test
    public void testGetMoviesList() throws Exception {
        when(movieService.getAll(Optional.empty())).thenReturn(movies);
        mockMvc.perform(get("/movies"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(movies.size())));
    }

    @DisplayName("Test find()")
    @Test
    public void testFind() throws Exception {
        final int id = 1;
        final Movie movie = new Movie(1, "Cadena perpetua", 1994, 142);

        when(movieService.find(id)).thenReturn(movie);
        mockMvc.perform(get("/movies/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(movie.getTitle())))
                .andExpect(jsonPath("$.year", is(movie.getYear())));
    }

    @DisplayName("Test movie not found")
    @Test()
    public void testNotFound() throws Exception {
        final int id = 19;

        // Simulamos que el servicio lanza ResourceNotFoundException para el ID proporcionado
        when(movieService.find(id)).thenThrow(new ResourceNotFoundException("Película no encontrada"));

        mockMvc.perform(get("/movies/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("Película no encontrada", result.getResolvedException().getMessage()));
    }


}
