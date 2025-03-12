package io.github.alancavalcante_dev.codefreelaapi.controller;

import io.github.alancavalcante_dev.codefreelaapi.dto.client.ProfileClientInsertRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.client.ProfileClientResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.client.ProfileClientUpdateRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.mapperstruct.ProfileClientMapper;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
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
    public ResponseEntity<List<ProfileClientResponseDTO>> getAllProfileClient() {
        List<ProfileClient> allProfileClients = service.getAllProfileClients();
        List<ProfileClientResponseDTO> listProfileClientDTO = allProfileClients.stream().
                map(mapper::toResponseDTO).toList();

        if (listProfileClientDTO.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listProfileClientDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileClientResponseDTO> getProfileClient(@PathVariable("id") String id) {
        return service.getByIdProfileClient(UUID.fromString(id))
                .map(p -> ResponseEntity.ok(mapper.toResponseDTO(p)))
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping
    public ResponseEntity<ProfileClientInsertRequestDTO> postProfileClient(@RequestBody @Valid ProfileClientInsertRequestDTO client ) {
        ProfileClient entity = mapper.toEntity(client);
        service.save(entity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(entity.getIdClient()).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("{id}")
    public ResponseEntity<ProfileClientResponseDTO> updateProfileClient(
            @PathVariable("id") String idClient,
            @RequestBody @Valid ProfileClientUpdateRequestDTO profileClientUpdateResponseDTO
    ) {
        return service.getByIdProfileClient(UUID.fromString(idClient))
                .map(p -> {
                    ProfileClient entity = mapper.toEntityUpdate(profileClientUpdateResponseDTO);
                    entity.setIdClient(p.getIdClient());
                    entity.setUsername(p.getUsername());
                    entity.setPassword(p.getPassword());
                    service.update(entity, p.getAddress().getIdAddress());
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
