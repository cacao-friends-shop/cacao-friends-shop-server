package com.cacaofriendsshop.scraping;

import com.cacaofriendsshop.post.repository.PostRepository;
import com.cacaofriendsshop.scraping.service.ScrapingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScrapingServiceTest {

    @Autowired
    PostRepository postRepository;

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