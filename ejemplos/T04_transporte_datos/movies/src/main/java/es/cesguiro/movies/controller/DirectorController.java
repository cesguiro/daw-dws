package es.cesguiro.movies.controller;

import es.cesguiro.movies.dto.director.DirectorDetailDTO;
import es.cesguiro.movies.dto.director.DirectorInsertDTO;
import es.cesguiro.movies.domain.service.DirectorService;
import es.cesguiro.movies.dto.director.DirectorUpdateDTO;
import es.cesguiro.movies.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/directors")
@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(directorService.find(id)).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody DirectorInsertDTO directorInsertDTO){
        int id = directorService.create(directorInsertDTO);
        DirectorDetailDTO directorDetailDTO = new DirectorDetailDTO(
                id,
                directorInsertDTO.getName(),
                directorInsertDTO.getBirthYear(),
                directorInsertDTO.getDeathYear()
        );
        return Response.builder().data(directorDetailDTO).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody DirectorUpdateDTO directorUpdateDTO) {
        directorUpdateDTO.setId(id);
        directorService.update(directorUpdateDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        directorService.delete(id);
    }


}
