package com.cacaofriendsshop.member.domain;

import com.cacaofriendsshop.cart.domain.Cart;
import com.cacaofriendsshop.etc.config등등.MemberLevel;
import com.cacaofriendsshop.order.domain.Order;
import com.cacaofriendsshop.order.domain.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String phone;

    private MemberLevel memberLevel;

    @OneToMany(mappedBy = "member")
    private List<Cart> cartList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

    @Builder
    public Member(Long id, String email, String password, String nickname, String phone,
        MemberLevel memberLevel) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.memberLevel = memberLevel;

    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
