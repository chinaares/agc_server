package com.lanjing.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.jester.util.utils.Result;
import com.lanjing.zuul.utils.Des3Util;
import com.lanjing.zuul.utils.GetInformation;
import com.lanjing.zuul.utils.JwtUtils;
import com.lanjing.zuul.utils.RedisDao;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class prefilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(prefilter.class);
    @Autowired
    RedisDao redisDao;

    @Autowired
    private GetInformation getInformation;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext() ; // 获取当前请求的上下文
        HttpServletRequest request = currentContext.getRequest();
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        logger.info(ip+"-------------"+uri);

        /*boolean result = preHandle(currentContext);

        if (!result){
            return null;
        }*/

        if(!uri.startsWith("/wellet")){
            //后台管理未验证token

            String encodestr = Des3Util.encode("{\"code\": 600,\"result\":\"Login invalid!\"}");

            String url = uri.substring(1).substring(uri.substring(1).indexOf("/"));
            if(url.startsWith("/admin/shop/findPassword")){
                return null;
            }
            String token =request.getHeader("token");
            if("".equals(token) || token == null){
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(600);
                currentContext.setResponseBody("{\"data\":\""+encodestr+"\"}");
                return null;
            }else{
                try{
                    JSONObject user = JwtUtils.decode(token,JSONObject.class);
                    if(user == null){
                        currentContext.setSendZuulResponse(false);
                        currentContext.setResponseStatusCode(600);
                        currentContext.setResponseBody("{\"data\":\""+encodestr+"\"}");
                    }else if(url.startsWith("/admin")){
                        return null;
                    }else{
                        JSONObject users = getInformation.getUser(user.getString("keyes"));
                        String device = users.getString("device");
                        if("".equals(user.getString("device")) || !user.getString("device").equals(device)){
                            currentContext.setSendZuulResponse(false);
                            currentContext.setResponseStatusCode(600);
                            currentContext.setResponseBody("{\"data\":\""+encodestr+"\"}");
                        }else if(users.getInteger("state").intValue() == 0){
                            currentContext.setSendZuulResponse(false);
                            currentContext.setResponseStatusCode(600);
                            currentContext.setResponseBody("{\"data\":\""+encodestr+"\"}");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    currentContext.setSendZuulResponse(false);
                    currentContext.setResponseStatusCode(500);
                    currentContext.setResponseBody("{\"data\":\""+encodestr+"\"}");
                    //currentContext.setResponseBody("{\"code\": 500,\"result\":\"error!\"}");
                }
            }
        }
        logger.info("-------------request to wellet");
        return null;
    }

    public boolean preHandle(RequestContext currentContext) {
        HttpServletRequest request = currentContext.getRequest();

        //获取RequestHeader里的userToken
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return true;
        } else {
            return beginControlErupt(currentContext,token, request.getRemoteAddr(), request.getRequestURI());
        }
    }

    public boolean beginControlErupt(RequestContext currentContext,String token, String ip, String uri) {
        String key = String.format("user:request:%s:%s%s", token, ip, uri);

        Boolean hasKey = redisDao.hasKey(key);
        if (hasKey) {
            redisDao.increment(key);
            //次数
            Integer count = Integer.parseInt(redisDao.getValue(key));
            if (count > 6) {
                currentContext.getResponse().setCharacterEncoding("utf-8");
                String result = JSONObject.toJSONString(Result.error(20004, "异常访问，正在反向定位访问者！"));
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(500);
                currentContext.setResponseBody(result);
                logger.info(String.format("%s:%s",key,result));
                return false;
            }
            return true;
        } else {
            redisDao.increment(key);
            //秒数
            redisDao.setTime(key,18);
            return true;
        }
    }
}