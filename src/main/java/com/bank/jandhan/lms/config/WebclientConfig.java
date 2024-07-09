package com.bank.jandhan.lms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebclientConfig {

    @Value("${BASE_URL}")
    String BASE_URL;
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(BASE_URL)
                .exchangeStrategies(
                        ExchangeStrategies.builder()
                                .codecs(codecs-> codecs.defaultCodecs().maxInMemorySize(1024 * 1024 ))
                                .build()
                )
                .build();
    }

}
