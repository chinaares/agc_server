package com.lanjing.wallet.model.po;

import com.lanjing.wallet.model.Invest;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class IncomeInterest extends Invest {
    private Double multiple;
}