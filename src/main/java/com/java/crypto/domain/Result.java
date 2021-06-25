package com.java.crypto.domain;

import lombok.Data;

@Data
public class Result {
    private String result;

    public Result(){}

    public Result(String result) {
        this.result = result;
    }
}
