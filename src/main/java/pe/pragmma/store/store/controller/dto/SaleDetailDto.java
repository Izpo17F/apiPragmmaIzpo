package pe.pragmma.store.store.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SaleDetailDto {
    private Integer id;
    @NotNull
    private Integer saleId;
    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;
    @NotBlank
    private String observation;
    @NotBlank
    private Boolean active;
}
