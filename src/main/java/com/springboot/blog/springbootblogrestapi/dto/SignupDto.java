package com.springboot.blog.springbootblogrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
