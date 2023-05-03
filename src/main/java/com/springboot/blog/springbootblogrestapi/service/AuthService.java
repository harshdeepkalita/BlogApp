package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.dto.LoginDto;
import com.springboot.blog.springbootblogrestapi.dto.SignupDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String signUp(SignupDto signupDto);
}
