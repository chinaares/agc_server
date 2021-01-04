package com.lanjing.goods.model.po;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jester.util.utils.DoubleUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品简略信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cargo {
    /**
     * 商品id
     */
    private Integer id;

    /**
     * 标题、名称
     */
    private String title;

    /**
     * 价格
     */
    @JsonSerialize(using = DoubleUtil.class)
    private Double price;

    /**
     * 商品缩略图
     */
    private String thumbnail;
}