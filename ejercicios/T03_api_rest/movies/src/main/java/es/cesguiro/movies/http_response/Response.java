package es.cesguiro.movies.http_response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // No incluirá atributos nulos en el JSON
@JsonPropertyOrder({ "page", "page size", "total pages", "total records", "previous", "next", "data"})
public class Response {

    private Object data;
    //Los ponemos como Integer para que el valor por defecto sea nulo y no 0, así se pueden excluir del JSON
    @JsonProperty("total records")
    private Integer totalRecords;
    private Integer page;
    @JsonProperty("page size")
    private Integer pageSize;
    @JsonProperty("total pages")
    private Integer totalPages;
    private String next;
    private String previous;

    public Response(Object data, int totalRecords, Optional<Integer> page, int pageSize) {
        this.data = data;
        this.totalRecords = totalRecords;
        if(page.isPresent())
            buildPaginationMetaData(totalRecords, pageSize, page.get());
    }

    private void buildPaginationMetaData(int totalRecords, int pageSize, int page) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        this.page = page;
        this.pageSize = pageSize;
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));
        this.totalPages = totalPages;

        if(page > 1 && totalPages > 1)
            this.previous = url + "?page=" + (page - 1);
        if(page < totalPages)
            this.next = url + "?page=" + (page + 1);
    }
}
