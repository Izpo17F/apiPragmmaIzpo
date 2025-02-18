package pe.pragmma.store.store.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocTypeDto {
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
