package com.cacaofriendsshop.post.repository;

import com.cacaofriendsshop.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
