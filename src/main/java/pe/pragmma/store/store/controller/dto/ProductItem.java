package pe.pragmma.store.store.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItem {
    private Integer id;
    @NotBlank(message = "El nombre no puede ser vacia")
    private String name;
    @NotBlank
    private String description;
    @NotNull
    @Positive
    private Double price;
    @NotNull
    @Positive
    private Integer storage;
    @NotBlank
    private Boolean active;
}
