package ee.janek24back.controller.service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link ee.janek24back.persistence.service.Service}
 */
@Value
public class ServiceController implements Serializable {
    Integer id;
    Integer serviceCategoryId;
    @NotNull
    LocalDate validFrom;
    @NotNull
    LocalDate validTo;
    @NotNull
    BigDecimal unitCost;
    Integer currencyIsId;
    @NotNull
    @Size(max = 1)
    String status;
}