package com.lanjing.wallet.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {
    private Integer fid;

    private String name;

    private String nick;

    private Integer purview;
}