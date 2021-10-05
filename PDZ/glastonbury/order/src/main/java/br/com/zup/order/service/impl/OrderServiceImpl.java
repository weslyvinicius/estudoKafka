package br.com.zup.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.event.OrderCreatedEvent;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderCreatedEvent> template;

    @Override
    public String save(CreateOrderRequest request) {
        String orderId = this.orderRepository.save(request.toEntity()).getId();

        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId(orderId)
                .customerId(request.getCustomerId())
                .amount(request.getAmount())
                .items(createItemMap(request))
                .build();

        this.template.send("created-orders", event);

        return orderId;
    }

    private Map<String, Integer> createItemMap(CreateOrderRequest request) {
        Map<String, Integer> result = new HashMap<>();
        for (CreateOrderRequest.OrderItemPart item : request.getItems()) {
            result.put(item.getId(), item.getQuantity());
        }
        return result;
    }

    @Override
    public List<OrderResponse> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
