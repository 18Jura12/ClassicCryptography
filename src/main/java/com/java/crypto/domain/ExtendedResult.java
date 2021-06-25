package com.java.crypto.domain;

import lombok.Data;

@Data
public class ExtendedResult extends Result {
    private char[][] matrix;

    public ExtendedResult(String result, char[][] matrix) {
        super(result);
        this.matrix = matrix;
    }
}
