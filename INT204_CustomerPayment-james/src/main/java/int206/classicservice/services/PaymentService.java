package int206.classicservice.services;

import int206.classicservice.entities.Payment;
import int206.classicservice.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;

    public List<Payment> getAllPayment() {
        return repository.findAll();
    }

}
