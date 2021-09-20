package com.transportia.transport.Service;

import com.transportia.transport.Auth.ApplicationUser;
import com.transportia.transport.Auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static com.transportia.transport.SecurityConfig.ApplicationUserRole.ADMIN;

public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ApplicationUser addAdmin(ApplicationUser applicationUser){
        ApplicationUser user = new ApplicationUser(
                applicationUser.getUsername(),
                passwordEncoder.encode(applicationUser.getPassword()),
                ADMIN.getGrantedAuthorities(),
                true,
                true,
                true,
                true);

        return userRepository.save(user);
    }
}
