package io.github.alancavalcante_dev.codefreelaapi.service;


import io.github.alancavalcante_dev.codefreelaapi.model.Address;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import io.github.alancavalcante_dev.codefreelaapi.repository.AddressRepository;
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
    private final AddressRepository addressRepository;
    private final ProfileClientValidate validate;

    public List<ProfileClient> getAllProfileClients() {
        return repository.findAll();
    }

    public Optional<ProfileClient> getByIdProfileClient(UUID uuid) { return repository.findById(uuid);}

    public void delete(ProfileClient profileClient){
        repository.delete(profileClient);
    }

    public ProfileClient update(ProfileClient client , UUID id) {
        validate.update(client);

        Address addressClient = client.getAddress();
        Optional<Address> addressExists = addressRepository.findById(id);

        if (addressExists.isPresent()) {
            Address address = addressExists.get();

            address.setState(addressClient.getState());
            address.setCity(addressClient.getCity());
            address.setNeighborhood(addressClient.getNeighborhood());
            address.setAddress(addressClient.getAddress());
            address.setAddressNumber(addressClient.getAddressNumber());
            addressClient = addressRepository.save(address);
        } else {
            addressClient = addressRepository.save(addressClient);
        }
        client.setAddress(addressClient);
        return repository.save(client);
    }

    public ProfileClient save(ProfileClient client) {
        validate.save(client);
        Address addressSave = addressRepository.save(client.getAddress());
        client.setAddress(addressSave);
        return repository.save(client);
    }
}

