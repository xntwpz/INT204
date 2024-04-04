package dev.bestzige.springpractice.dtos;

import lombok.Data;

@Data
public class BooksDTO {
    private Integer id;
    private String title;
    private String author;
    private Float price;
    private Integer categoryId;
}
