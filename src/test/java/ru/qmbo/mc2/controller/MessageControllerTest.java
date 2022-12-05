package ru.qmbo.mc2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.qmbo.mc2.config.KafkaConsumer;
import ru.qmbo.mc2.model.Message;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MessageControllerTest {
    @Autowired
    private MessageController messageController;
    @Autowired
    private KafkaConsumer consumer;

    @Test
    public void whenMessageThenSendMessage() throws IOException, InterruptedException {
        this.messageController.processRequestWithoutResponse(new Message());
        boolean messageConsumed = consumer.getLatch().await(20, TimeUnit.SECONDS);
        Message message = consumer.getMessage();
        assertThat(messageConsumed).isTrue();
        assertThat(message).isNotNull();
        assertThat(message.getMc2Timestamp()).isNotNull();
    }
}