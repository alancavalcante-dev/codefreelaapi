package io.github.alancavalcante_dev.codefreelaapi.controller;


import io.github.alancavalcante_dev.codefreelaapi.dto.user.UserResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.mapperstruct.UserMapper;
import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = service.getAllUsers();
        if (users.isEmpty()) return ResponseEntity.noContent().build();

        List<UserResponseDTO> usersDTO = users.stream().map(mapper::entityToResponse).toList();
        return ResponseEntity.ok(usersDTO);

    }

//    @PostMapping
//    public ResponseEntity<?> register() {
//
//    }

}







