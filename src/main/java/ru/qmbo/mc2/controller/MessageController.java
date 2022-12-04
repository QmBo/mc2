package ru.qmbo.mc2.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.qmbo.mc2.model.Message;
import ru.qmbo.mc2.service.KafkaProducerService;

import java.io.IOException;

import static ru.qmbo.mc2.config.WebsocketConfiguration.WS_TOPIC_DESTINATION_PREFIX;

/**
 * MessageController
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 04.12.2022
 */
@Controller
@Log4j2
public class MessageController {
    private final KafkaProducerService producerService;
    /**
     * The constant SAMPLE_ENDPOINT_MESSAGE_MAPPING.
     */
    public static final String SAMPLE_ENDPOINT_MESSAGE_MAPPING = "/sampleEndpoint";
    /**
     * The constant SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING.
     */
    public static final String SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING = "/sampleEndpointWithoutResponse";
    /**
     * The constant WS_TOPIC_NO_RESPONSE.
     */
    public static final String WS_TOPIC_NO_RESPONSE = WS_TOPIC_DESTINATION_PREFIX+"/messagesNoResponse";

    /**
     * Instantiates a new Message controller.
     *
     * @param producerService the producer service
     */
    public MessageController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * Process request without response.
     *
     * @param message the message
     * @throws IOException the io exception
     */
    @MessageMapping(SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING)
    @SendTo(WS_TOPIC_NO_RESPONSE)
    public void processRequestWithoutResponse(Message message) throws IOException {
        log.debug("Received new request without responses message {}.", message);
        this.producerService.send(message);
    }
}
