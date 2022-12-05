package ru.qmbo.mc2.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import ru.qmbo.mc2.model.Message;

import java.util.concurrent.CountDownLatch;

/**
 * KafkaMessageController
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 02.12.2022
 */
@Controller
@Log4j2
public class KafkaConsumer {

    private CountDownLatch latch = new CountDownLatch(1);
    private Message message;

    /**
     * Gets message.
     *
     * @param input the input
     */
    @KafkaListener(topics = {"cycle-message"})
    public void getMessage(ConsumerRecord<Integer, String> input) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            message = objectMapper.readValue(input.value(), Message.class);
            latch.countDown();
        } catch (JsonProcessingException e) {
            log.error("String to Message parse fail.");
        }
    }

    public void resetLatch() {
        latch = new CountDownLatch(1);
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public Message getMessage() {
        return this.message;
    }
}
