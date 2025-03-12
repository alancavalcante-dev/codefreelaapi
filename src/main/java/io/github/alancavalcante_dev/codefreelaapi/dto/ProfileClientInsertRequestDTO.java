package io.github.alancavalcante_dev.codefreelaapi.dto;


import io.github.alancavalcante_dev.codefreelaapi.model.Address;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileClientInsertRequestDTO {

    @NotNull
    @NotBlank
    @Size(min = 2, message = "Username: Mínimo de 2 caracteres e máximo de 20 caracteres", max = 20)
    String username;

    @NotNull
    @NotBlank
    @Size(min = 2, message = "Senha: Mínimo de 2 caracteres e máximo de 20 caracteres", max = 20)
    String password;


    @NotNull
    @NotBlank
    @Size(min = 2, message = "Nome: Mínimo de 2 caracteres e máximo de 100 caracteres", max = 100)
    String name;

    @NotNull
    @NotBlank
    @Email
    @Size(min = 2, message = "Email: Mínimo de 2 caracteres e máximo de 100 caracteres", max = 100)
    String email;

    @Size(min = 11, message = "Telefone: Mínimo de 11 caracteres e máximo de 13 caracteres", max = 13)
    String phone;

    @NotNull
    @NotBlank
    @Size(min = 11, message = "CPF: é de 11 caracteres, modelo: xxxxxxxxxxx", max = 11)
    String cpf;

    @NotNull(message = "Tem que definir se é Pessoa Juridica ou não.")
    boolean isPj;

    Address address;

}
