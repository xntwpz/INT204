package dev.bestzige.springpractice.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookDTO extends BooksDTO {
    private String description;
    private CategoryDTO category;
}
