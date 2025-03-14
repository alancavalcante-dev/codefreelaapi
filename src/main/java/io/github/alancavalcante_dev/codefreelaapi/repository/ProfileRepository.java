package io.github.alancavalcante_dev.codefreelaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import io.github.alancavalcante_dev.codefreelaapi.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
}
