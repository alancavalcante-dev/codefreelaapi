package io.github.alancavalcante_dev.codefreelaapi.service;

import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.repository.UserRepository;
import io.github.alancavalcante_dev.codefreelaapi.validate.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
//    private final UserValidate validate;

    public User save(User user) {
//        validate.save(user);
        return repository.save(user);
    }


    public List<User> getUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserById(UUID idUser) {
        return repository.findById(idUser);
    }
}
