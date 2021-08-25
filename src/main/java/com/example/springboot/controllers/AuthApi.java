package com.example.springboot.controllers;

import com.example.springboot.configure.security.JwtTokenUtil;
import com.example.springboot.dto.AuthRequest;
import com.example.springboot.dto.UserView;
import org.springframework.security.core.userdetails.User;
import com.example.springboot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @PostMapping("login")
    public ResponseEntity<UserView> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            request.getName(),
                            request.getPassword()));

            User user = (User) authenticate.getPrincipal();
            com.example.springboot.models.User dbUser = userRepository
                    .findByName(user.getUsername());
            UserView userView = new UserView();
            String tkn=jwtTokenUtil.generateAccessToken(dbUser);
            userView.setName(tkn);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, tkn)
                    .body(userView);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("register")
    public ResponseEntity register(@RequestBody @Valid UserView newuser) {
        try {
            com.example.springboot.models.User dbUser = userRepository
                    .findByName(newuser.getName());

            if(dbUser == null){
                dbUser = new com.example.springboot.models.User(
                        newuser.getName(),
                        newuser.getPassword(),
                        newuser.getBirthday(),
                        newuser.getGender(),
                        newuser.getPhone(),
                        newuser.getIcon(),
                        newuser.getRoles());

                userRepository.save(dbUser);
                return (ResponseEntity) ResponseEntity.ok();
            }
            else{
                return (ResponseEntity) ResponseEntity.badRequest();
            }
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}

}