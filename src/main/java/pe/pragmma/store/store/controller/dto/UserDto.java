package pe.pragmma.store.store.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotNull
    @Min(value = 1) @Max(value = 10)
    private Integer docTypeId;
    @NotBlank
    private String docNumber;
    @NotNull
    @Min(value = 1) @Max(value = 10)
    private Integer roleId;
}
