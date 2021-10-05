package br.com.zup.order.controller.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.order.entity.Order;
import lombok.Data;

@Data(staticConstructor = "of")
public class OrderResponse {

    private final String id;

    private final String customerId;

    private final BigDecimal amount;

    private final List<OrderItemResponse> items;

    private final String status;

    public static OrderResponse fromEntity(Order order) {
        return OrderResponse.of(
                order.getId(),
                order.getCustomerId(),
                order.getAmount(),
                order.getItems().stream().map(OrderItemResponse::fromEntity).collect(Collectors.toList()),
                order.getStatus()
        );
    }
}
