package com.cacaofriendsshop.product.domain;

import com.cacaofriendsshop.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private String comment;
    private Integer rating;
    private Integer likeCount;
    private String createdDate;

    public boolean isSameProductId(Long productId) {
        return this.product.getId().equals(productId);
    }

    public void updateLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public void updateRating(Integer rating) {
        this.rating = rating;
    }
}
