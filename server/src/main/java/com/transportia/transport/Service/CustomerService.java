package com.transportia.transport.Service;

import com.transportia.transport.Auth.ApplicationUser;
import com.transportia.transport.Auth.UserRepository;
import com.transportia.transport.Model.Customer;
import com.transportia.transport.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.transportia.transport.SecurityConfig.ApplicationUserRole.CUSTOMER;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Transactional
    public Customer addCustomer(Customer customer){
        ApplicationUser user = new ApplicationUser(
                customer.getUsername(),
                passwordEncoder.encode(customer.getPassword()),
                CUSTOMER.getGrantedAuthorities(),
                true,
                true,
                true,
                true);
        ApplicationUser applicationUser = userRepository.save(user);

        Customer customerToInsert = new Customer(applicationUser.getUsername(), customer.getEmail(), customer.getPassword());
        return customerRepository.save(customerToInsert);
    }

    @Transactional
    public Customer deleteCustomer(String customerUsername){
        Customer customer = customerRepository.findCustomerByUsername(customerUsername);
        Optional<ApplicationUser> user = userRepository.findApplicationUserByUsername(customerUsername);
        if(customer != null && user.isPresent()) {
            customerRepository.delete(customer);
            userRepository.delete(user.get());
        }
        return customer;
    }
}
