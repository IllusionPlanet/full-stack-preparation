package com.illusionplanet.full_stack_preparation.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(StatusEnum.SUCCESS.getSuccess());
        result.setCode(StatusEnum.SUCCESS.getCode());
        result.setMessage(StatusEnum.SUCCESS.getMessage());
        return result;
    }

    public static Result setResult(StatusEnum statusEnum) {
        Result result = new Result();
        result.setSuccess(statusEnum.getSuccess());
        result.setCode(statusEnum.getCode());
        result.setMessage(statusEnum.getMessage());
        return result;
    }

    public Result customSuccess(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result customCode(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result customMessage(String message) {
        this.setMessage(message);
        return this;
    }

    public Result customData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result customData(Map<String, Object> data) {
        this.setData(data);
        return this;
    }
}
