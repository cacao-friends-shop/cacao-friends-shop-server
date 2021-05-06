package com.cacaofriendsshop.product.service;

import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts(String characterType, String sort) {
        List<Product> productList = productRepository.findAll();
        if (sort != null) {
            switch (sort) {
                case "sellCount,desc":
                    productList = productRepository.findAllByOrderBySoldCountDesc();
                    break;
                case "price,asc":
                    productList = productRepository.findAllByOrderByPriceAsc();
                    break;
                case "price,desc":
                    productList = productRepository.findAllByOrderByPriceDesc();
                    break;
                default:
                    productList = productRepository.findAll();
                    break;
            }
        }

        if (characterType != null) {
            return productList.stream()
                .filter(product -> product.getCharacterType().equals(characterType))
                .collect(Collectors.toList());
        }

        return productList;
    }

    public Product getProductDetail(Long id) {
        return productRepository.findById(id).get();
    }

    public void addProductsForTest() {
        for (int i = 1; i <= 3; ++i) {
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
            productRepository.save(product);
            productRepository.save(product2);
        }
    }
}
