package int206.classicservice.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import int206.classicservice.entities.Customer;
import lombok.Data;

@Data
public class PaymentDTO {
    private String checkNumber;
    private String paymentDate;
    private Double amount;
    @JsonIgnore
    private Customer customer;
    public Integer getCustomerNumber() {
        return customer == null ? null : customer.getCustomerNumber();
    }


}
