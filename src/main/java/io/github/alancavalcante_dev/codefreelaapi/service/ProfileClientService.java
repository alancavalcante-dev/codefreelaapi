package io.github.alancavalcante_dev.codefreelaapi.service;


import io.github.alancavalcante_dev.codefreelaapi.model.Address;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.repository.AddressRepository;
import io.github.alancavalcante_dev.codefreelaapi.repository.ProfileClientRepository;
import io.github.alancavalcante_dev.codefreelaapi.repository.UserRepository;
import io.github.alancavalcante_dev.codefreelaapi.validate.UserValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileClientService {

    private final ProfileClientRepository repository;
    private final UserRepository userRepository;
    private final UserValidate validate;
    private final AddressRepository addressRepository;


    public List<ProfileClient> getAllProfileClients() {
        return repository.findAll();
    }

    public Optional<ProfileClient> getByIdProfileClient(UUID uuid) { return repository.findById(uuid);}

    public void delete(ProfileClient profileClient){
        repository.delete(profileClient);
    }

    public ProfileClient update(ProfileClient client , User userEntity,Address addressEntity) {
        validate.update(client.getUser());

        User userClient = client.getUser();
        Address addressClient = client.getAddress();

        Optional<User> userExists = userRepository.findById(userEntity.getIdUser());
        Optional<Address> addressExists = addressRepository.findById(addressEntity.getIdAddress());

        if (userExists.isPresent()) {
            User user = userExists.get();
            user.setUsername(userClient.getUsername());
            user.setPassword(userClient.getPassword());

            userClient = userRepository.save(user);
        } else {
            userClient = userRepository.save(userClient);
        }


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

        client.setUser(userClient);
        client.setAddress(addressClient);
        return repository.save(client);
    }




    public ProfileClient save(ProfileClient client) {
        validate.save(client.getUser());

        User userSave = userRepository.save(client.getUser());
        Address addressSave = addressRepository.save(client.getAddress());

        client.setUser(userSave);
        client.setAddress(addressSave);
        return repository.save(client);
    }
}

