package br.com.github.kafka.topicmessege3.producer;


import br.com.github.kakfa.messege.Message3;
import br.com.github.kakfa.topics.Topics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaSendMessage {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    private final KafkaTemplate kafkaTemplate;

    public KafkaSendMessage(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageMessage3(Message3 message3) {
        ListenableFuture<SendResult<String, Message3>> future = kafkaTemplate.send(Topics.MESSAGE3_TOPIC, message3);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message3>>() {

            @Override
            public void onSuccess(final SendResult<String, Message3> message) {
                LOGGER.info("Messagem enviada com sucesso");
                LOGGER.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                LOGGER.info("Ocorreu um erro messagem..");
                LOGGER.error("unable to send message= " + message3, throwable);
            }
        });
    }

}
