package com.lanjing.wallet.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AspUtils {

	/**
	 * 获取USDT2CNY的汇率价格
	 */
	public static BigDecimal getUsdt2Cny() {
		BigDecimal CNY = new BigDecimal("7.0");// 默认价7.0

		try {
			CNY = fromhuobi();// 先调用火币接口
		} catch (Exception e) {
			// 失败就去调用币核的接口
			try {
				CNY = fromBhex();
			} catch (Exception e1) {// 还失败就默认默认值7.0
				return CNY;
			}
		}

		return CNY;
	}

	/**
	 * 币核接口 https://www.hbtc.com/api/quote/v1/rates?tokens=USDT&legalCoins=CNY
	 */
	public static BigDecimal fromBhex() throws Exception {
		BigDecimal CNY = new BigDecimal("7.0");// 默认价7.0
		String json = HttpUtils.sendGet("https://www.hbtc.com/api/quote/v1/rates", "tokens=USDT&legalCoins=CNY");
		JSONObject jsonMap = (JSONObject) JSON.parse(json);
		Object code = jsonMap.get("code");
		if (code != null && code.equals(200)) {
			JSONArray data = (JSONArray) jsonMap.get("data");
			JSONObject js2 = (JSONObject) data.get(0);
			if ("USDT".equalsIgnoreCase(js2.getString("token"))==false) {//token不等于USDT
				return CNY;
			}
			JSONObject js3 = (JSONObject) js2.get("rates");
			CNY = new BigDecimal((String)js3.get("CNY"));
		}
		return CNY;
	}
	
	/**
	 * 火币接口
	 * https://otc-api.eiijo.cn/v1/data/config/purchase-price?coinId=2&currencyId=1&matchType=0
	 */
	public static BigDecimal fromhuobi() throws Exception {
		BigDecimal CNY = new BigDecimal("7.0");// 默认价7.0
		String json = HttpUtils.sendGet("https://otc-api.eiijo.cn/v1/data/config/purchase-price", "coinId=2&currencyId=1&matchType=0");
		JSONObject jsonMap = (JSONObject) JSON.parse(json);
		Object code = jsonMap.get("code");
		boolean success = (boolean) jsonMap.get("success");
		if (code != null && code.equals(200) && success) {
			JSONObject data = (JSONObject) jsonMap.get("data");
			CNY = data.getBigDecimal("price");
		}
		return CNY;
	}

}