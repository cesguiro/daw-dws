package es.cesguiro.movies.common.dto;

import es.cesguiro.movies.common.exception.DtoValidationException;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ActorDto {

    Integer id;
    private String name;

    //@ValidYear
    @Nullable
    @Min(value = 1880, message = "El año debe ser posterior a 1880")
    private Integer birthYear;

    //@ValidYear
    @Nullable
    @Min(value = 1880, message = "El año debe ser posterior a 1880")
    private Integer deathYear;

    public void setBirthYear(Integer birthYear) {
        if(this.deathYear != null && birthYear!= null && this.deathYear < birthYear) {
                throw new DtoValidationException("El año de nacimiento no puede ser mayor que el año de muerte.");
        }
        this.birthYear = birthYear;
    }

    public void setDeathYear(Integer deathYear) {
        if(this.birthYear != null && deathYear != null && this.birthYear > deathYear) {
            throw new DtoValidationException("El año de nacimiento no puede ser mayor que el año de muerte.");
        }
        this.deathYear = deathYear;
    }

}
