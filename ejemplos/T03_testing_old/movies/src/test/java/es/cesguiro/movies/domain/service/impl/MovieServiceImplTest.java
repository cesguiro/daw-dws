package es.cesguiro.movies.domain.service.impl;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.persistence.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService = new MovieServiceImpl();

    /*@InjectMocks
    private MovieServiceImpl movieService = new MovieServiceImpl();*/


    List<Movie> movies = List.of(
            new Movie(1, "Cadena perpetua", 142,1994),
            new Movie(2, "El padrino", 175,1972),
            new Movie(3, "El caballero oscuro", 152,2008),
            new Movie(4, "El padrino (parte II)", 202,1974),
            new Movie(5, "12 hombres sin piedad", 96,1957)
    );

    @DisplayName("Test getAll()")
    @Test
    public void testGetAllMovies(){
        when(movieRepository.getAll()).thenReturn(movies);
        assertEquals(movies, this.movieService.getAll());
    }

}