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
@Table(name = "tbl_profile_freela")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProfileFreela {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_profile_freela")
    private UUID idFreela;

    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 13)
    private String phone;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @OneToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @Column(nullable = false)
    private boolean isPj;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate dateCreated;

    @LastModifiedDate
    private LocalDateTime dateLastModify;
}

