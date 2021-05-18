package com.cacaofriendsshop.scraping;

import com.cacaofriendsshop.post.repository.PostRepository;
import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.product.repository.ProductRepository;
import com.cacaofriendsshop.scraping.service.ScrapingService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@Disabled
@SpringBootTest
class ScrapingServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private ScrapingService scrapingService;

    @Test
    void collectProduct() {
        scrapingService.collectProduct();
    }

    @Test
    void collectStory() {
        scrapingService.collectStory();
    }

    @DisplayName("")
    @Test
    void name() {
        List<Product> products = productRepository.findAll().stream()
                .filter(product -> "프로도".equals(product.getCharacterType()))
                .collect(Collectors.toList());
        productRepository.deleteAll(products);
    }
}