package com.cacaofriendsshop.cart.api;

import com.cacaofriendsshop.cart.domain.Cart;
import com.cacaofriendsshop.cart.service.CartService;
import com.cacaofriendsshop.etc.config등등.CurrentMember;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/carts")
@RestController
public class CartApiController {

    private final CartService cartService;

    @PostMapping("/{id}")
    public void addCart(@PathVariable Long id, @CurrentMember String email,
        @RequestBody Map<String, Integer> map) {
        cartService.addCart(id, email, map.get("amount"));
    }

    @GetMapping
    public List<Cart> getMyCarts(@CurrentMember String email) {
        if (email == null) {
            return null;
        }

        return cartService.getMyCarts(email);
    }

    @PatchMapping("/{id}")
    public void updateCartAmount(@PathVariable Long id, @RequestBody Map<String, Integer> map) {
        cartService.updateCartAmount(id, map.get("amount"));
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}
