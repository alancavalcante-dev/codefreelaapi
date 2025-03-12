package io.github.alancavalcante_dev.codefreelaapi.controller;


import io.github.alancavalcante_dev.codefreelaapi.dto.freela.ProfileFreelaInsertRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.freela.ProfileFreelaResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.freela.ProfileFreelaUpdateRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.mapperstruct.ProfileFreelaMapper;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileFreela;
import io.github.alancavalcante_dev.codefreelaapi.service.ProfileFreelaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("api/profile/freela")
@RequiredArgsConstructor
public class ProfileFreelaController {

    private final ProfileFreelaService service;
    private final ProfileFreelaMapper mapper;


    @GetMapping
    public ResponseEntity<List<ProfileFreelaResponseDTO>> getAllProfileFreela() {
        List<ProfileFreela> listProfileFreelas = service.getAllProfileFreelas();
        List<ProfileFreelaResponseDTO> listProfileFreelaDTO = listProfileFreelas.stream().
                map(mapper::toResponseDTO).toList();

        if (listProfileFreelas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listProfileFreelaDTO);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProfileFreelaResponseDTO> getProfileFreela(@PathVariable("id") String id) {
        return service.getByIdProfileFreela(UUID.fromString(id))
                .map(p -> ResponseEntity.ok(mapper.toResponseDTO(p)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping
    public ResponseEntity<ProfileFreelaInsertRequestDTO> postProfileFreela(@RequestBody @Valid ProfileFreelaInsertRequestDTO freela ) {
        ProfileFreela entity = mapper.toEntity(freela);
        service.save(entity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(entity.getIdFreela()).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("{id}")
    public ResponseEntity<ProfileFreelaResponseDTO> updateProfileFreela(
            @PathVariable("id") String idFreela,
            @RequestBody @Valid ProfileFreelaUpdateRequestDTO profileFreelaUpdateResponseDTO
    ) {
        return service.getByIdProfileFreela(UUID.fromString(idFreela))
                .map(p -> {
                    ProfileFreela entity = mapper.toEntityUpdate(profileFreelaUpdateResponseDTO);
                    entity.setIdFreela(p.getIdFreela());
                    service.update(entity, p.getUser(), p.getAddress());
                    return ResponseEntity.ok(mapper.toResponseDTO(entity));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProfileFreela(@PathVariable("id") String id) {
        return service.getByIdProfileFreela(UUID.fromString(id))
                .map(p -> {
                    service.delete(p);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}