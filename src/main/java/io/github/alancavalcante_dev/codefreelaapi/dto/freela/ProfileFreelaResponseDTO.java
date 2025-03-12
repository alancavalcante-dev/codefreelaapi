package io.github.alancavalcante_dev.codefreelaapi.dto.freela;

import io.github.alancavalcante_dev.codefreelaapi.dto.AddressDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class ProfileFreelaResponseDTO {

    UUID idClient;
    String name;
    String email;
    String phone;
    String cpf;
    boolean isPj;
    AddressDTO address;

}
