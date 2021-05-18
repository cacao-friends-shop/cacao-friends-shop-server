package com.cacaofriendsshop.post.api;

import com.cacaofriendsshop.post.domain.Comment;
import com.cacaofriendsshop.post.dto.CommentRequestDto;
import com.cacaofriendsshop.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @GetMapping("/{postId}/posts")
    public ResponseEntity<List<Comment>> findByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.findByPostId(postId));
    }

    @GetMapping("/{memberId}/members")
    public ResponseEntity<List<Comment>> findByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(commentService.findByMemberId(memberId));
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody CommentRequestDto commentRequestDto) {
        Comment saved = commentService.save(commentRequestDto);
        return ResponseEntity.created(URI.create("/" + saved.getId())).body(saved);
    }

    @PutMapping
    public ResponseEntity<Comment> update(@RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.save(commentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
