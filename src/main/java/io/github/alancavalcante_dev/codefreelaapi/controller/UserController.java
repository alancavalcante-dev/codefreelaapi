package io.github.alancavalcante_dev.codefreelaapi.controller;

import io.github.alancavalcante_dev.codefreelaapi.dto.UserRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.UserResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.mapperstruct.UserMapper;
import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<User> usersAll = service.getUsers();
        List<UserResponseDTO> users = usersAll.stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());

        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") String idUser) {
        return service.getUserById(UUID.fromString(idUser))
                .map(user -> ResponseEntity.ok(mapper.toResponseDTO(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> postUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        User entity = mapper.toEntity(userRequestDTO);
        service.save(entity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(entity.getIdUser()).toUri();

        return ResponseEntity.created(location).build();
    }


    @PutMapping("{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable("id") String idUser,
            @RequestBody @Valid UserRequestDTO userDTO
    ) {
        return service.getUserById(UUID.fromString(idUser))
                .map(user -> {
                    User entity = mapper.toEntity(userDTO);
                    entity.setIdUser(user.getIdUser());
                    service.update(entity);
                    return ResponseEntity.ok(mapper.toResponseDTO(entity));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("{id}") String id) {
        return service.getUserById(UUID.fromString(id))
                .map(user -> ResponseEntity.ok().build())
                .orElseGet(() -> ResponseEntity.notFound().build());

    }




}
