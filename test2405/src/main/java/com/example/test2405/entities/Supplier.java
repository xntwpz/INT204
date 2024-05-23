package com.example.test2405.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
// table name in database
@Table(name = "suppliers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    private Integer id;
    // not null
    @NotNull
    private String companyName ;
    private String contactName ;
    private String contactTitle;
    private String country;
    private String phone;
    private String fax;

    // one to many = 1 SUPPLIER can be much product
    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private Set<Product> products;
}
