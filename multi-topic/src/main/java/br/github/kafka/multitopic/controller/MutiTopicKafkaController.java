package br.github.kafka.multitopic.controller;

import br.github.kafka.multitopic.message.Message1;
import br.github.kafka.multitopic.message.Message2;
import br.github.kafka.multitopic.message.Message3;
import br.github.kafka.multitopic.producer.KafkaSendMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka-message")
public class MutiTopicKafkaController {

    private final KafkaSendMessage kafkaSendMessage;

    public MutiTopicKafkaController(final KafkaSendMessage kafkaSendMessage) {
        this.kafkaSendMessage = kafkaSendMessage;
    }

    @PostMapping("/{message}")
    public void sendMessageString(@PathVariable("message") String message){
        kafkaSendMessage.sendMessageString(message);
    }

    @PostMapping("/message1")
    public void sendMessageMessage1(@RequestBody Message1 message1){
        kafkaSendMessage.sendMessageMessage1(message1);
    }

    @PostMapping("/message2")
    public void sendMessageMessage2(@RequestBody Message2 message2){
        kafkaSendMessage.sendMessageMessage2(message2);
    }

    @PostMapping("/message3")
    public void sendMessageMessage3(@RequestBody Message3 message3){
        kafkaSendMessage.sendMessageMessage3(message3);
    }
}
