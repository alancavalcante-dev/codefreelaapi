package io.github.alancavalcante_dev.codefreelaapi.dto.client;

import io.github.alancavalcante_dev.codefreelaapi.dto.AddressDTO;
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

}
