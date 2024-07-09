package com.bank.jandhan.lms.config;

import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.PreferHeapByteBufAllocator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebclientConfig {

    @Value("${BASE_URL}")
    String BASE_URL;
    @Bean
    public WebClient webClient() {
//       ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
//                        .codecs(codecs-> codecs.defaultCodecs().maxInMemorySize(1024 * 1024 ))
//                        .build();

        return WebClient.builder()
                .baseUrl(BASE_URL)
                .build();
    }

}
