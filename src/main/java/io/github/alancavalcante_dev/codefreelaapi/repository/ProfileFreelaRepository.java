package io.github.alancavalcante_dev.codefreelaapi.repository;

import io.github.alancavalcante_dev.codefreelaapi.model.ProfileFreela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface ProfileFreelaRepository extends JpaRepository<ProfileFreela, UUID> {

    @Query("SELECT u FROM ProfileFreela u WHERE u.username = ?1 and u.idFreela <> ?2")
    Optional<ProfileFreela> findByUsernameDistinctId(String username, UUID idFreela);

    Optional<ProfileFreela> findByUsername(String username);

}