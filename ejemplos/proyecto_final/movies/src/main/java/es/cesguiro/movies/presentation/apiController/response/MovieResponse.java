package es.cesguiro.movies.presentation.apiController.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import es.cesguiro.movies.common.ApplicationProperties;
import es.cesguiro.movies.presentation.apiController.MovieApiController;
import lombok.Data;

import java.util.List;

@Data
public class MovieResponse {

    String link;
    @JsonIgnore
    int id;
    String title;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer year;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer runtime;

    @JsonProperty("director")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    DirectorResponse directorResponse;
    @JsonProperty("characters")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<CharacterMovieResponse> characterMovieResponseList;

    public void setId(int id) {
        this.id = id;
        this.link = String.join("/",ApplicationProperties.getUrl() + MovieApiController.MOVIES, Integer.toString(this.id)) ;
    }
}
