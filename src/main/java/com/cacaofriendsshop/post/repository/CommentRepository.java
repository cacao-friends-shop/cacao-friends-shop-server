package com.cacaofriendsshop.post.repository;

import com.cacaofriendsshop.post.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
