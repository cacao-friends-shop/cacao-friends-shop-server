package com.cacaofriendsshop.order.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDtoWrapper {

    private List<OrderItemDto> orderItemDtoList;
}
