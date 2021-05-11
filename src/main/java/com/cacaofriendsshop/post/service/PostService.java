package com.cacaofriendsshop.post.service;

import com.cacaofriendsshop.post.domain.Post;
import com.cacaofriendsshop.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Post> findByCharacterType(String characterType) {
        return postRepository.findByCharacterType(characterType);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
