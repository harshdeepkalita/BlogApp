package com.springboot.blog.springbootblogrestapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private long id;
    @NotEmpty(message = "name should not be empty.")
    private String name;
    @Email
    @NotEmpty(message = "email should not be empty.")
    private String email;
    @NotEmpty
    @Size(min=10,message = "min 10 characters for body.")
    private String body;
}
