package com.lanjing.otc.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {
    private Integer id;

    private String title;

    private Double price;

    private String thumbnail;
}