package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.dto.CommentDto;
import com.springboot.blog.springbootblogrestapi.entity.Comment;
import com.springboot.blog.springbootblogrestapi.repository.CommentRepository;
import com.springboot.blog.springbootblogrestapi.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.CommentEvent;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping("/posts/{post_id}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long post_id,
                                                    @Valid @RequestBody CommentDto commentDto)
    {
        return new ResponseEntity<>(commentService.createComment(post_id,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{post_id}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable long post_id)

    {
        return commentService.getCommentsByPostId(post_id);
    }
@GetMapping("posts/{post_id}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentsById(
            @PathVariable long post_id, @PathVariable long id)
    {
        CommentDto commentDto = commentService.getCommentsById(post_id,id);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId,
                                                    @PathVariable long commentId, @Valid @RequestBody CommentDto commentDto)
    {
        CommentDto updatedCommentDto = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId,
                                                @PathVariable long commentId)
    {
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);
    }
}
