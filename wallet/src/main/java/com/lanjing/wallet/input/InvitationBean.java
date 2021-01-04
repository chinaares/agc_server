package com.lanjing.wallet.input;

import lombok.Data;

import java.util.List;

@Data
public class InvitationBean {
    private Long id;
    private Long pid;
    private String name;
    private String title;
    private List<InvitationBean> children;

}
