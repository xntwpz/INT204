package com.example.classicmodel2305.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "offices")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Office {
    @Id
    @Column(name = "officeCode" , nullable = false , length = 10)
    private String officeCode;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "addressLine1", nullable = false, length = 50)
    private String addressLine1;

    @Column(name = "addressLine2",  length = 50)
    private String addressLine2;
    // if can null ,  don't have to use nullable = true

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "postalCode", nullable = false, length = 15)
    private String postalCode;

    @Column(name = "territory", nullable = false, length = 10)
    private String territory;

    // if table is one-to-many out JsonIgnore
    @JsonIgnore
    @OneToMany(mappedBy = "office")
    private Set<Employee> employees;
}
