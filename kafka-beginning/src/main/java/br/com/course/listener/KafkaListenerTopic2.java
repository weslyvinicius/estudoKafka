package br.com.course.listener;

import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.course.messagem.messagemDTO;
import br.com.course.topic.Topics;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaListenerTopic2 {

    @KafkaListener(topics = Topics.SECUND_TOPIC, clientIdPrefix = "json",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenAsObject(
            ConsumerRecord<String, messagemDTO> cr,
            @Payload messagemDTO payload) {

        log.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
                typeIdHeader(cr.headers()), payload, cr.toString());
    }

    private static String typeIdHeader(Headers headers) {
        return StreamSupport.stream(headers.spliterator(), false)
                .filter(header -> header.key().equals("__TypeId__"))
                .findFirst().map(header -> new String(header.value())).orElse("N/A");
    }
}
