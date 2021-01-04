package com.lanjing.wallet.ex;

import com.alibaba.fastjson.JSONObject;
import com.lanjing.wallet.model.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ExException.class)
    @ResponseBody
    public String bizExceptionHandler(HttpServletRequest req, ExException e) {
        logger.error("发生业务异常！原因是：{}", e.getMessage());
        return JSONObject.toJSONString(new ResultDTO(e.getCode(), e.getMessage()));
    }

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("未知异常！原因是:", e);
        return JSONObject.toJSONString(new ResultDTO(-1, "error"));
    }
}