package com.cacaofriendsshop.cart.service;

import com.cacaofriendsshop.cart.domain.Cart;
import com.cacaofriendsshop.cart.repository.CartRepository;
import com.cacaofriendsshop.member.domain.Member;
import com.cacaofriendsshop.member.repository.MemberRepository;
import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void addCart(Long id, String email, int amount) {
        Product product = productRepository.findById(id).get();
        Member member = memberRepository.findByEmail(email).get();
        Cart cart = cartRepository.findOneByMemberAndProduct(member, product);

        if (cart != null) {
            cart.increaseAmount(amount);
        } else {
            cart = Cart.builder()
                .member(member)
                .product(product)
                .amount(amount)
                .build();
            cartRepository.save(cart);
        }
    }

    public List<Cart> getMyCarts(String email) {
        Member member = memberRepository.findByEmail(email).get();
        return cartRepository.findAllByMember(member);
    }

    @Transactional
    public void updateCartAmount(Long id, Integer amount) {
        Cart cart = cartRepository.findById(id).get();
        cart.updateAmount(amount);
    }

    @Transactional
    public void deleteCart(Long id) {
        Cart cart = cartRepository.findById(id).get();
        cartRepository.delete(cart);
    }
}
