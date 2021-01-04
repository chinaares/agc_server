package com.lanjing.wallet.agc.vo;

import com.lanjing.wallet.util.ServletUtils;
import com.lanjing.wallet.util.StringUtils;

/**
 * 表格数据处理
 * 
 * @author live
 */
public class TableSupport
{
    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain()
    {
        PageDomain pageDomain = new PageDomain();
        if (StringUtils.isNull(ServletUtils.getParameterToInt(PAGE_NUM))||ServletUtils.getParameterToInt(PAGE_NUM).intValue()==0) {
        	pageDomain.setPageNum(1);
		}else {
			pageDomain.setPageNum(ServletUtils.getParameterToInt(PAGE_NUM));
		}
        if (StringUtils.isNull(ServletUtils.getParameterToInt(PAGE_SIZE))||ServletUtils.getParameterToInt(PAGE_SIZE).intValue()==0) {
        	pageDomain.setPageSize(20);
		}else {
			pageDomain.setPageSize(ServletUtils.getParameterToInt(PAGE_SIZE));
		}
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest()
    {
        return getPageDomain();
    }
}
