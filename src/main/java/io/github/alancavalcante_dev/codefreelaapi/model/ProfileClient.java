package io.github.alancavalcante_dev.codefreelaapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_profile_client")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProfileClient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_profile_client")
    private UUID idProfileClient;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user;


    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private boolean isPj;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate dateCreated;

    @LastModifiedDate
    private LocalDateTime dateLastModify;
}
