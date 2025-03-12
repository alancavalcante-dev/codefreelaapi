package io.github.alancavalcante_dev.codefreelaapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tbl_address")
@Data
public class Address {

    @Id
    @Column(name = "id_address")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idAddress;

    @Column(nullable = false, length = 50)
    private String state;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String neighborhood;

    @Column(length = 100)
    private String address;

    @Column(name = "address_number")
    private int addressNumber;




}
