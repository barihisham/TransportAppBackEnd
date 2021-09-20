package com.transportia.transport.Controller;

import com.transportia.transport.Model.Customer;
import com.transportia.transport.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @DeleteMapping("{customerUsername}")
    public Customer deleteCustomer(@PathVariable("customerUsername") String customerUsername){
        return customerService.deleteCustomer(customerUsername);
    }
}
