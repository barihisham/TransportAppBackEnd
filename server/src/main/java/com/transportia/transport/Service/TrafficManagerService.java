package com.transportia.transport.Service;

import com.transportia.transport.Auth.ApplicationUser;
import com.transportia.transport.Auth.UserRepository;
import com.transportia.transport.Model.TrafficManager;
import com.transportia.transport.Repository.TrafficManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.transportia.transport.SecurityConfig.ApplicationUserRole.TRAFFIC_MANAGER;

@Service
public class TrafficManagerService{

    private final TrafficManagerRepository trafficManagerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public TrafficManagerService(TrafficManagerRepository trafficManagerRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.trafficManagerRepository = trafficManagerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<TrafficManager> getAllTrafficManagers(){
        return trafficManagerRepository.findAll();
    }

    @Transactional
    public TrafficManager addTrafficManager(TrafficManager trafficManager){

        ApplicationUser user = new ApplicationUser(
                trafficManager.getUsername(),
                passwordEncoder.encode(trafficManager.getPassword()),
                TRAFFIC_MANAGER.getGrantedAuthorities(),
                true,
                true,
                true,
                true);
        ApplicationUser applicationUser = userRepository.save(user);

        TrafficManager trafficManagerToInsert = new TrafficManager(applicationUser.getUsername(), trafficManager.getEmail(), trafficManager.getPhoneNumber());

        return trafficManagerRepository.save(trafficManagerToInsert);
    }

    //TODO: Ta bort i ApplicationUser collection och i TrafficManager collection @ Trello
    @Transactional
    public TrafficManager deleteTrafficManager(String trafficManagerUsername){
        TrafficManager trafficManager = trafficManagerRepository.findTrafficManagerByUsername(trafficManagerUsername);
        Optional<ApplicationUser> user = userRepository.findApplicationUserByUsername(trafficManagerUsername);
        if(trafficManager != null && user.isPresent()) {
            trafficManagerRepository.delete(trafficManager);
            userRepository.delete(user.get());
        }
        return trafficManager;
    }
}
