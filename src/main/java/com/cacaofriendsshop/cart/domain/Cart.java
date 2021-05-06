package com.cacaofriendsshop.cart.domain;

import com.cacaofriendsshop.member.domain.Member;
import com.cacaofriendsshop.product.domain.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int amount;

    @Builder
    public Cart(Long id, Member member, Product product, int amount) {
        this.id = id;
        this.member = member;
        this.product = product;
        this.amount = amount;
    }

    public void increaseAmount(int amount) {
        this.amount += amount;
    }

    public void updateAmount(int amount) {
        this.amount = amount;
    }
}