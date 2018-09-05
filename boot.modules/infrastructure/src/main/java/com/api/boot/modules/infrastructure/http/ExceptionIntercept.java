package com.api.boot.modules.infrastructure.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionIntercept {

    private MessageSource messageSource;

    @Autowired
    public ExceptionIntercept(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler (BindException.class)
    public HttpModel validation(BindException bindException) {
        bindException.printStackTrace();
        List<String> list = bindException
            .getBindingResult()
            .getFieldErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .map(a -> messageSource.getMessage(a, null, Locale.SIMPLIFIED_CHINESE))
            .collect(Collectors.toList());
        return HttpModel.instance()
                .setResponses(list)
                .setMessages("Bad Request")
                .setStatus(HttpServletResponse.SC_BAD_REQUEST)
                .build();
    }



    @ExceptionHandler (Exception.class)
    public HttpModel system(Exception exception) {
        exception.printStackTrace();
        return HttpModel.instance()
                .setMessages("sys.exception")
                .setResponses("exception !!")
                .setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
                .build();
    }

}
