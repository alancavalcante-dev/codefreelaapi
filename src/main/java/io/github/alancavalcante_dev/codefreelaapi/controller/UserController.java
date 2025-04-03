package io.github.alancavalcante_dev.codefreelaapi.controller;


import io.github.alancavalcante_dev.codefreelaapi.dto.user.UserRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.user.UserResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.mapperstruct.UserMapper;
import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = service.getAllUsers();
        if (users.isEmpty()) return ResponseEntity.noContent().build();

        List<UserResponseDTO> usersDTO = users.stream().map(mapper::entityToResponse).toList();
        return ResponseEntity.ok(usersDTO);
    }


    @PostMapping
    public ResponseEntity<UserResponseDTO> registroDeUsuario(@RequestBody @Valid UserRequestDTO userDTO) {
        User user = mapper.requestToEntity(userDTO);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        User userSave = service.save(user);
        return ResponseEntity.ok(mapper.entityToResponse(userSave));
    }

}







