package es.cesguiro.movies.dto.director;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class DirectorListDTO {

    @JsonIgnore
    private int id;
    private String name;
    private String link;
}
