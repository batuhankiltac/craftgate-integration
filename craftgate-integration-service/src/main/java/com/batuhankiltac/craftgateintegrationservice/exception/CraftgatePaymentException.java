package com.batuhankiltac.craftgateintegrationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CraftgatePaymentException extends RuntimeException {
    private String message;
    private String conversationId;
    private HttpStatus httpStatus;
}
