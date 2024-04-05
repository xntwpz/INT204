package int206.classicservice.services;

import int206.classicservice.entities.Customer;
import int206.classicservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Page<Customer> getAllCustomer(String[] sortBy, String[] direction, int pageNo, int pageSize) {
        if (sortBy != null && sortBy.length > 0) {
            List<Sort.Order> sortOrderList = new ArrayList<>();
            for (int i = 0; i < sortBy.length; i++) {
                sortOrderList.add(new Sort.Order(Sort.Direction.valueOf(direction[i].toUpperCase()), sortBy[i]));
            }
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortOrderList));
            return repository.findBy(pageable);
        } else {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            return repository.findBy(pageable);
        }
    }

}
