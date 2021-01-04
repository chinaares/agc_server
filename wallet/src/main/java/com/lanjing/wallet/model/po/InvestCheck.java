package com.lanjing.wallet.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class InvestCheck {
    private Integer id;

    private String phone;

    private String title;

    private Integer periodsNum;

    private Integer status;

    private Double principal;

    private Double income;

    private Double total;

    private Double dailyRelease;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}