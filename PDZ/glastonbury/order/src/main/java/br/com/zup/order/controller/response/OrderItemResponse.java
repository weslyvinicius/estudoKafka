package br.com.zup.order.controller.response;

import java.math.BigDecimal;

import br.com.zup.order.entity.OrderItem;
import lombok.Data;

@Data(staticConstructor = "of")
public class OrderItemResponse {

    private final String id;

    private final String name;

    private final BigDecimal amount;

    private final Integer quantity;

    public static OrderItemResponse fromEntity(OrderItem orderItem) {
        return OrderItemResponse.of(
                orderItem.getId(),
                orderItem.getName(),
                orderItem.getAmount(),
                orderItem.getQuantity()
        );
    }
}
