package int206.classicservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "checkNumber", nullable = false)
    private String checkNumber;
    @Column(name = "paymentDate", nullable = false)
    private String paymentDate;
    @Column(name = "amount", nullable = false)
    private Double amount;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customerNumber")
    private Customer customer;


}
