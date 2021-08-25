package com.example.springboot.loader;

import com.example.springboot.constants.Roles;
import com.example.springboot.models.Role;
import com.example.springboot.models.User;
import com.example.springboot.repositories.RoleRepository;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DatabaseLoader(RoleRepository roleRepository,
                          UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository=roleRepository;
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) throws Exception {

        if(this.roleRepository.count()==0)
        {
            this.roleRepository.save(new Role(Roles.Admin));
            this.roleRepository.save(new Role(Roles.User));
        }

        if(this.userRepository.count()==0)
        {
//            this.userRepository.save(
//                    new User("semen@gmail.com",passwordEncoder.encode("123456"), "blabla")
//            );
        }
    }
}