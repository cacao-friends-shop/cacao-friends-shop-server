package com.cacaofriendsshop.product.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDto {

    private Long id;
    private Long productId;
    private Long memberId;
    private String comment;
    private Integer likeCount;
    private Integer rating;
    private String createdDate;

}
