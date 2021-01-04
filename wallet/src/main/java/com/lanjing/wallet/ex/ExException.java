package com.lanjing.wallet.ex;

import lombok.Data;

@Data
public class ExException extends RuntimeException {
    private int code;

    public ExException(String message, int code) {
        super(message);
        this.code = code;
    }
}
