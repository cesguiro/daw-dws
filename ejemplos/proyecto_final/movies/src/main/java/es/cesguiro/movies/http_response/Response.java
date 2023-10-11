package es.cesguiro.movies.http_response;

import com.fasterxml.jackson.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonPropertyOrder({"metaData", "pagination", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private Map<String, Object> metaData = new HashMap<>();
    private Map<String, Object> pagination = new HashMap<>();;
    private Object data;


    public Response(Object data, int totalRecords, Integer page, Integer pageSize) {
        this.data = data;
        this.metaData.put("total_records", totalRecords);
        if(page != null) {
            this.paginate(page, pageSize, totalRecords);
        } else {
            this.pagination = null;
        }
    }

    private void paginate(int page, int pageSize, int totalRecords) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        this.pagination.put("page", page);
        this.pagination.put("page_size", pageSize);
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));
        if(page > 1 && totalPages > 1)
            this.pagination.put("previous", url + "?page=" + (page - 1));
        if(page < totalPages)
            this.pagination.put("next", url + "?page=" + (page + 1));
    }

}
