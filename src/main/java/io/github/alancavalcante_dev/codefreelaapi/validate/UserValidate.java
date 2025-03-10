package io.github.alancavalcante_dev.codefreelaapi.validate;

import io.github.alancavalcante_dev.codefreelaapi.model.User;
import io.github.alancavalcante_dev.codefreelaapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@RequiredArgsConstructor
public class UserValidate {

    private final UserRepository repository;


    public Void save(User user) {
        System.out.println(user);
    }
}
