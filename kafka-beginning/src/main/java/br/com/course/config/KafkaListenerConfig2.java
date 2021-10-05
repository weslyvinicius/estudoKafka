package br.com.course.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableKafka
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class KafkaListenerConfig2 {

    private final KafkaProperties kafkaProperties;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> consumerConfigs = kafkaProperties.buildConsumerProperties();
        consumerConfigs.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        consumerConfigs.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "5000000");
        consumerConfigs.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "100000");
        return consumerConfigs;
    }


    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        consumerConfigs().put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        consumerConfigs().put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
        jsonDeserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(), new StringDeserializer(), jsonDeserializer
        );

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    // String Consumer Configuration
//
//    @Bean
//    public ConsumerFactory<String, String> stringConsumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(
//                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new StringDeserializer()
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerStringContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(stringConsumerFactory());
//
//        return factory;
//    }
//
//    // Byte Array Consumer Configuration
//
//    @Bean
//    public ConsumerFactory<String, byte[]> byteArrayConsumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(
//                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new ByteArrayDeserializer()
//        );
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerByteArrayContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(byteArrayConsumerFactory());
//        return factory;
//    }
}
