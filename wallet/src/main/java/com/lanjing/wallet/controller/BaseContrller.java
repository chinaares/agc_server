package com.lanjing.wallet.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanjing.wallet.agc.vo.PageDomain;
import com.lanjing.wallet.agc.vo.TableDataInfo;
import com.lanjing.wallet.agc.vo.TableSupport;
import com.lanjing.wallet.util.JwtUtils;
import com.lanjing.wallet.util.SqlUtil;
import com.lanjing.wallet.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class BaseContrller {
    @Autowired
    private HttpServletRequest request;


    public String getUserKey(){
        JSONObject user = JwtUtils.decode(request.getHeader("token"),JSONObject.class);
        return user.getString("keyes");
    }

    public Integer getUserId(){
        JSONObject user = JwtUtils.decode(request.getHeader("token"),JSONObject.class);
        return user.getInteger("keyes");
    }

    public Integer getId(){
        JSONObject admin = JwtUtils.decode(request.getHeader("token"),JSONObject.class);
        return admin.getInteger("fId");
    }

    public String getDevice(){
        JSONObject user = JwtUtils.decode(request.getHeader("token"),JSONObject.class);
        return user.getString("device");
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
    
    /**
     * 设置请求分页数据
     */
    protected void startPage(Integer pageNum,Integer pageSize)
    {
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            PageHelper.startPage(pageNum, pageSize, null);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list)
    {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(200);
        rspData.setMsg("success");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
}
