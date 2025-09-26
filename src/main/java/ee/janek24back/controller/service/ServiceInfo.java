package ee.janek24back.controller.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class ServiceInfo implements Serializable {
    private Integer serviceId;
    private String serviceName;
    private String description_short;
    private String unit_cost;


}