package br.com.github.kakfa.topicmessege1.producer;


import br.com.github.kakfa.messege.Message1;
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

    public void sendMessage(Message1 message1) {
        ListenableFuture<SendResult<String, Message1>> future = kafkaTemplate.send(Topics.MESSAGE1_TOPIC, message1);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message1>>() {

            @Override
            public void onSuccess(final SendResult<String, Message1> message) {
                LOGGER.info("Messagem enviada com sucesso");
                LOGGER.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                LOGGER.info("Ocorreu um erro messagem..");
                LOGGER.error("unable to send message= " + message1, throwable);
            }
        });
    }


}
