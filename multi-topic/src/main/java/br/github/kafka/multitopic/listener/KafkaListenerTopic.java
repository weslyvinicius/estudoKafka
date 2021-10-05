package br.github.kafka.multitopic.listener;


import br.github.kafka.multitopic.topic.Topics;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;



@Component
public class KafkaListenerTopic {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    @KafkaListener(topics = Topics.FIRST_TOPIC, clientIdPrefix = "json",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenAsObject(ConsumerRecord<String, Object> cr,
                               @Payload Object payload) {
        LOGGER.info("Logger 1 [JSON] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
                payload, cr.toString());
    }

    @KafkaListener(topics = Topics.FIRST_TOPIC, clientIdPrefix = "string",
            containerFactory = "kafkaListenerStringContainerFactory")
    public void listenasString(ConsumerRecord<String, String> cr,
                               @Payload String payload) {
        LOGGER.info("Logger 2 [String] received key {}: Type [{}] | Payload: {} | Record: {}", cr.key(),
                 payload, cr.toString());
    }

}
