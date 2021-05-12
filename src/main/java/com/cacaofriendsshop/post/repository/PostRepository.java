package com.cacaofriendsshop.post.repository;

import com.cacaofriendsshop.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCharacterType(String characterType);
}
