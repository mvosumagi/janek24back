package ee.janek24back.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "currency", schema = "janek24")
public class Currency {
    @Id
    @Column(name = "\"is\"", nullable = false)
    private Integer id;

    @Size(max = 3)
    @NotNull
    @Column(name = "short_code", nullable = false, length = 3)
    private String shortCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

}