package es.cesguiro.movies.controller.model.director;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DirectorListWeb {

    private int id;
    private String name;

}
