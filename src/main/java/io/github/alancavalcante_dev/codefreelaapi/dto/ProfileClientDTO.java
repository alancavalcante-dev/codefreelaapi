package io.github.alancavalcante_dev.codefreelaapi.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileClientDTO {

    @NotNull
    @NotBlank
    @Size(min = 2, message = "Mínimo de 2 caracteres e máximo de 100 caracteres", max = 100)
    String name;

    @NotNull
    @NotBlank
    @Email
    @Size(min = 2, message = "Mínimo de 2 caracteres e máximo de 100 caracteres", max = 100)
    String email;

    @NotNull
    @NotBlank
    @Size(min = 11, message = "O cpf é de 11 caracteres, modelo: xxxxxxxxxxx", max = 11)
    String cpf;

    @NotNull(message = "Tem que definir se é Pessoa Juridica ou não.")
    boolean isPj;

    private UserResponseDTO user; // <- Adiciona aqui o DTO do user

}
