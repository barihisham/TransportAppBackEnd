package com.transportia.transport.Service;

import com.transportia.transport.Auth.ApplicationUser;
import com.transportia.transport.Auth.UserRepository;
import com.transportia.transport.Model.Driver;
import com.transportia.transport.Repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.transportia.transport.SecurityConfig.ApplicationUserRole.DRIVER;

@Service
public class DriverService{

    private final MongoTemplate mongoTemplate;
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DriverService(MongoTemplate mongoTemplate, DriverRepository driverRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.mongoTemplate = mongoTemplate;
        this.driverRepository = driverRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public Driver getDriver(String driverUsername){
        return driverRepository.findDriverByUsername(driverUsername);
    }

    public void setDriverAvailability(Driver driver){

        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(driver.getUsername()));
        Update update = new Update();
        update.set("isAvailable", driver.getIsAvailable());
        mongoTemplate.updateFirst(query, update, Driver.class);
    }

    @Transactional
    public Driver addDriver(Driver driver){
        if(driver.getUsername() != null && driver.getPassword() != null){
            ApplicationUser user = new ApplicationUser(
                    driver.getUsername(),
                    passwordEncoder.encode(driver.getPassword()),
                    DRIVER.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true);
            ApplicationUser applicationUser = userRepository.save(user);
            Driver driverToInsert = new Driver(applicationUser.getUsername(), (int) (driverRepository.count() + 1) ,driver.getEmail(), driver.getPhoneNumber(), driver.getLongitude(), driver.getLatitude());
            return driverRepository.save(driverToInsert);
        }
        return null;
    }

    public List<Driver> getAllAvailableDrivers(){
        return driverRepository.findAllByIsAvailableIsTrue();
    }
    @Transactional
    public Driver deleteDriver(String driverUsername){
        Driver driver = driverRepository.findDriverByUsername(driverUsername);
        Optional<ApplicationUser> user = userRepository.findApplicationUserByUsername(driverUsername);
        if(driver != null && user.isPresent()) {
            driverRepository.delete(driver);
            userRepository.delete(user.get());
        }
        return driver;
    }
}
