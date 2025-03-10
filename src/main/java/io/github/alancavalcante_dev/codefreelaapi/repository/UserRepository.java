package io.github.alancavalcante_dev.codefreelaapi.repository;


import io.github.alancavalcante_dev.codefreelaapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
