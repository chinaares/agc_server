package com.lanjing.wallet.ex;

public class CheckEx {
    public static void check(boolean flag, String message, int code) {
        if (flag) {
            throw new ExException(message, code);
        }
    }

    public static void ex(String message) {
        throw new ExException(message, 300);
    }

    public static void ex() {
        throw new ExException("error", 300);
    }

    public static void error(boolean flag) {
        if (flag) {
            throw new ExException("error", 300);
        }
    }

    public static void error(boolean flag, String message) {
        if (flag) {
            throw new ExException(message, 300);
        }
    }

    public static void db(int flag) {
        if (flag < 1) {
            throw new ExException("error", 300);
        }
    }

    public static void db(int flag, String message) {
        if (flag < 1) {
            throw new ExException(message, 300);
        }
    }
}
