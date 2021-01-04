package com.lanjing.wallet.util;

import com.google.gson.Gson;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class QuotationUtil {

    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36";


    public static Map sendSMS(String account, String code) {
        String result = null;
        String url = "https://api.huobipro.com/market/tickers";
        Map resultMap = new HashMap();
        try {
            result = net(url, null, "GET");
            JSONObject object = JSONObject.fromObject(result);
            ResultVO resultVO = new Gson().fromJson(object.toString(),ResultVO.class);
            if(resultVO.getStatus().equals("ok")){

                for(Quotation quotation : resultVO.getData()){
                    if(quotation.getSymbol().equals("btcusdt")){
                        System.out.println("btcusdt收盘价" + quotation.getClose());
                        System.out.println("btcusdt24小时成交量" + quotation.getAmount());
                        System.out.println("btcusdt开盘价" + quotation.getOpen());

                        Map map = new HashMap();
                        map.put("price",quotation.getClose());
                        map.put("amount",quotation.getAmount());
                        resultMap.put("btcusdt",map);

                    }
                    if(quotation.getSymbol().equals("ethusdt")){
                        System.out.println("ethusdt收盘价" + quotation.getClose());
                        System.out.println("ethusdt24小时成交量" + quotation.getAmount());
                        System.out.println("ethusdt开盘价" + quotation.getOpen());

                        Map map = new HashMap();
                        map.put("price",quotation.getClose());
                        map.put("amount",quotation.getAmount());
                        resultMap.put("ethusdt",map);
                    }
                    if(quotation.getSymbol().equals("eosusdt")){
                        System.out.println("eosusdt收盘价" + quotation.getClose());
                        System.out.println("eosusdt24小时成交量" + quotation.getAmount());
                        System.out.println("eosusdt开盘价" + quotation.getOpen());

                        Map map = new HashMap();
                        map.put("price",quotation.getClose());
                        map.put("amount",quotation.getAmount());
                        resultMap.put("eosusdt",map);
                    }
                }
            }
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static String net(String strUrl, Map params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || "GET".equals(method)) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || "GET".equals(method)) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params != null && "POST".equals(method)) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    public static String urlencode(Map<String, Object> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }



}
