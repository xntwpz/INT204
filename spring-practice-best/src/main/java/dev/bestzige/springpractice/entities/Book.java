package dev.bestzige.springpractice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String title;
    private String description;
    private String author;
    @Min(value = 0, message = "Price must be greater than 0")
    private Double price;
    private Boolean published;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
