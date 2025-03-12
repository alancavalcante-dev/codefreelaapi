package io.github.alancavalcante_dev.codefreelaapi.service;

import io.github.alancavalcante_dev.codefreelaapi.model.Address;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileFreela;
import io.github.alancavalcante_dev.codefreelaapi.repository.AddressRepository;
import io.github.alancavalcante_dev.codefreelaapi.repository.ProfileFreelaRepository;
import io.github.alancavalcante_dev.codefreelaapi.validate.ProfileFreelaValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Service
@RequiredArgsConstructor
public class ProfileFreelaService {

    private final ProfileFreelaRepository repository;
    private final AddressRepository addressRepository;
    private final ProfileFreelaValidate validate;

    public List<ProfileFreela> getAllProfileFreelas() {
        return repository.findAll();
    }

    public Optional<ProfileFreela> getByIdProfileFreela(UUID uuid) {
        return repository.findById(uuid);
    }

    public void delete(ProfileFreela profileFreela){
        repository.delete(profileFreela);
    }

    public ProfileFreela update(ProfileFreela freela , UUID id) {
        validate.update(freela);

        Address addressFreela = freela.getAddress();
        Optional<Address> addressExists = addressRepository.findById(id);

        if (addressExists.isPresent()) {
            Address address = addressExists.get();

            address.setState(addressFreela.getState());
            address.setCity(addressFreela.getCity());
            address.setNeighborhood(addressFreela.getNeighborhood());
            address.setAddress(addressFreela.getAddress());
            address.setAddressNumber(addressFreela.getAddressNumber());
            addressFreela = addressRepository.save(address);
        } else {
            addressFreela = addressRepository.save(addressFreela);
        }
        freela.setAddress(addressFreela);
        return repository.save(freela);
    }

    public ProfileFreela save(ProfileFreela freela) {
        validate.save(freela);
        Address addressSave = addressRepository.save(freela.getAddress());
        freela.setAddress(addressSave);
        return repository.save(freela);
    }
}