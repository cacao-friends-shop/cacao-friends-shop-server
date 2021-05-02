package com.cacaofriendsshop.product.scraping;

import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.product.domain.Story;
import com.cacaofriendsshop.product.repository.ProductRepository;
import com.cacaofriendsshop.product.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScrapingService {

    private final ProductRepository productRepository;
    private final StoryRepository storyRepository;

    public void collectProduct() {
        ProductScrapper productScrapper = new ProductScrapper();
        List<Product> productsByCrawling = productScrapper.createProductsByCrawling();
        productRepository.saveAll(productsByCrawling);
    }

    public void collectStory() {
        StoryScrapper storyScrapper = new StoryScrapper();
        Set<Story> stories = storyScrapper.crawlStory();
        storyRepository.saveAll(stories);
    }
}
