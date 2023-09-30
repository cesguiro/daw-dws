package es.cesguiro.movies.http_response;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonPropertyOrder({"total records", "total page", "page", "page size", "previous", "next", "data"})
public class Response {

    private Object data;

    @JsonIgnore
    @JsonAnyGetter
    private Map<String, Object> additionalAttributes = new HashMap<>();

    public Response(Object data) {
        this.data = data;
    }

    public void addAdditionalAttribute(String name, Object value) {
        this.additionalAttributes.put(name, value);
    }

    public void paginate(int page, int pageSize, int totalRecords) {
        addAdditionalAttribute("page", page);
        addAdditionalAttribute("page size", pageSize);
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));
        addAdditionalAttribute("total pages", totalPages);
        if(page > 1 && totalPages > 1)
            addAdditionalAttribute("previous", "/movies?page=" + (page - 1));
            //this.previous = "/movies?page=" + (page - 1);
        if(page < totalPages)
            addAdditionalAttribute("next", "/movies?page=" + (page + 1));
            //this.next = "/movies?page=" + (page + 1);
    }

    //@JsonAnyGetter
    //@JsonIgnore
    //@JsonProperty(index = 1)
    /*public Map<String, Object> getAdditionalAttributes() {
        return additionalAttributes;
    }

    @JsonProperty("data")
    public Object getData() {
        return data;
    }

    /*public Response(Object data, int totalRecords, Optional<Integer> page, int pageSize) {
        this.data = data;
        this.totalRecords = totalRecords;
        if(page.isPresent())
            buildPaginationMetaData(totalRecords, pageSize, page.get());
    }*/

    /*private void buildPaginationMetaData(int totalRecords, int pageSize, int page) {
        this.page = page;
        this.pageSize = pageSize;
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));
        this.totalPages = totalPages;

        if(page > 1 && totalPages > 1)
            this.previous = "/movies?page=" + (page - 1);
        if(page < totalPages)
            this.next = "/movies?page=" + (page + 1);
    }*/
}
