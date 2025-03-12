package io.github.alancavalcante_dev.codefreelaapi.dto.businesproject;

import jakarta.validation.constraints.*;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BusinessProjectDTO {

    String idProfileClient;

    @NotNull
    @NotBlank
    @Size(min=2, message = "Erro", max = 100)
    String title;

    @NotNull
    @NotBlank
    @Size(min=2, message = "Erro", max = 100)
    String description;

    @NotNull
    BigDecimal priceDay;

    @NotNull
    BigDecimal priceHour;

    @NotNull
    LocalDate closingDate;




}
