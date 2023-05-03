package com.springboot.blog.springbootblogrestapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;
    @NotEmpty
    @Size(min = 5,message = "Post title should have at least 5 characters")
    private String title; // not null, min 5 chars
    @NotEmpty
    @Size(min = 10,message = "Post title should have at least 10 characters")
    private String description; // not null and min 10 characters
    @NotEmpty
    private String content; // not null
    private Set<CommentDto> comments;

    private Long categoryId;
}
