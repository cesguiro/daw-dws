package es.cesguiro.movies.presentation.apiController.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import es.cesguiro.movies.common.ApplicationProperties;
import es.cesguiro.movies.presentation.apiController.ActorApiController;
import lombok.Data;

@Data
public class ActorResponse {

    String link;

    @JsonIgnore
    int id;

    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer birthYear;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer deathYear;

    public void setId(int id) {
        this.id = id;
        this.link = String.join("/", ApplicationProperties.getUrl() + ActorApiController.ACTORS, Integer.toString(this.id)) ;
    }
}
