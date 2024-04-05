package int206.classicservice.Controllers;

import int206.classicservice.dtos.CustomerDTO;
import int206.classicservice.dtos.PageDTO;
import int206.classicservice.entities.Customer;
import int206.classicservice.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import int206.classicservice.services.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/s65105/customers")
public class CustomerController {
    @Autowired
    private CustomerService service;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;


    @GetMapping("")
    public ResponseEntity<Object> getAllCustomer(
            @RequestParam(defaultValue = "") String[] sortBy,
            @RequestParam(defaultValue = "ASC") String[] sortDirection,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Customer> customerList = service.getAllCustomer(sortBy, sortDirection, pageNo, pageSize);
        PageDTO<CustomerDTO> customerDTO = listMapper.toPageDTO(customerList, CustomerDTO.class, modelMapper);
        return ResponseEntity.ok(customerDTO);
    }
}
