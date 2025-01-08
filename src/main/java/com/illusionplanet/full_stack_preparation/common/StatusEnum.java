package com.illusionplanet.full_stack_preparation.common;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum StatusEnum {
    SUCCESS(true, 200, "Success"),

    UNKNOWN_ERROR(false, 500, "Unknown error"),

    USER_NOT_FOUND(false, 404, "No such user"),
    WRONG_PASSWORD(false, 401, "Wrong Password"),
    USER_EXISTS(false, 409, "User exists");

    private Boolean success;
    private Integer code;
    private String message;

    StatusEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
