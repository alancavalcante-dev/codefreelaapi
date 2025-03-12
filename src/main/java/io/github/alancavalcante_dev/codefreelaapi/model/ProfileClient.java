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
    private UUID idClient;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 13)
    private String phone;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private boolean isPj;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate dateCreated;

    @LastModifiedDate
    private LocalDateTime dateLastModify;

    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}
