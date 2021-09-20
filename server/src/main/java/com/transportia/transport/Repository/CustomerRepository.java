package com.transportia.transport.Repository;

import com.transportia.transport.Model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {
    Customer findCustomerByUsername(String username);
}
