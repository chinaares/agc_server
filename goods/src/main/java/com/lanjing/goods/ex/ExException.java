package com.lanjing.goods.ex;

import lombok.Data;

@Data
public class ExException extends RuntimeException {

    public ExException(String message) {
        super(message);
    }
}
