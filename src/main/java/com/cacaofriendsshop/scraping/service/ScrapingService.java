package com.cacaofriendsshop.scraping.service;

import com.cacaofriendsshop.post.domain.Post;
import com.cacaofriendsshop.post.repository.PostRepository;
import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.product.repository.ProductRepository;
import com.cacaofriendsshop.scraping.scrapper.PostScrapper;
import com.cacaofriendsshop.scraping.scrapper.ProductScrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScrapingService {

    private final ProductRepository productRepository;
    private final PostRepository postRepository;

    public void collectProduct() {
        ProductScrapper productScrapper = new ProductScrapper();
        List<Product> productsByCrawling = productScrapper.createProductsByCrawling();
        productRepository.saveAll(productsByCrawling);
    }

    public void collectStory() {
        PostScrapper postScrapper = new PostScrapper();
        Set<Post> stories = postScrapper.crawlStory();
        postRepository.saveAll(stories);
    }
}
