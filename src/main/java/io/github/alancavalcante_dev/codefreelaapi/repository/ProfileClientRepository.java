package io.github.alancavalcante_dev.codefreelaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;

@Repository
public interface ProfileClientRepository extends JpaRepository<ProfileClient, UUID> {

    @Query("SELECT u FROM ProfileClient u WHERE u.username = ?1 and u.idClient <> ?2")
    Optional<ProfileClient> findByUsernameDistinctId(String username, UUID idClient);

    Optional<ProfileClient> findByUsername(String username);

}
