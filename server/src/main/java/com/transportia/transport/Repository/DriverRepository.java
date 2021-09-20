package com.transportia.transport.Repository;

import com.transportia.transport.Model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends MongoRepository<Driver, String> {
    Driver findDriverByUsername(String username);
    List<Driver> findAllByIsAvailableIsTrue();
}
