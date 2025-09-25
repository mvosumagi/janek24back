package ee.janek24back.controller.service;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link ee.janek24back.persistence.service.ServiceCategory}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ServiceInfo implements Serializable {
    private Integer serviceId;
    private String serviceName;
    private String descriptionShort;
    private BigDecimal unitCost;

}