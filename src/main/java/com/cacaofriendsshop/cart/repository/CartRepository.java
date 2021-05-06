package com.cacaofriendsshop.cart.repository;

import com.cacaofriendsshop.cart.domain.Cart;
import com.cacaofriendsshop.member.domain.Member;
import com.cacaofriendsshop.product.domain.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findOneByMemberAndProduct(Member member, Product product);

    List<Cart> findAllByMember(Member member);
}
