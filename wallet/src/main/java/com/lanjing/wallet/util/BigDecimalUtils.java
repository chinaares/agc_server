package com.lanjing.wallet.util;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static String getBigDecimalStr(BigDecimal number) {
        if (number == null || number.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        }
        return number.stripTrailingZeros().toPlainString();
    }

    public static String multiplyStr(BigDecimal number1, BigDecimal number2) {
        if (number1 == null || number2 == null || number1.compareTo(BigDecimal.ZERO) == 0 || number2.compareTo(BigDecimal.ZERO) == 0) {
            return "0";
        } else {
            return number1.multiply(number2).stripTrailingZeros().toPlainString();
        }
    }
}
