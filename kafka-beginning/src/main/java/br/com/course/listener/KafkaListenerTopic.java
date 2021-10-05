package br.com.course.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import lombok.extern.slf4j.Slf4j;


//@Component
@Slf4j
public class KafkaListenerTopic {

    @KafkaListener(topics = "first", containerFactory = "kafkaListenerContainerFactory")
    public void receive(ConsumerRecord<String, String> cr, @Payload String payload,
            @Headers MessageHeaders headers) {

        log.info("received data='{}'", payload);

        log.info("1- "+cr.key());
        log.info("2- "+cr.topic());
        log.info("3- "+cr.partition());
        log.info("4- "+cr.offset());

            headers.keySet().forEach(key -> {
                log.info("{}: {}", key, headers.get(key));
                log.info("values: {}",headers.values());

        });
    }
}
