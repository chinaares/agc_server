package com.lanjing.wallet.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpUtil {

    private static final Logger log = LogManager.getLogger(HttpUtil.class);

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";

    // 超时设置
    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(50000)
            .setConnectionRequestTimeout(50000)
            .setSocketTimeout(50000)
            .build();

    // 编码设置
    private static final ConnectionConfig connectionConfig = ConnectionConfig.custom()
            .setMalformedInputAction(CodingErrorAction.IGNORE)
            .setUnmappableInputAction(CodingErrorAction.IGNORE)
            .setCharset(Consts.UTF_8)
            .build();

    private static HttpClientBuilder getBuilder() {
        List<Header> headers = new ArrayList<>();
        Header header = new BasicHeader("User-Agent", USER_AGENT);
        headers.add(header);
        return HttpClients.custom().setDefaultConnectionConfig(connectionConfig).setDefaultHeaders(headers).setDefaultRequestConfig(requestConfig);
    }

    /**
     * 发送HttpGet请求
     *
     * @param url 请求地址
     * @return
     */
    public static String sendGet(String url) throws IOException {
        String result;
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpClient httpclient = getBuilder().build();
             CloseableHttpResponse response = httpclient.execute(httpGet)) {
            HttpEntity httpEntity = response.getEntity();
            result = EntityUtils.toString(httpEntity);
        }
        return result;
    }


    /**
     * 发送HttpPost请求，参数为json字符串
     *
     * @param url     请求地址
     * @param jsonStr json字符串
     * @return
     */
    public static String sendPost(String url, String jsonStr) throws IOException {
        String result;

        // 设置entity
        StringEntity stringEntity = new StringEntity(jsonStr, Consts.UTF_8);
        stringEntity.setContentType("application/json");

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(stringEntity);

        try (CloseableHttpClient httpclient = getBuilder().build(); CloseableHttpResponse httpResponse = httpclient.execute(httpPost);) {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        }
        return result;
    }

    /**
     * 发送HttpPost请求，提交表单，支持文件上传
     *
     * @param url    请求地址
     * @param params 表单参数
     * @param files  上传文件
     * @return
     */
    public static String sendPost(String url, Map<String, Object> params, List<MultipartFile> files) throws IOException {
        String result;
        // 设置entity
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName("UTF-8"));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        if (CollectionUtils.isNotEmpty(files)) { // 文件表单参数
            for (MultipartFile file : files) {
                Path path = Paths.get(file.getOriginalFilename());
                String contentType = Files.probeContentType(path);
                if (StringUtils.isEmpty(contentType)) {
                    contentType = "application/octet-stream";
                }
                builder.addBinaryBody(file.getName(), file.getInputStream(), ContentType.create(contentType), file.getOriginalFilename());
            }
        }
        if (MapUtils.isNotEmpty(params)) { // 普通表单参数
            params.forEach((k, v) -> {
                StringBody stringBody = new StringBody(v.toString(), ContentType.create("text/plain", "UTF-8"));
                builder.addPart(k, stringBody);
            });
        }

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(builder.build());

        try (CloseableHttpClient httpclient = getBuilder().build();
             CloseableHttpResponse httpResponse = httpclient.execute(httpPost)) {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        }
        return result;
    }


    public static String doPost(String url, Map<String, String> param) {

        // 创建Httpclient对象

        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        String resultString = "";

        try {

            // 创建Http Post请求

            HttpPost httpPost = new HttpPost(url);

            // 创建参数列表

            if (param != null) {

                List<NameValuePair> paramList = new ArrayList<>();

                for (String key : param.keySet()) {

                    paramList.add(new BasicNameValuePair(key, param.get(key)));

                }

                // 模拟表单

                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");

                httpPost.setEntity(entity);

            }

            // 执行http请求

            response = httpClient.execute(httpPost);

            resultString = EntityUtils.toString(response.getEntity(), "utf-8");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                response.close();

            } catch (IOException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

            }

        }



        return resultString;

    }


    // MultipartFile里面的name就是表单的name，文件名是originalFilename
    private static MultipartFile fileToMultipartFile(File file, String name) throws IOException {
        log.info("File转MultipartFile：文件路径：{}", file.getAbsolutePath());
        FileInputStream inputStream = new FileInputStream(file);
        Path path = Paths.get(file.getAbsolutePath());
        String contentType = Files.probeContentType(path);
        if (StringUtils.isEmpty(contentType)) {
            contentType = "application/octet-stream";
        }
        MultipartFile multipartFile = new MockMultipartFile(
                name,
                file.getName(),
                contentType,
                inputStream);
        log.info("File转MultipartFile：转换后的文件信息：[name:{}, originalFilename:{} ,contentType:{}]",
                multipartFile.getName(),
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType());
        return multipartFile;
    }

    public static void main(String[] args) {
        Map<String,Object> json = new HashMap();
        json.put("account","CN6176501");
        json.put("password","=7~H8^lPK_am");
        JSONObject json1 = new JSONObject();
        json1.put("account","CN6176501");
        json1.put("password","=7~H8^lPK_am");
        try {
            System.out.println(sendPost("http://smssh1.253.com/msg/balance/json",json1.toJSONString()));
        }catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            System.out.println(doPost("http://smssh1.253.com/msg/balance/json",json1.toJSONString(),"UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }*/
        //System.out.println(post("http://smssh1.253.com/msg/balance/json",json));

    }

    /**
     * 发送HttpPost请求，参数为text/plain
     * data 用拼接的方式发生
     * @param url     请求地址
     * @param
     * @return
     */
    public static String sendPostTextPlain(String url) throws IOException {
        String result;

        // 设置entity
        StringEntity stringEntity = new StringEntity("", Consts.UTF_8);
        stringEntity.setContentType("text/plains");

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(stringEntity);

        try (CloseableHttpClient httpclient = getBuilder().build(); CloseableHttpResponse httpResponse = httpclient.execute(httpPost);) {
            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        }
        return result;
    }
}
