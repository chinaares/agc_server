package com.lanjing.wallet.filter;


import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.model.Users;
import com.lanjing.wallet.service.UsersService;
import com.lanjing.wallet.util.Des3Util;
import com.lanjing.wallet.util.JwtUtils;
import com.lanjing.wallet.util.RedisDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginFilter", urlPatterns = {"/*"})
public class loginFilter implements Filter {

    private Log logging = LogFactory.getLog(loginFilter.class);

    @Resource(name = "UsersService")
    private UsersService usersService;

    @Autowired
    RedisDao redisDao;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /*static {
        System.out.println("..................................chushihua");
        List<String> list = new ArrayList<>();
        list.add("/app/login");
    }*/

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logging.info("filter");
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
//        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");
//        //解决跨域访问报错
//        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        //response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0.
        response.setHeader("Expires", "0");
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        System.out.println("请求ip：" + ip + ".................................." + url);
        if (url.startsWith("/input/") || url.startsWith("/sys/") || url.startsWith("/version/findVersion") || url.startsWith("/app/sendcode") || url.startsWith("/app/createCoinAddress")) {
            filterChain.doFilter(servletRequest, servletResponse);
            /*if(ip.equals("127.0.0.1")){
            }*/
        } else if (url.startsWith("/agc/list") || url.startsWith("/get")
                || url.startsWith("/open/app") || url.startsWith("/app/getmarket") || url.startsWith("/app/getExemption")
                || url.startsWith("/app/getAgreement") || url.startsWith("/app/importwellet") || url.startsWith("/app/uploaduserpicture")
                || url.startsWith("/app/login") || url.startsWith("/app/register") || url.startsWith("/app/sethelp") || url.startsWith("/app/isfindpassword")
                || url.startsWith("/app/findpassword")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (url.startsWith("/zuul")) {
            if (ip.equals("127.0.0.1")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else if (url.startsWith("/admin/shop/login") || url.startsWith("/admin/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (url.startsWith("/admin")) {
            String token = request.getHeader("token");
            if ("".equals(token) || token == null) {
                response.setCharacterEncoding("UTF-8");
                JSONObject json = new JSONObject();
                json.put("msg", "登录过期，请重新登录");
                json.put("code", 600);
                String encodestr = Des3Util.encode(json.toJSONString());
                json.clear();
                json.put("data", encodestr);
                response.getWriter().print(json.toJSONString());
            } else {
                JSONObject json = new JSONObject();
                try {
                    JSONObject user = JwtUtils.decode(token, JSONObject.class);
                    response.setCharacterEncoding("UTF-8");
                    if (user == null) {
                        json.put("msg", "登录过期，请重新登录");
                        json.put("code", 600);
                        String encodestr = Des3Util.encode(json.toJSONString());
                        json.clear();
                        json.put("data", encodestr);
                        response.getWriter().print(json.toJSONString());
                    } else {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    json.put("msg", "error");
                    json.put("code", 500);
                    String encodestr = Des3Util.encode(json.toJSONString());
                    json.clear();
                    json.put("data", encodestr);
                    response.getWriter().print(json.toJSONString());
                }
            }
        } else {
            String token = request.getHeader("token");
            if ("".equals(token) || token == null) {
                response.setCharacterEncoding("UTF-8");
                JSONObject json = new JSONObject();
                json.put("msg", "登录过期，请重新登录");
                json.put("code", 600);
                String encodestr = Des3Util.encode(json.toJSONString());
                json.clear();
                json.put("data", encodestr);
                response.getWriter().print(json.toJSONString());
            } else {
                JSONObject json = new JSONObject();
                try {
                    JSONObject user = JwtUtils.decode(token, JSONObject.class);
                    response.setCharacterEncoding("UTF-8");
                    if (user == null) {
                        json.put("msg", "登录过期，请重新登录");
                        json.put("code", 600);
                        String encodestr = Des3Util.encode(json.toJSONString());
                        json.clear();
                        json.put("data", encodestr);
                        response.getWriter().print(json.toJSONString());
                    } else {
                        Users users = usersService.selectByUserKey(user.getString("keyes"));
                        String device = users.getDevice();
                        if ("".equals(user.getString("device")) || !user.getString("device").equals(device)) {
                            response.setCharacterEncoding("UTF-8");
                            json.put("msg", "登录过期，请重新登录");
                            json.put("code", 600);
                            String encodestr = Des3Util.encode(json.toJSONString());
                            json.clear();
                            json.put("data", encodestr);
                            response.getWriter().print(json.toJSONString());
                        } else if (users.getState().intValue() == 0) {
                            response.setCharacterEncoding("UTF-8");
                            json.put("msg", "该用户已被冻结");
                            json.put("code", 600);
                            String encodestr = Des3Util.encode(json.toJSONString());
                            json.clear();
                            json.put("data", encodestr);
                            response.getWriter().print(json.toJSONString());
                        } else if (users.getState().intValue() == -1) {
                            response.setCharacterEncoding("UTF-8");
                            json.put("msg", "账号不存在");
                            json.put("code", 600);
                            String encodestr = Des3Util.encode(json.toJSONString());
                            json.clear();
                            json.put("data", encodestr);
                            response.getWriter().print(json.toJSONString());
                        } else {
                            filterChain.doFilter(servletRequest, servletResponse);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    json.put("msg", "error");
                    json.put("code", 500);
                    String encodestr = Des3Util.encode(json.toJSONString());
                    json.clear();
                    json.put("data", encodestr);
                    response.getWriter().print(json.toJSONString());
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
