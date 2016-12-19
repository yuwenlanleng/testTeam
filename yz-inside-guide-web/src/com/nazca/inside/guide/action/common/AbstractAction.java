/*
 * AbstractAction.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-05-17 13:42:37
 */
package com.nazca.inside.guide.action.common;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 * action抽象，提供获取request
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class AbstractAction extends ActionSupport implements ServletRequestAware {

    protected HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }
}
