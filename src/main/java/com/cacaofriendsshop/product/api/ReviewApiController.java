package com.cacaofriendsshop.product.api;

import com.cacaofriendsshop.product.domain.Review;
import com.cacaofriendsshop.product.dto.ReviewRequestDto;
import com.cacaofriendsshop.product.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/reviews")
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/{productId}")
    public ResponseEntity<List<Review>> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewService.findByProductId(productId));
    }

    @PostMapping
    public ResponseEntity<Review> createOrUpdate(@RequestBody ReviewRequestDto reviewRequestDto) {
        Review saved = reviewService.save(reviewRequestDto);
        return ResponseEntity.created(URI.create("/" + saved.getId())).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reviewService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
