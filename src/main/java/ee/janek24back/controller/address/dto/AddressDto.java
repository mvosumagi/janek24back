package ee.janek24back.controller.address.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressDto {

    @NotNull
    private Integer countryId;

    @NotNull
    private Integer cityId;

    @Size(max = 100)
    private String state;

    @Size(max = 255)
    private String address;



}
