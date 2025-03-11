package io.github.alancavalcante_dev.codefreelaapi.controller;

import io.github.alancavalcante_dev.codefreelaapi.dto.ProfileClientDTO;
import io.github.alancavalcante_dev.codefreelaapi.mapperstruct.ProfileClientMapper;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.service.ProfileClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/profile-client")
@RequiredArgsConstructor
public class ProfileClientController {

    private final ProfileClientService service;
    private final ProfileClientMapper mapper;


    @GetMapping
    public ResponseEntity<List<ProfileClientDTO>> getAllProfileClient() {
        List<ProfileClient> allProfileClients = service.getAllProfileClients();
        List<ProfileClientDTO> listProfileClientDTO = allProfileClients.stream().
                map(mapper::toResponseDTO).toList();

        if (listProfileClientDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listProfileClientDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileClientDTO> getProfileClient(@PathVariable("id") String id) {
        return service.getByIdProfileClient(UUID.fromString(id))
                .map(p -> ResponseEntity.ok(mapper.toResponseDTO(p)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping
    public ResponseEntity<ProfileClientDTO> postProfileClient( @RequestBody @Valid ProfileClientDTO client ) {
        ProfileClient entity = mapper.toEntity(client);
        service.save(entity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(entity.getIdProfileClient()).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("{id}")
    public ResponseEntity<ProfileClientDTO> updateProfileClient(
            @PathVariable("id") String idUser,
            @RequestBody @Valid ProfileClientDTO profileClientResponseDTO
    ) {
        return service.getByIdProfileClient(UUID.fromString(idUser))
                .map(p -> {
                    ProfileClient entity = mapper.toEntity(profileClientResponseDTO);
                    entity.setIdProfileClient(p.getIdProfileClient());
                    service.update(entity);
                    return ResponseEntity.ok(mapper.toResponseDTO(entity));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProfileClient(@PathVariable("id") String id) {
        return service.getByIdProfileClient(UUID.fromString(id))
                .map(p -> {
                    service.delete(p);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }




}
