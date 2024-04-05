package int206.classicservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customerNumber", nullable = false)
    private Integer customerNumber;
    @Column(name = "customerName", nullable = false, length = 50)
    private String customerName;
    @Column(name = "contactLastName", nullable = false, length = 50)
    private String contactLastName;
    @Column(name = "contactFirstName", nullable = false, length = 50)
    private String contactFirstName;
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;
    @Column(name = "addressLine1", nullable = false, length = 50)
    private String addressLine1;
    @Column(name = "city", nullable = false, length = 50)
    private String city;
    @Column(name = "country", nullable = false, length = 50)
    private String country;
//    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<Payment> paymentList = new LinkedHashSet<>();

}