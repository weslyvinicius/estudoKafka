package br.academy.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.academy.kafka.config.kafka.Groups;
import br.academy.kafka.config.kafka.Topics;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LendoTopics {

    @KafkaListener(topics = Topics.NEW_ORDER, containerFactory = "kafkaListenerContainerFactory", groupId =
            Groups.FRAUD_DETECTOR_SERVICE)
    public void receive(ConsumerRecord<String, String> record, @Payload String payload,
            @Headers MessageHeaders headers) {

        log.info("-----------------------------------------------------");
        log.info("key:  "+record.key());
        log.info("Message: "+payload);

        log.info("Headers - offset :  "+headers.get(KafkaHeaders.OFFSET));




    }
}
