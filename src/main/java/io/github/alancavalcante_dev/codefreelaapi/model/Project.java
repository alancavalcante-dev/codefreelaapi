package io.github.alancavalcante_dev.codefreelaapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Data
@Table(name = "tbl_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_project")
    private UUID idProject;

    @OneToOne(mappedBy = "idBusinessProjectProfile", optional = false)
    @JoinColumn(name = "id_bussines_project_profile", nullable = false)
    private BusinessProjectProfile idBusinessProjectProfile;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 50, nullable = false)
    private String description;

    @Column(name = "date_last_deploy")
    private LocalDateTime dateLastDeploy;

    @Column(name = "date_closing")
    private LocalDateTime dateClosing;

    // business
    @Column(name = "date_starting")
    private LocalDateTime dateStarting;

    @Column(name = "date_ending")
    private LocalDateTime dateEnding;
}
