package com.cacaofriendsshop.product.scraping;

import com.cacaofriendsshop.product.repository.StoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScrapingServiceTest {

    @Autowired
    StoryRepository storyRepository;

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

}