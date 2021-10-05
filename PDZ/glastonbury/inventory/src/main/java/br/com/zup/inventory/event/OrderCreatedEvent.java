package br.com.zup.inventory.event;

import java.math.BigDecimal;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private Map<String, Integer> items;


}
