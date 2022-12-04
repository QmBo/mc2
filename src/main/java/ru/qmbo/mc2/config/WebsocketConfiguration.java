package ru.qmbo.mc2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import static ru.qmbo.mc2.controller.MessageController.SAMPLE_ENDPOINT_MESSAGE_MAPPING;
import static ru.qmbo.mc2.controller.MessageController.SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING;

/**
 * WebsocketConfiguration
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 04.12.2022
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * The constant WS_ENDPOINT_PREFIX.
     */
    public static final String WS_ENDPOINT_PREFIX = "/app";
    /**
     * The constant WS_TOPIC_DESTINATION_PREFIX.
     */
    public static final String WS_TOPIC_DESTINATION_PREFIX = "/topic";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(WS_TOPIC_DESTINATION_PREFIX);
        config.setApplicationDestinationPrefixes(WS_ENDPOINT_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(SAMPLE_ENDPOINT_MESSAGE_MAPPING)
                .setAllowedOrigins("*")
                .withSockJS();

        registry.addEndpoint(SAMPLE_ENDPOINT_WITHOUT_RESPONSE_MESSAGE_MAPPING)
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
