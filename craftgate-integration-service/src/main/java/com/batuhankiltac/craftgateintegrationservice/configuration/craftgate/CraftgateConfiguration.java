package com.batuhankiltac.craftgateintegrationservice.configuration.craftgate;

import io.craftgate.request.common.RequestOptions;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CraftgateConfiguration {

    public static RequestOptions getOptions() {
        return RequestOptions.builder()
                .apiKey("your-api-key")
                .secretKey("your-secret-key")
                .baseUrl("https://sandbox-api.craftgate.io")
                .build();
    }
}
