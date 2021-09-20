package com.transportia.transport.Auth;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<ApplicationUser, String> {
    Optional<ApplicationUser> findApplicationUserByUsername(String username);
}
