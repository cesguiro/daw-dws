package es.cesguiro.movies.unit.domain.service;

import es.cesguiro.movies.controller.MovieController;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_errors.ResourceNotFoundException;
import es.cesguiro.movies.persistence.MovieRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieService.class)
class MovieServiceUnitTest {

    @Autowired
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    private List<Movie> movies = new ArrayList<>();

    @BeforeEach
    void setUp(){
        movies.clear();
        movies.add(new Movie(1, "Cadena perpetua", 1994, 142));
        movies.add(new Movie(2, "El padrino", 1972, 175));
        movies.add(new Movie(3, "El caballero Oscuro", 2008, 152));
    }

    @DisplayName("Test getAll() without pagination")
    @Test
    public void testGetMoviesListWithoutPagination() throws Exception {
        when(movieRepository.getAll(any(), any())).thenReturn(movies);
        //when(movieRepository.getTotalNumberOfRecords()).thenReturn(movies.size());

        /*List<Movie> actualMovies = movieService.getAll(Optional.empty(), Optional.empty());

        assertEquals(movies.size(), actualMovies.size());
        assertIterableEquals(movies, actualMovies);
        assertEquals(List<Movie>.class, );*/
        movieService.getAll(Optional.empty(), Optional.empty());
        verify(movieRepository, times(1)).getAll(any(), any());
        //verify(movieRepository, times(1)).getTotalNumberOfRecords();
    }

    @DisplayName("Test movie not found")
    @Test()
    public void testNotFound() throws Exception {
        final int nonExistentMovieId = 19;

        // Simulamos que el repositorio lanza ResourceNotFoundException para el ID proporcionado
        when(movieService.find(nonExistentMovieId)).thenThrow(new ResourceNotFoundException("Película con id " + nonExistentMovieId + " no encontrada"));
        //when(movieService.find(nonExistentMovieId)).thenThrow(ResourceNotFoundException.class);

    }

}