package io.github.alancavalcante_dev.codefreelaapi.dto.client;

import io.github.alancavalcante_dev.codefreelaapi.dto.AddressDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.user.UserResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProfileClientResponseDTO {

    UUID idClient;
    String name;
    String email;
    String phone;
    String cpf;
    boolean isPj;
    AddressDTO address;
    UserResponseDTO user;
}
