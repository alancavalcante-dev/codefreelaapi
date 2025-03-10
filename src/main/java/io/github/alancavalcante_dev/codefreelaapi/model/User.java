package io.github.alancavalcante_dev.codefreelaapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tbl_user")
@Data
public class User {

    @Id
    @Column(name = "id_user")
    private UUID idUser;

    @Column
    private String username;

    @Column
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ProfileClient profileClient;

}
