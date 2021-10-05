package br.com.github.kakfa.topicmessege1.config;

import br.com.github.kakfa.messege.Message1;
import br.com.github.kakfa.topics.Topics;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    private final KafkaProperties kafkaProperties;

    public KafkaProducerConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public Map<String, Object> producerConfigs() {

        Map<String, Object> buildProducer = kafkaProperties.buildProducerProperties();
        buildProducer.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        buildProducer.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return buildProducer;
    }

    @Bean
    public ProducerFactory<String, Message1> producerFactory() {
        return new DefaultKafkaProducerFactory(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Message1> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // Criando topic no Kafka.
    @Bean
    public NewTopic topicExample() {
        return TopicBuilder.name(Topics.MESSAGE1_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
