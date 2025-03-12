package io.github.alancavalcante_dev.codefreelaapi.model;


import io.github.alancavalcante_dev.codefreelaapi.dto.StateBusiness;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_business_project")
@EqualsAndHashCode(of = "idBusinessProject")
public class BusinessProject {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_business_project")
    private UUID idBusinessProject;

    @OneToMany(mappedBy = "businessProject", cascade = CascadeType.ALL)
    private List<BusinessProjectProfile> profiles = new ArrayList<>();

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "description", length = 50, nullable = false)
    private String description;

    @Column(name = "price_day", precision = 8, scale = 2)
    private BigDecimal priceDay;

    @Column(name = "price_hour", precision = 8, scale = 2)
    private BigDecimal priceHour;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Column(name = "state")
    private StateBusiness stateBusiness;


}
