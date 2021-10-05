package br.com.zup.order.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import br.com.zup.order.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrap;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic createNewTopicOrder() {
        return new NewTopic("created-orders", 3, (short) 1);
    }

    @Bean
    public Map<String, Object> producerConfigs() {

        Map<String, Object> buildProducer = kafkaProperties.buildProducerProperties();
        buildProducer.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        buildProducer.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return buildProducer;
    }

    @Bean
    public ProducerFactory<String, OrderCreatedEvent> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
