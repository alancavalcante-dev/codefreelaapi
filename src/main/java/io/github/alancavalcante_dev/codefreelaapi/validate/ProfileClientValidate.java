package io.github.alancavalcante_dev.codefreelaapi.validate;


import io.github.alancavalcante_dev.codefreelaapi.exceptions.UsernameDuplicadoExeption;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import io.github.alancavalcante_dev.codefreelaapi.repository.ProfileClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProfileClientValidate {

    private final ProfileClientRepository repository;

    public void save(ProfileClient client) {
        Optional<ProfileClient> clientGet = repository.findByUsername(client.getUsername());
        if (clientGet.isPresent()) {
            throw new UsernameDuplicadoExeption("Esse username já esta sendo usado, tente outro!");
        }
    }

    public void update(ProfileClient client) {
        Optional<ProfileClient> userGet = repository.findByUsernameDistinctId(client.getUsername(), client.getIdClient());
        if (userGet.isPresent()) {
            throw new UsernameDuplicadoExeption("Esse username já esta sendo usado, tente outro!");
        }

    }
}
