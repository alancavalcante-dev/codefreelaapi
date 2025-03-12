package io.github.alancavalcante_dev.codefreelaapi.service;

import io.github.alancavalcante_dev.codefreelaapi.model.Address;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileFreela;
import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.repository.AddressRepository;
import io.github.alancavalcante_dev.codefreelaapi.repository.ProfileFreelaRepository;
import io.github.alancavalcante_dev.codefreelaapi.repository.UserRepository;
import io.github.alancavalcante_dev.codefreelaapi.validate.UserValidate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class ProfileFreelaService {

    private final ProfileFreelaRepository repository;
    private final UserRepository userRepository;
    private final UserValidate validate;
    private final AddressRepository addressRepository;

    public List<ProfileFreela> getAllProfileFreelas() {
        return repository.findAll();
    }

    public Optional<ProfileFreela> getByIdProfileFreela(UUID uuid) {
        return repository.findById(uuid);
    }

    public void delete(ProfileFreela profileFreela){
        repository.delete(profileFreela);
    }

    @Transactional
    public ProfileFreela update(ProfileFreela client , User userEntity, Address addressEntity) {
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



    @Transactional
    public ProfileFreela save(ProfileFreela client) {
        validate.save(client.getUser());

        User userSave = userRepository.save(client.getUser());
        Address addressSave = addressRepository.save(client.getAddress());

        client.setUser(userSave);
        client.setAddress(addressSave);
        return repository.save(client);
    }
}