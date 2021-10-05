package br.com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.course.topic.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@RestController
//@RequestMapping("kafka")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class MessageProduceController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("publish/{message}")
    public String messageProduce(@PathVariable String message){
        kafkaTemplate.send(Topics.FIRST_TOPIC, message);
        return "Messagem enviado com sucesso";
    }

    @Async
    @PostMapping("publish-callback/{message}")
    public String messageProduceCallBack(@PathVariable String message){

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(Topics.FIRST_TOPIC, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(final SendResult<String, String> message) {
                log.info("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                log.error("unable to send message= " + message, throwable);
            }
        });

        return "Messagem enviado com sucesso";
    }


}
