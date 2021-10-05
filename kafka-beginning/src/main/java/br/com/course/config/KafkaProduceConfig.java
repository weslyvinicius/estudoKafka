package br.com.course.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


//@Configuration
public class KafkaProduceConfig {

    private static final String BROKER_HOST = "localhost:9092";
    private static final String ACKS = "all";

    @Bean
    public Map<String, Object> producerConfigs() {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_HOST);
        //props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, ACKS);

        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        DefaultKafkaProducerFactory<String, String> kafkaProducerFactory = new DefaultKafkaProducerFactory<>(producerConfigs());
        //kafkaProducerFactory.setValueSerializer(new JsonSerializer<>(new ObjectMapper().registerModule(new
        // JavaTimeModule()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)));
        return kafkaProducerFactory;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate(this.producerFactory());
    }
}
