package pe.pragmma.store.store.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class SaleDto {
    private Integer id;
    private LocalDateTime issueDate;
    @NotBlank
    private Boolean success;
    @NotBlank
    private String observation;
    private Boolean active;
}
