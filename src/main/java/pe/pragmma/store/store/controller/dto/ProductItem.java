package pe.pragmma.store.store.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {
    private Integer id;
    @NotBlank(message = "El ")
    private String name;
    @NotBlank()
    private String description;
    @NotNull()
    @Positive()
    private Double price;
    @NotNull()
    @PositiveOrZero()
    private Integer storage;
    private Boolean active;
}
