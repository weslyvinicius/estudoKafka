package br.academy.kafka.config.kafka.consumer;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import br.academy.kafka.config.kafka.Groups;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class kafkaConfigConsumer {

    private final KafkaProperties kafkaProperties;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> consumerConfigs = kafkaProperties.buildConsumerProperties();
        consumerConfigs.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "300000");
        consumerConfigs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "100000");
        consumerConfigs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        consumerConfigs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        consumerConfigs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, Groups.AUTO_OFFSET_EARLIEST);

        return consumerConfigs;
    }


    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        //final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
        //jsonDeserializer.addTrustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
