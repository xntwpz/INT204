package int206.classicservice.dtos;

import int206.classicservice.entities.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class CustomerDTO {
    private String customerName;
    private String phone;
    private String city;
    private String country;
    private Set<PaymentDTO> paymentList;
}
