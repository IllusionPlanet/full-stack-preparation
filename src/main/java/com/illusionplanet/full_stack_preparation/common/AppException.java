package com.illusionplanet.full_stack_preparation.common;

import lombok.Data;

@Data
public class AppException extends RuntimeException {
    private Integer code;

    public AppException(StatusEnum statusEnum) {
        super(statusEnum.getMessage());
        this.code = statusEnum.getCode();
    }

    @Override
    public String toString() {
        return "AppException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
