package br.com.zup.order.controller.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.zup.order.entity.Order;
import br.com.zup.order.entity.OrderItem;
import lombok.Data;

@Data
public class CreateOrderRequest {

    private String customerId;

    private BigDecimal amount;

    private List<OrderItemPart> items;

    public Order toEntity() {
        return Order.builder()
                .id (UUID.randomUUID().toString())
                .customerId(this.customerId)
                .amount(this.amount)
                .items( this.items.stream()
                        .map(OrderItemPart::toEntity)
                        .collect(Collectors.toList()))
                .status("pending")
                .build();
    }

    @Data
    public static class OrderItemPart {

        private String id;

        private String name;

        private BigDecimal amount;

        private Integer quantity;

        public OrderItem toEntity() {
            return OrderItem.builder()
                    .id(this.id)
                    .name(this.name)
                    .amount(this.amount)
                    .quantity(this.quantity)
                    .build();
        }
    }
}
