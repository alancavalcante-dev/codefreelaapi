package io.github.alancavalcante_dev.codefreelaapi.controller;


import io.github.alancavalcante_dev.codefreelaapi.dto.businesproject.BusinessProjectInsertDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.businesproject.BusinessProjectResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.businesproject.BusinessProjectUpdateDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.BusinessProject;
import io.github.alancavalcante_dev.codefreelaapi.service.BusinessProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/projects/business")
public class BusinessProjectController {

    @Autowired
    private BusinessProjectService service;


    @GetMapping
    public ResponseEntity<List<BusinessProjectResponseDTO>> getAllBusinessProject() {
        List<BusinessProjectResponseDTO> listDTO = service.getAllBusinessProject()
                .stream().map( bP -> new BusinessProjectResponseDTO(
                        bP.getIdBusinessProject().toString(),
                        bP.getTitle(),
                        bP.getDescription(),
                        bP.getPriceDay(),
                        bP.getPriceDay(),
                        bP.getClosingDate(),
                        bP.getStateBusiness()
                )).toList();

        if(listDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listDTO);
    }


    @GetMapping("{id}")
    public ResponseEntity<BusinessProjectResponseDTO> getIdBusinessProject(@PathVariable("id") String id) {
        Optional<BusinessProject> projectOptional = service.findyByIdBusinessProject(UUID.fromString(id));
        if (projectOptional.isEmpty()) { return ResponseEntity.notFound().build(); }

        BusinessProject project = projectOptional.get();

        BusinessProjectResponseDTO dto = new BusinessProjectResponseDTO(
                id,
                project.getTitle(),
                project.getDescription(),
                project.getPriceDay(),
                project.getPriceHour(),
                project.getClosingDate(),
                project.getStateBusiness()
        );

        return ResponseEntity.ok(dto);
    }



    @PutMapping("{id}")
    public ResponseEntity<Object> updateBusinessProject(
            @PathVariable("id") String id,
            @RequestBody @Valid BusinessProjectUpdateDTO request
    ) {
        Optional<BusinessProject> businessOptional = service.findyByIdBusinessProject(UUID.fromString(id));
        if (businessOptional.isEmpty()) { return ResponseEntity.notFound().build(); }

        BusinessProject project = businessOptional.get();
        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setPriceDay(request.getPriceDay());
        project.setPriceHour(request.getPriceHour());

        service.update(project);
        return ResponseEntity.ok().build();
    }


    @PostMapping
    public ResponseEntity<BusinessProjectInsertDTO> postBusinessProject(@RequestBody @Valid BusinessProjectInsertDTO request) throws Exception {
        BusinessProject business = service.save(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(business.getIdBusinessProject()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteBusinessProject(@PathVariable("id") String idBusinessProject) {
        return service.findyByIdBusinessProject(UUID.fromString(idBusinessProject))
                .map( project -> {
                    service.delete(project);
                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
