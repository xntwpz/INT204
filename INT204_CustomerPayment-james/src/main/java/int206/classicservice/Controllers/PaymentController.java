package int206.classicservice.Controllers;

import int206.classicservice.services.CustomerService;
import int206.classicservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s65105/payments")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @GetMapping("")
    public ResponseEntity<Object> getAllPayment() {
        return ResponseEntity.ok(service.getAllPayment());
    }
}
