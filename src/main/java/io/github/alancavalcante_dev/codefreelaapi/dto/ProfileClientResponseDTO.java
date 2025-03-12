package io.github.alancavalcante_dev.codefreelaapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileClientResponseDTO {

    String name;
    String email;
    String phone;
    String cpf;
    boolean isPj;

}
