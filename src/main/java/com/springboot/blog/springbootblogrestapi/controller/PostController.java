package com.springboot.blog.springbootblogrestapi.controller;
import com.springboot.blog.springbootblogrestapi.dto.PostDto;
import com.springboot.blog.springbootblogrestapi.dto.PostResponse;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping // create a new post
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto)
    {
        return new ResponseEntity<>(postService.createdPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping // get all posts + supports pagination
    public PostResponse getPosts(@RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
                                 @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
                                 @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
                                 @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir)
            {
        return postService.getPosts(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}") //get post by id
    public ResponseEntity<PostDto> getPostsById(@PathVariable long id)
    {
        return ResponseEntity.ok(postService.getPostsById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}") // update the post by id
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable long id)
    {
        return new ResponseEntity<>(postService.updatePost(postDto,id),HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}") // delete a post by id
    public ResponseEntity<String> deletePostbyId(@PathVariable long id)
    {
        postService.deletePostbyId(id);
        return new ResponseEntity<>("deleted post successfully",HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Long id)
    {
        List<PostDto> postDtos = postService.getPostByCategory(id);
        return ResponseEntity.ok(postDtos);
    }


}
