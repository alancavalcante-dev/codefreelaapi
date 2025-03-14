package io.github.alancavalcante_dev.codefreelaapi.validate;


import io.github.alancavalcante_dev.codefreelaapi.exceptions.CurrentDateGreaterThanProjectDate;
import io.github.alancavalcante_dev.codefreelaapi.exceptions.SomeValueMustBeFilled;
import io.github.alancavalcante_dev.codefreelaapi.model.BusinessProject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class BusinessProjectValidate {

    public void fieldsPrice(BusinessProject project) {
        boolean priceDay = project.getPriceDay().compareTo(BigDecimal.ZERO) > 0;
        boolean priceHour = project.getPriceHour().compareTo(BigDecimal.ZERO) > 0;
        boolean priceProject = project.getPriceProject().compareTo(BigDecimal.ZERO) > 0;

        if (!(priceDay || priceHour || priceProject)) {
            throw new SomeValueMustBeFilled(
                    "Algum valor deve ser preenchido em algum tipo de preço: Preço por dia, Preço por Hora, Preço por projeto"
            );
        }
    }

    public void verifyFieldDateClosing(LocalDate date) {
        LocalDate dateCurrent = LocalDate.now();
        if (dateCurrent.isAfter(date)) {
            throw new CurrentDateGreaterThanProjectDate(
                    "Data do projeto deve ser maior que a data atual"
            );
        }
    }

    public void save(BusinessProject project) {
        this.fieldsPrice(project);
        this.verifyFieldDateClosing(project.getClosingDate());
    }
}
