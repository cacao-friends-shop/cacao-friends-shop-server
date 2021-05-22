package com.cacaofriendsshop.product.api;

import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.product.service.ProductService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

    // 상품 목록 조회
    @GetMapping
    public Page<Product> getProducts(
        @RequestParam Optional<String> characterType,
        @PageableDefault(size = 8, sort = "soldCount", direction = Direction.DESC) Pageable pageable) {
        return productService.getProducts(characterType, pageable);
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