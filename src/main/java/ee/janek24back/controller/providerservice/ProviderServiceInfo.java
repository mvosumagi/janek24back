package ee.janek24back.controller.providerservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ee.janek24back.controller.providerservice.ProviderServiceInfo;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProviderServiceInfo implements Serializable {
    private Integer serviceId;
    private Integer categoryId;
    private String serviceName;
    private BigDecimal unitCost;
    private String descriptionShort;
    private String descriptionLong;
    private String imageData;
}