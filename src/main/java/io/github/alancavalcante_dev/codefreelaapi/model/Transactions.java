package io.github.alancavalcante_dev.codefreelaapi.model;


import io.github.alancavalcante_dev.codefreelaapi.dto.TransactionalTypes;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_transactions")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_transactions")
    private UUID idTransactions;

    @Column(name = "transactional_types", nullable = false)
    private TransactionalTypes transactionalTypes;

    private BigDecimal value;

    @Column(name = "profile_payer", nullable = false)
    private Profile profilePayer;

    @Column(name = "profile_received", nullable = false)
    private Profile profileReceived;

    @CreatedDate
    @Column(name = "datetime_transactional", updatable = false)
    private LocalDateTime dateTimeTransaction;

}
