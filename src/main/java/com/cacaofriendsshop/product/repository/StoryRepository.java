package com.cacaofriendsshop.product.repository;

import com.cacaofriendsshop.product.domain.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}
