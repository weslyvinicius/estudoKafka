package br.academy.kafka.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.academy.kafka.config.kafka.Topics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("order")
@Slf4j
public class OrderController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("publish/{message}")
    public String messageProduce(@PathVariable String message){

        ListenableFuture<SendResult<String, String>> fultureMessage = kafkaTemplate.send(Topics.NEW_ORDER, message);
        fultureMessage.addCallback(
                new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(final SendResult<String, String> message) {
                log.info("Menssagem Enviada com Sucesso: \n" +
                        "offset= {} \n"+
                        "partition= {} \n"+
                        "topic= {} \n", message.getRecordMetadata().offset(), message.getRecordMetadata().partition()
                        , message.getRecordMetadata().topic());
            }

            @Override
            public void onFailure(final Throwable throwable) {
                log.info("Erro ao enviar menssagem:  {}", throwable.getStackTrace());
            }
        });

        return "Mensagem enviada";
    }

}
