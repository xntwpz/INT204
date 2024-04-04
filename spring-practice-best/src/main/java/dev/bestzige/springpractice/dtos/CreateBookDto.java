package dev.bestzige.springpractice.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookDto {
    @NotNull
    private String title;
    private String description;
    private String author;
    @Min(value = 0, message = "Price must be greater than 0")
    private Double price;
    private Boolean published;
    @NotNull
    private Integer categoryId;
}
