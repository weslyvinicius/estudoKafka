package br.com.course.controller;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.course.messagem.messagemDTO;
import br.com.course.topic.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("kafka-2")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageProducer2Controller {

    private final KafkaTemplate<String, Object> template;

    @Value("${tpd.messages-per-request}")
    private int messagesPerRequest;


    @PostMapping("producer/{mensagem}")
    public String envidandoMenssagem(@PathVariable String mensagem) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(messagesPerRequest);
        IntStream.range(0, messagesPerRequest)
                .forEach(i -> this.template.send(Topics.SECUND_TOPIC, String.valueOf(i),
                        messagemDTO.builder()
                                .identifier(i)
                                .message("Enviando_"+i+" : "+mensagem)
                                .build())
                );
        latch.await(15, TimeUnit.SECONDS);
        log.info("mensagens enviadas");
        return "Hello Kafka sucess !!";
    }
}
