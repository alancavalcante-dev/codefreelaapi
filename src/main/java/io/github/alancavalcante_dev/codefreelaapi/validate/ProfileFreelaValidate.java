package io.github.alancavalcante_dev.codefreelaapi.validate;

import io.github.alancavalcante_dev.codefreelaapi.exceptions.UsernameDuplicadoExeption;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileFreela;
import io.github.alancavalcante_dev.codefreelaapi.repository.ProfileFreelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProfileFreelaValidate {

    private final ProfileFreelaRepository repository;

    public void save(ProfileFreela freela) {
        Optional<ProfileFreela> freelaGet = repository.findByUsername(freela.getUsername());
        if (freelaGet.isPresent()) {
            throw new UsernameDuplicadoExeption("Esse username já esta sendo usado, tente outro!");
        }
    }

    public void update(ProfileFreela freela) {
        Optional<ProfileFreela> freelaGet = repository.findByUsernameDistinctId(freela.getUsername(), freela.getIdFreela());
        if (freelaGet.isPresent()) {
            throw new UsernameDuplicadoExeption("Esse username já esta sendo usado, tente outro!");
        }

    }
}