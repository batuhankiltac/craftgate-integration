package com.batuhankiltac.craftgateintegrationservice.exception;

import com.batuhankiltac.craftgateintegrationservice.model.error.ExceptionDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return ExceptionDetail.builder()
                .errors(methodArgumentNotValidException.getFieldErrors()
                                .stream()
                                .map(i -> messageSource.getMessage(Objects.requireNonNull(i.getDefaultMessage()),
                                                                   i.getArguments(),
                                                                   i.getDefaultMessage(),
                                                                   new Locale("TR")))
                                .collect(Collectors.toList()))
                .build();
    }
}
