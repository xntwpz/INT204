package dev.bestzige.springpractice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCategoryDto {
    @NotNull
    private String name;
    private String description;
}
