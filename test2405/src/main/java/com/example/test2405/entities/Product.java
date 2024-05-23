package com.example.test2405.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
// table name in database
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private Integer id;
    @NotNull
    private String productName;
    // validate
    @Min(value = 0 , message = "Price need to greater than 0")
    // double
    private Double unitPrice;
    @Column(name = "package")
    private String packageField;
    // tinyint
    private Boolean discontinued;

    // many to one = many PRODUCT cna be in 1 supplier
    @ManyToOne
    @JoinColumn(name = "supplierId")
//    private Supplier supplierId;
    private Supplier supplier;

}
