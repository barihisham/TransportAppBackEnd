package com.transportia.transport.Repository;

import com.transportia.transport.Model.TrafficManager;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrafficManagerRepository extends MongoRepository<TrafficManager, String> {
    TrafficManager findTrafficManagerByUsername(String username);
}
