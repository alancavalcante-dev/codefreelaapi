package io.github.alancavalcante_dev.codefreelaapi.dto.businesproject;

import io.github.alancavalcante_dev.codefreelaapi.dto.StateBusiness;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BusinessProjectResponseDTO {
    String idBusinessProject;
    String title;
    String description;
    BigDecimal priceDay;
    BigDecimal priceHour;
    BigDecimal priceProject;
    LocalDate closingDate;
    StateBusiness state;


}
