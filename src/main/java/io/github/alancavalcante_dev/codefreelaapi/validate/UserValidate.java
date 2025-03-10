package io.github.alancavalcante_dev.codefreelaapi.validate;

import io.github.alancavalcante_dev.codefreelaapi.exceptions.UsernameDuplicadoExeption;
import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository repository;


    public void save(User user) {
        Optional<User> userGet = repository.findByUsername(user.getUsername());
        if (userGet.isPresent()) {
            throw new UsernameDuplicadoExeption("Esse username já esta sendo usado, tente outro!");
        }
    }
}
