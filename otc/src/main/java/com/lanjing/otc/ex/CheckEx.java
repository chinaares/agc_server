package com.lanjing.otc.ex;

import java.util.Objects;

public class CheckEx {

    public static void isNull(Object obj, String message) {
        if (Objects.isNull(obj)) {
            throw new ExException(message);
        }
    }

    public static void isNull(Object obj) {
        if (Objects.isNull(obj)) {
            throw new ExException("error");
        }
    }

    public static void nonNull(Object obj, String message) {
        if (Objects.nonNull(obj)) {
            throw new ExException(message);
        }
    }

    public static void nonNull(Object obj) {
        if (Objects.nonNull(obj)) {
            throw new ExException("error");
        }
    }

    public static void check(boolean flag, String message) {
        if (flag) {
            throw new ExException(message);
        }
    }

    public static void error(boolean flag) {
        if (flag) {
            throw new ExException("error");
        }
    }

    public static void error(String message) {
        throw new ExException(message);
    }

    public static void db(int flag, String message) {
        if (flag < 1) {
            throw new ExException(message);
        }
    }

    public static void db(int flag) {
        if (flag < 1) {
            throw new ExException("error");
        }
    }
}
