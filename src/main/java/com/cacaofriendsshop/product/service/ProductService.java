package com.cacaofriendsshop.product.service;

import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getProducts(Optional<String> characterType, Pageable pageable) {
        if (characterType.isPresent()) {
            return productRepository.findByCharacterType(characterType.get(), pageable);
        }
        return productRepository.findAll(pageable);
    }

    public Product getProductDetail(Long id) {
        return productRepository.findById(id).get();
    }

    public void addProductsForTest() {
        for (int i = 1; i <= 33; ++i) {
            Product product = Product.builder()
                .characterType("무지")
                .price(String.valueOf(i * 10000))
                .soldCount(i * 100)
                .build();
            Product product2 = Product.builder()
                .characterType("어피치")
                .price(String.valueOf(i * 10000))
                .soldCount(i * 100)
                .build();
            Product product3 = Product.builder()
                .characterType("라이언")
                .price(String.valueOf(i * 10000))
                .soldCount(i * 100)
                .build();
            productRepository.save(product);
            productRepository.save(product2);
            productRepository.save(product3);
        }
    }
}
