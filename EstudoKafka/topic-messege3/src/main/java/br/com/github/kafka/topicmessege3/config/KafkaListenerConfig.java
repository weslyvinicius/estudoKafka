package br.com.github.kafka.topicmessege3.config;


import br.com.github.kakfa.messege.Message3;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaListenerConfig {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    private final KafkaProperties kafkaProperties;

    public KafkaListenerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        return kafkaProperties.buildConsumerProperties();
    }

    // Json Consumer factory generic
    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        consumerConfigs().put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfigs().put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerConfigs().put(JsonDeserializer.TRUSTED_PACKAGES, "br.com.github.kakfa.messege.Message3");

        return new DefaultKafkaConsumerFactory(consumerConfigs());

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message3> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Message3> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
