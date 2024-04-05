package int206.classicservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "orderNumber", nullable = false)
    private Integer orderNumber;
    @Column(name = "orderDate", nullable = false)
    private String orderDate;
    @Column(name = "requiredDate", nullable = false)
    private String requiredDate;
    @Column(name = "shippedDate", nullable = true)
    private String shippedDate;
    @Column(name = "status", nullable = false)
    private String status;
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "customerNumber")
//    private Customer customer;
}