package io.github.alancavalcante_dev.codefreelaapi.service;


import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import io.github.alancavalcante_dev.codefreelaapi.repository.ProfileClientRepository;
import io.github.alancavalcante_dev.codefreelaapi.validate.ProfileClientValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileClientService {

    private final ProfileClientRepository repository;
    private final ProfileClientValidate validate;

    public List<ProfileClient> getAllProfileClients() {
        return repository.findAll();
    }

    public Optional<ProfileClient> getByIdProfileClient(UUID uuid) { return repository.findById(uuid);}

    public void delete(ProfileClient profileClient){
        repository.delete(profileClient);
    }

    public ProfileClient update(ProfileClient client) {
        validate.update(client);
        return repository.save(client);
    }

    public ProfileClient save(ProfileClient client) {
        validate.save(client);
        return repository.save(client);
    }
}

