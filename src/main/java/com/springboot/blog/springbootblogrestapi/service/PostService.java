package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createdPost(PostDto postDto);
    PostResponse getPosts(int pageNo, int pageSize, String sortBy,String sortDir);

    PostDto getPostsById(long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostbyId(long id);

    List<PostDto> getPostByCategory(Long categoryId);
}
