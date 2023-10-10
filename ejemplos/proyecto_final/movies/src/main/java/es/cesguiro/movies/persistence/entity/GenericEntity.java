package es.cesguiro.movies.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public abstract class GenericEntity{

    protected String table;
    protected String primaryKey = "id";

    /*public GenericEntity(String table) {
        GenericEntity.table = table;
    }

    public static String getTableName(){
        return GenericEntity.table;
    }

    public static String getPrimaryKey(){
        return GenericEntity.idFieldName;
    }*/
}
