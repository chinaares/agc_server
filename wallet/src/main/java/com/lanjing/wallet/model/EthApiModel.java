package com.lanjing.wallet.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EthApiModel {
    private BigDecimal fast = BigDecimal.ZERO;
    private BigDecimal fastest = BigDecimal.ZERO;
    private BigDecimal safeLow = BigDecimal.ZERO;
}
