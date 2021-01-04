package com.lanjing.wallet.model.po;

import lombok.Data;

/**
 * @author Jester
 * @version version 1.0.0
 * @email jiansite@qq.com
 * @date 2019-07-19 19:21
 */
@Data
public class Verified {
    //id
    private Integer id;
    //用户名称
    private String name;
    //手机号码
    private String phone;
    //是否实名   0，未实名 1，实名审核中 2，实名成功 -1，实名未通过
    private Integer status;
    //真实姓名
    private String realName;
    //身份证号
    private String identityId;
    //身份证号人像页
    private String identityImg1;
    //身份证号国徽页
    private String identityImg2;
}
