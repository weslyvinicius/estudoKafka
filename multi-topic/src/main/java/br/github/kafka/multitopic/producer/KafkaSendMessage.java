package br.github.kafka.multitopic.producer;


import br.github.kafka.multitopic.message.Message1;
import br.github.kafka.multitopic.message.Message2;
import br.github.kafka.multitopic.message.Message3;
import br.github.kafka.multitopic.topic.Topics;
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

    public void sendMessageString(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(Topics.FIRST_TOPIC, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(final SendResult<String, String> message) {
                LOGGER.info("Messagem enviada com sucesso");
                LOGGER.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                LOGGER.info("Ocorreu um erro messagem..");
                LOGGER.error("unable to send message= " + message, throwable);
            }
        });
    }

    public void sendMessageMessage1(Message1 message1) {
        ListenableFuture<SendResult<String, Message1>> future = kafkaTemplate.send(Topics.FIRST_TOPIC, message1);
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


    public void sendMessageMessage2(Message2 message2) {
        ListenableFuture<SendResult<String, Message2>> future = kafkaTemplate.send(Topics.FIRST_TOPIC, message2);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Message2>>() {

            @Override
            public void onSuccess(final SendResult<String, Message2> message) {
                LOGGER.info("Messagem enviada com sucesso");
                LOGGER.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                LOGGER.info("Ocorreu um erro messagem..");
                LOGGER.error("unable to send message= " + message2, throwable);
            }
        });
    }

    public void sendMessageMessage3(Message3 message3) {
        ListenableFuture<SendResult<String, Message3>> future = kafkaTemplate.send(Topics.FIRST_TOPIC, message3);
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
