package com.cacaofriendsshop.order.service;

import com.cacaofriendsshop.member.domain.Member;
import com.cacaofriendsshop.member.repository.MemberRepository;
import com.cacaofriendsshop.order.domain.Order;
import com.cacaofriendsshop.order.domain.OrderItem;
import com.cacaofriendsshop.order.dto.OrderItemDto;
import com.cacaofriendsshop.order.repository.OrderItemRepository;
import com.cacaofriendsshop.order.repository.OrderRepository;
import com.cacaofriendsshop.product.domain.Product;
import com.cacaofriendsshop.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public List<Order> getOrders(String email) {
        Member member = memberRepository.findByEmail(email).get();

        return member.getOrderList();
    }

    @Transactional
    public void submitOrder(String email, List<OrderItemDto> orderItemDtoList) {
        Member member = memberRepository.findByEmail(email).get();

        Order order = Order.builder()
            .member(member)
            .build();

        orderRepository.save(order);

        List<OrderItem> orderItemList = orderItemDtoList.stream()
            .map(orderItemDto -> OrderItem.builder()
                .amount(orderItemDto.getAmount())
                .order(order)
                .product(productRepository.findById(orderItemDto.getProductId()).get())
                .build())
            .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItemList);
    }

    public Order getOrder(Long id) {
        Order order = orderRepository.findById(id).get();

        return order;
    }
}