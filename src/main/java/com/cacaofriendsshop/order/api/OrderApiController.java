package com.cacaofriendsshop.order.api;

import com.cacaofriendsshop.etc.config등등.CurrentMember;
import com.cacaofriendsshop.order.domain.Order;
import com.cacaofriendsshop.order.domain.OrderItem;
import com.cacaofriendsshop.order.dto.OrderItemDto;
import com.cacaofriendsshop.order.dto.OrderItemDtoWrapper;
import com.cacaofriendsshop.order.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/orders")
@RestController
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getOrders(@CurrentMember String email) {
        if (email == null) {
            return null;
        }

        return orderService.getOrders(email);
    }

    @GetMapping("/{id}")
    public Order getOrderDetail(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    public void submitOrder(@CurrentMember String email,
        @RequestBody OrderItemDtoWrapper orderItemDtoWrapper) {
        orderService.submitOrder(email, orderItemDtoWrapper.getOrderItemDtoList());
    }
}
