package com.cacaofriendsshop.product.service;

import com.cacaofriendsshop.member.service.MemberService;
import com.cacaofriendsshop.product.domain.Review;
import com.cacaofriendsshop.product.dto.ReviewRequestDto;
import com.cacaofriendsshop.product.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberService memberService;
    private final ProductService productService;

    public List<Review> findByProductId(Long productId) {
        return reviewRepository.findAll().stream()
                .filter(review -> review.isSameProductId(productId))
                .collect(Collectors.toList());
    }

    public Review save(ReviewRequestDto reviewRequestDto) {
        Review saved = createReview(reviewRequestDto);
        return saved;
    }

    public Review createReview(ReviewRequestDto reviewRequestDto) {
        Review review = Review.builder()
                .id(reviewRequestDto.getId())
                .comment(reviewRequestDto.getComment())
                .createdDate(reviewRequestDto.getCreatedDate())
                .likeCount(reviewRequestDto.getLikeCount())
                .rating(reviewRequestDto.getRating())
                .member(memberService.findById(reviewRequestDto.getMemberId()))
                .product(productService.getProductDetail(reviewRequestDto.getProductId()))
                .build();
        if (reviewRequestDto.getLikeCount() == null) {
            review.updateLikeCount(0);
        }
        if (reviewRequestDto.getRating() == null) {
            review.updateRating(0);
        }
        return review;
    }

    public void deleteById(Long id) {
        reviewRepository.deleteById(id
        );
    }
}
