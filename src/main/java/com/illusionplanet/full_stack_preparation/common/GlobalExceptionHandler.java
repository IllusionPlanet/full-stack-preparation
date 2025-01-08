package com.illusionplanet.full_stack_preparation.common;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return Result.setResult(StatusEnum.UNKNOWN_ERROR).customMessage(e.getMessage());
    }
}
