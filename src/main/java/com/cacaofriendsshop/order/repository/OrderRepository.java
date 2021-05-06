package com.cacaofriendsshop.order.repository;

import com.cacaofriendsshop.member.domain.Member;
import com.cacaofriendsshop.order.domain.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
