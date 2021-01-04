package com.lanjing.goods.model.po;

import lombok.Data;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-08-23 17:44
 */
@Data
public class SalesVolume extends Cargo {
    //销量
    private Integer salesVolume;
    //单位
    private String unit;
}
