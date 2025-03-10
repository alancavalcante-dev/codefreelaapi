package io.github.alancavalcante_dev.codefreelaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @Size(min = 2, message = "Mínimo de 2 caracteres necessários.")
    @NotNull
    @NotBlank
    String username;

    @Size(min = 2, message = "Mínimo de 8 caracteres necessários.")
    @NotNull
    @NotBlank
    String password;
}
