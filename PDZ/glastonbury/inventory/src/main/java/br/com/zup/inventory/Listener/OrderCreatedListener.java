package br.com.zup.inventory.Listener;


import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

import br.com.zup.inventory.event.OrderCreatedEvent;

public class OrderCreatedListener {

    @KafkaListener(topics = "created-orders")
    public void listen(ConsumerRecord<String, OrderCreatedEvent> cr,
            @Payload OrderCreatedEvent payload) throws IOException {
        System.out.println(payload);
    }

}
