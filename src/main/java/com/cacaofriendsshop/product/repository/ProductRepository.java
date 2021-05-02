package com.cacaofriendsshop.product.repository;

import com.cacaofriendsshop.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
