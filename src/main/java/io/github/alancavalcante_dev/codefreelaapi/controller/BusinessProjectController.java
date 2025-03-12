package io.github.alancavalcante_dev.codefreelaapi.controller;


import io.github.alancavalcante_dev.codefreelaapi.dto.businesproject.BusinessProjectDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.businesproject.BusinessProjectResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.BusinessProject;
import io.github.alancavalcante_dev.codefreelaapi.service.BusinessProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @PostMapping
    public ResponseEntity<BusinessProjectDTO> postBusinessProject(@RequestBody @Valid BusinessProjectDTO request) throws Exception {
        BusinessProject business = service.save(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(business.getIdBusinessProject()).toUri();

        return ResponseEntity.created(location).build();
    }








}
