package com.springboot.blog.springbootblogrestapi.service.impl;

import com.springboot.blog.springbootblogrestapi.dto.LoginDto;
import com.springboot.blog.springbootblogrestapi.dto.SignupDto;
import com.springboot.blog.springbootblogrestapi.entity.Role;
import com.springboot.blog.springbootblogrestapi.entity.User;
import com.springboot.blog.springbootblogrestapi.exception.BlogAPIException;
import com.springboot.blog.springbootblogrestapi.repository.RoleRepository;
import com.springboot.blog.springbootblogrestapi.repository.UserRepository;
import com.springboot.blog.springbootblogrestapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User Logged-in successfully!.";
    }

    @Override
    public String signUp(SignupDto signupDto) {
        // check for username existence
        if(userRepository.existsByUsername(signupDto.getUsername()))
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"username exists!");
        }
        // check for email existence
        if (userRepository.existsByEmail(signupDto.getEmail()))
        {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"email exists!");
        }

        User user = new User();
        user.setName(signupDto.getName());
        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        // add roles to the user
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);
        return "user signed up successfully";
    }}



//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//    private AuthenticationManager authenticationManager;
//    private UserRepository userRepository;
//    private RoleRepository roleRepository;
//    private PasswordEncoder passwordEncoder;
//
//
//    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//
//
//
//    @Override
//    public String login(LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(),
//                loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return "User Logged in successfully!";
//    }
//
//    @Override
//    public String signUp(SignupDto signupDto) {
//        // check for username existence
//        if(userRepository.existsByUsername(signupDto.getUsername()))
//        {
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"username exists!");
//        }
//        // check for email existence
//        if (userRepository.existsByEmail(signupDto.getEmail()))
//        {
//            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"email exists!");
//        }
//
//        User user = new User();
//        user.setName(signupDto.getName());
//        user.setUsername(signupDto.getUsername());
//        user.setEmail(signupDto.getEmail());
//        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
//
//        // add roles to the user
//        Set<Role> roles = new HashSet<>();
//        Role userRole = roleRepository.findByName("ROLE_USER").get();
//        roles.add(userRole);
//        user.setRoles(roles);
//
//        userRepository.save(user);
//        return "user signed up successfully";
//    }
//}
//
