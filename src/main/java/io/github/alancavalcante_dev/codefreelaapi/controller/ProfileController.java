package io.github.alancavalcante_dev.codefreelaapi.controller;

import io.github.alancavalcante_dev.codefreelaapi.dto.client.ProfileInsertRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.client.ProfileResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.client.ProfileUpdateRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.mapperstruct.ProfileMapper;
import io.github.alancavalcante_dev.codefreelaapi.model.Profile;
import io.github.alancavalcante_dev.codefreelaapi.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;
    private final ProfileMapper mapper;


    @GetMapping
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfileClient() {
        List<Profile> allProfiles = service.getAllProfiles();
        List<ProfileResponseDTO> listProfileClientDTO = allProfiles.stream().
                map(mapper::toResponseDTO).toList();

        if (listProfileClientDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listProfileClientDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileResponseDTO> getProfileClient(@PathVariable("id") String id) {
        return service.getByIdProfile(UUID.fromString(id))
                .map(p -> ResponseEntity.ok(mapper.toResponseDTO(p)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping
    public ResponseEntity<ProfileInsertRequestDTO> postProfileClient(@RequestBody @Valid ProfileInsertRequestDTO client ) {
        Profile entity = mapper.toEntity(client);
        service.save(entity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(entity.getIdProfile()).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfileClient(
            @PathVariable("id") String idClient,
            @RequestBody @Valid ProfileUpdateRequestDTO profileClientUpdateResponseDTO
    ) {
        return service.getByIdProfile(UUID.fromString(idClient))
                .map(p -> {
                    Profile entity = mapper.toEntityUpdate(profileClientUpdateResponseDTO);
                    entity.setIdProfile(p.getIdProfile());
                    service.update(entity, p.getUser(), p.getAddress());
                    return ResponseEntity.ok(mapper.toResponseDTO(entity));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProfileClient(@PathVariable("id") String id) {
        return service.getByIdProfile(UUID.fromString(id))
                .map(p -> {
                    service.delete(p);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
