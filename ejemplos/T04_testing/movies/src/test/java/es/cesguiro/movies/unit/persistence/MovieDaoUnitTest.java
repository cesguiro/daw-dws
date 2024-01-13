package es.cesguiro.movies.unit.persistence;

import es.cesguiro.movies.domain.entity.Movie;
import es.cesguiro.movies.persistence.dao.MovieDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieDao.class)*/
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class MovieDaoUnitTest {


    @Autowired
    private MovieDao movieDao;




    @Test
    public void testDatabaseConnection() {
        // Aquí puedes probar la conexión con la base de datos H2
        // Asegúrate de que las configuraciones de H2 sean correctas y la conexión se establezca correctamente
        assertNotNull(movieDao);

        // Agrega más aserciones según sea necesario
    }
    @Test
    public void testFindAll() {
        List<Movie> movies = movieDao.findAll();
        assertEquals(3, movies.size()); // Ajusta el número según la cantidad de películas en tu script SQL

        // Puedes agregar más aserciones para verificar los detalles de las películas si es necesario
        assertEquals("Cadena perpetua", movies.get(0).getTitle());
        assertEquals(1994, movies.get(0).getYear());
        assertEquals(142, movies.get(0).getRunTime());

        // Repite las aserciones para las otras películas
        // ...

    }

}
