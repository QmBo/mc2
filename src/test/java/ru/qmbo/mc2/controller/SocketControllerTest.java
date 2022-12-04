package ru.qmbo.mc2.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.qmbo.mc2.model.Message;
import ru.qmbo.mc2.service.KafkaProducerService;

import java.io.IOException;

@SpringBootTest
class SocketControllerTest {
    @Autowired
    private KafkaProducerService service;

    @Test
    public void sendMessage() throws IOException {
        Message message = new Message().setId(1).setSessionId(2);
        service.send(message);
    }
}