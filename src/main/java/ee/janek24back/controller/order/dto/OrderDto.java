package ee.janek24back.controller.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    Integer providerServiceId;
    @NotNull
    LocalDate date;
    @NotNull
    @Size(max = 1000)
    String userComment;
    @NotNull
    @Size(max = 1)
    String status;
    @NotNull
    String confirmationComment;
}
