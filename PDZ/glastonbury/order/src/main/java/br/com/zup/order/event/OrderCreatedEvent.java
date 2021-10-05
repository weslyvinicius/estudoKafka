package br.com.zup.order.event;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCreatedEvent {
    private final String orderId;
    private final String customerId;
    private final BigDecimal amount;
    private final Map<String,Integer> items;


}
