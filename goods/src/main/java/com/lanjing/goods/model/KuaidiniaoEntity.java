package com.lanjing.goods.model;

import lombok.Data;

import java.util.List;

@Data
public class KuaidiniaoEntity {
    private String LogisticCode;
    private String ShipperCode;
    private String State;
    private String EBusinessID;
    private boolean Success;
    private List<TracesBean> Traces;

    public static class TracesBean {
        /**
         * AcceptStation : 【广东省揭阳市普宁东公司】 已收件 取件人: 洪佳婵 (19842114248)
         * AcceptTime : 2020-08-21 00:35:11
         */

        private String AcceptStation;
        private String AcceptTime;

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }
    }
}
