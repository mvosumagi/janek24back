package ee.janek24back.controller.providerservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProviderServiceDto implements Serializable {

    Integer serviceCategoryId;
    @NotNull
    @Size(max = 254)
    String name;
    @NotNull
    @Size(max = 100)
    String descriptionShort;
    @NotNull
    @Size(max = 1000)
    String descriptionLong;
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
    Integer providerServiceId;

}