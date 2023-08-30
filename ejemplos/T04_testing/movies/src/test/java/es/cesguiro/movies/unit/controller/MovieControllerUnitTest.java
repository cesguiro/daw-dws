package es.cesguiro.movies.unit.controller;

import es.cesguiro.movies.controller.MovieController;
import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.domain.service.MovieService;
import es.cesguiro.movies.http_errors.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
@ActiveProfiles("test")
class MovieControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private List<Movie> movies = new ArrayList<>();

    @Value("${page.size}")
    private int LIMIT;

    //private final int LIMIT = 3;

    @BeforeEach
    void setUp(){
        movies.clear();
        movies.add(new Movie(1, "Cadena perpetua", 1994, 142));
        movies.add(new Movie(2, "El padrino", 1972, 175));
        movies.add(new Movie(3, "El caballero Oscuro", 2008, 152));
        movies.add(new Movie(4, "Pulp Fiction", 1994, 154));
        movies.add(new Movie(5, "Senderos de gloria", 1957, 88));
        movies.add(new Movie(6, "Forrest Gump", 1994, 142));
        movies.add(new Movie(7, "Matrix", 1999, 136));
        movies.add(new Movie(8, "El crepúsculo de los dioses", 1950, 110));
    }

    @DisplayName("Test list movies without pagination scenarios")
    @ParameterizedTest(name = "URL: {1}")
    @CsvSource({
            "/movies, No pagination",           // No pagination
            "/movies?pageSize=3, Pagination with pageSize only" // Pagination with pageSize only
    })
    public void testGetMoviesListWithoutPagination(String url, String diplayName) throws Exception {
        when(movieService.getAll(any(), any())).thenReturn(movies);
        when(movieService.getTotalNumberOfRecords()).thenReturn(movies.size());
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.['total records']", is(movies.size())))
                .andExpect(jsonPath("$.data", hasSize(movies.size())))
                .andExpect(jsonPath("$.data[0].title", is(movies.get(0).getTitle())))
                .andExpect(jsonPath("$.page").doesNotExist())
                .andExpect(jsonPath("$.['page size']").doesNotExist())
                .andExpect(jsonPath("$.['total pages']").doesNotExist())
                .andExpect(jsonPath("$.next").doesNotExist())
                .andExpect(jsonPath("$.previous").doesNotExist());
    }

    /*@DisplayName("Test getAll() without pagination")
    @Test
    public void testGetMoviesListWithoutPagination() throws Exception {
        when(movieService.getAll(any(), any())).thenReturn(movies);
        when(movieService.getTotalNumberOfRecords()).thenReturn(movies.size());
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.['total records']", is(movies.size())))
                .andExpect(jsonPath("$.data", hasSize(movies.size())))
                .andExpect(jsonPath("$.data[0].title", is(movies.get(0).getTitle())));
    }

    @DisplayName("Test getAll() with pagination and no page size")
    @Test
    public void testGetMoviesListWithPaginationWithoutPageSize() throws Exception {
        int page = 2;
        List<Movie> paginatedMovies = movies.subList((page - 1) * LIMIT, Math.min(page * LIMIT, movies.size()));

        when(movieService.getAll(any(), any())).thenReturn(paginatedMovies);
        when(movieService.getTotalNumberOfRecords()).thenReturn(movies.size());
        mockMvc.perform(get("/movies").param("page", String.valueOf(page)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.['total records']", is(movies.size())))
                .andExpect(jsonPath("$.data", hasSize(paginatedMovies.size())))
                .andExpect(jsonPath("$.data[0].title", is(paginatedMovies.get(0).getTitle())));
    }

    @DisplayName("Test getAll() with pagination and page size")
    @Test
    public void testGetMoviesListWithPaginationAndPageSize() throws Exception {
        int page = 2;
        int pageSize = 3;
        List<Movie> paginatedMovies = movies.subList((page - 1) * pageSize, Math.min(page * pageSize, movies.size()));

        when(movieService.getAll(any(), any())).thenReturn(paginatedMovies);
        when(movieService.getTotalNumberOfRecords()).thenReturn(movies.size());

        mockMvc.perform(get("/movies").param("page", String.valueOf(page)).param("pageSize", String.valueOf(pageSize)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.['total records']", is(movies.size())))
                .andExpect(jsonPath("$.data", hasSize(pageSize)))
                .andExpect(jsonPath("$.data[0].title", is(paginatedMovies.get(0).getTitle())));
    }

    @DisplayName("Test getAll() without pagination and with page size")
    @Test
    public void testGetMoviesListWithoutPaginationAndWithPageSize() throws Exception {
        int pageSize = 2;
        when(movieService.getAll(any(), any())).thenReturn(movies);
        when(movieService.getTotalNumberOfRecords()).thenReturn(movies.size());

        mockMvc.perform(get("/movies").param("pageSize", String.valueOf(pageSize)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.['total records']", is(movies.size())))
                .andExpect(jsonPath("$.data", hasSize(movies.size())))
                .andExpect(jsonPath("$.data[0].title", is(movies.get(0).getTitle())));
    }*/

    @DisplayName("Test getAll() with different pagination scenarios")
    @ParameterizedTest
    //No se puede utilizar LIMIT, ya que no es una constante (no es final)
    /*@CsvSource({
            "/movies, 0, 8",           // No pagination
            "/movies?page=2, 2, 8",    // Pagination with page
            "/movies?page=2&pageSize=2, 2, 8", // Pagination with page and pageSize
            "/movies?pageSize=3, 0, 8" // Pagination with pageSize only
    })*/
    /*@CsvSource({
            "/movies, 0, " + LIMIT,           // No pagination
            "/movies?page=2, 2, " + LIMIT,    // Pagination with page
            "/movies?page=2&pageSize=2, 2, " + LIMIT, // Pagination with page and pageSize
            "/movies?pageSize=3, 0, " + LIMIT // Pagination with pageSize only
    })*/
    @CsvSource({
            "/movies?page=2, 2, 3",    // Pagination with page
            "/movies?page=3&pageSize=2, 3, 2", // Pagination with page and pageSize
    })
    public void testGetMoviesListWithDifferentPagination(String url, int expectedPage, int expectedSize) throws Exception {
        //when(movieService.getAll(any(), any())).thenReturn(movies);
        List<Movie> paginatedMovies = movies.subList((expectedPage - 1) * expectedSize, Math.min(expectedPage * expectedSize, movies.size()));

        when(movieService.getAll(any(), any())).thenReturn(paginatedMovies);

        when(movieService.getTotalNumberOfRecords()).thenReturn(movies.size());

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.['total records']", is(movies.size())))
                .andExpect(jsonPath("$.data", hasSize(expectedSize)))
                .andExpect(jsonPath("$.page", is(expectedPage)))
                .andExpect(jsonPath("$.['page size']", is(expectedSize)))
                .andExpect(jsonPath("$.['total pages']", is(3)))
                .andExpect(jsonPath("$.next").exists())
                .andExpect(jsonPath("$.previous").exists());
    }

    /*@DisplayName("Test getAll() without pagination scenarios")
    @ParameterizedTest
    @CsvSource({
            "/movies, 1",           // No pagination
            "/movies?pageSize=3, 1" // Pagination with pageSize only
    })
    public void testGetMoviesListWithoutPagination(String url, int expectedSize) throws Exception {
        when(movieService.getAll(any(), any())).thenReturn(movies);
        when(movieService.getTotalNumberOfRecords()).thenReturn(movies.size());

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data", hasSize(movies.size())))
                .andExpect(jsonPath("$.['total records']", is(movies.size())))
                .andExpect(jsonPath("$.page").doesNotExist())
                .andExpect(jsonPath("$.['page size']").doesNotExist())
                .andExpect(jsonPath("$.['total pages']").doesNotExist())
                .andExpect(jsonPath("$.next").doesNotExist())
                .andExpect(jsonPath("$.previous").doesNotExist());
    }*/


    @DisplayName("Test find movie")
    @Test
    public void testFindMovie() throws Exception {
        Movie mockMovie = new Movie(2, "Mock Movie", 2023, 110);

        when(movieService.find(2)).thenReturn(mockMovie);

        mockMvc.perform(get("/movies/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(2)));
    }

    @DisplayName("Test movie not found")
    @Test
    public void testNotFound() throws Exception {
        final int nonExistentMovieId = 19;

        // Simulamos que el servicio lanza ResourceNotFoundException para el ID proporcionado
        when(movieService.find(nonExistentMovieId)).thenThrow(new ResourceNotFoundException("Película con id " + nonExistentMovieId + " no encontrada"));
        //when(movieService.find(nonExistentMovieId)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get("/movies/{id}", nonExistentMovieId))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Resource not found. Película con id " + nonExistentMovieId + " no encontrada")));
    }

}