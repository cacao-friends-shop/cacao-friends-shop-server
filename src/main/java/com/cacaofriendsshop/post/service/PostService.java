package com.cacaofriendsshop.post.service;

import com.cacaofriendsshop.post.domain.Post;
import com.cacaofriendsshop.post.dto.PostResponseDto;
import com.cacaofriendsshop.post.dto.PostResponseWithCommentDto;
import com.cacaofriendsshop.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentService commentService;

    public List<PostResponseDto> findAll() {
        return createPostResponseDto(postRepository.findAll());
    }

    public PostResponseWithCommentDto findById(Long id) {
        Post byId = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return PostResponseWithCommentDto.of(byId, commentService.findByPostId(byId.getId()));
    }

    public List<PostResponseDto> findByCharacterType(String characterType) {
        return createPostResponseDto(postRepository.findByCharacterType(characterType));
    }

    private List<PostResponseDto> createPostResponseDto(List<Post> posts) {
        return posts.stream()
                .map(post -> PostResponseDto.of(post, commentService.findByPostId(post.getId())))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post update(Post post) {
        Post byId = postRepository.findById(post.getId())
                .orElseThrow(IllegalArgumentException::new);
        byId.update(post);
        return byId;
    }
}
