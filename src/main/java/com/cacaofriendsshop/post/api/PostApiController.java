package com.cacaofriendsshop.post.api;

import com.cacaofriendsshop.post.domain.Post;
import com.cacaofriendsshop.post.service.CommentService;
import com.cacaofriendsshop.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostApiController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping
    public ResponseEntity<Post> createOrUpdate(@RequestBody Post post) {
        Post savedPost = postService.save(post);
        return ResponseEntity
                .created(URI.create("/" + savedPost.getId()))
                .body(savedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commentService.deleteByPostId(id);
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
