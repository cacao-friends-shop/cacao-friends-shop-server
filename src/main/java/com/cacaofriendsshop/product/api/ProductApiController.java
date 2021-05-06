package com.cacaofriendsshop.product.api;

import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductApiController {

    private final ProductService productService;

    // 상품 목록 조회 (타입, 정렬, 검색)
    @GetMapping
    public List<Product> getProducts(
        @RequestParam(required = false) String characterType,
        @RequestParam(required = false) String sort) {
        return productService.getProducts(characterType, sort);
    }

    // 상품 상세 정보 조회
    @GetMapping("/{id}")
    public Product getProductDetail(@PathVariable Long id) {
        return productService.getProductDetail(id);
    }

    @PostMapping
    public void addProductsForTest() {
        productService.addProductsForTest();
    }
}