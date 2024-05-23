package com.example.test2405.dtos;

import lombok.Data;

@Data
public class ProductsDTO {
    private Integer id;
    private String productName;
    private Double unitPrice;
    private String packageField;
    private Boolean discontinued;
}
