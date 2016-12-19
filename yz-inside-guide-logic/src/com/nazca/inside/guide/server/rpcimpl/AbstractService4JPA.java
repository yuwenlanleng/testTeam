/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-03-26 16:48:33
 */
package com.nazca.inside.guide.server.rpcimpl;

import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.usm.common.SessionConst;
import com.nazca.usm.model.USMSUser;
import com.nazca.util.IPAddressUtil;
import com.nazca.inside.guide.common.enums.ModifyLogOperateType;
import com.nazca.inside.guide.common.jpa.ModifyLog;
import com.nazca.inside.guide.server.util.USMSProxyTool;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import com.nazca.inside.guide.common.consts.InsideGuideErrorCode;
import java.sql.Connection;

/**
 *
 * @author fred
 */
public class AbstractService4JPA {
    protected HttpRPCException dealException(Connection conn, Throwable ex) {
        ex.printStackTrace();
        if (ex instanceof HttpRPCException) {
            try {
                if (!conn.getAutoCommit()) {
                    conn.rollback();
                }
                return (HttpRPCException) ex;
            } catch (Throwable th) {
                th.printStackTrace();
                return new HttpRPCException("数据库操作失败",
                        InsideGuideErrorCode.DB_ROLLBACK_ERROR, ex);
            }
        } else {
            try {
                if (!conn.getAutoCommit()) {
                    conn.rollback();
                    return new HttpRPCException("数据库操作失败",
                            InsideGuideErrorCode.DB_TRANSACTION_ERROR, ex);
                } else {
                    return new HttpRPCException("数据库操作失败",
                            InsideGuideErrorCode.DB_ERROR, ex);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return new HttpRPCException("数据库操作失败",
                        InsideGuideErrorCode.DB_ROLLBACK_ERROR, ex);
            }
        }
    }

    private ModifyLog buildLog(String moduleId, String subModuleId,
            String ip, String operator, String desc, ModifyLogOperateType logType) {
        ModifyLog log = new ModifyLog();
        log.setId(UUID.randomUUID().toString());
        log.setModuleId(moduleId);
        log.setSubModuleId(subModuleId);
        log.setIp(ip);
        log.setOperateTime(new Date());
        log.setDescription(desc);
        log.setOperator(new USMSUser(operator));
        log.setOperateType(logType);
        return log;
    }
    
    /**
     * 获取当前登录用户ID
     *
     * @param request
     * @return
     */
    protected String getCurrentUserID(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(SessionConst.KEY_USER_ID);
    }

    /**
     * 获取当前登录的USMS用户
     *
     * @param request
     * @return
     */
    // TODO
    protected USMSUser getCurrentUSMSUser(HttpServletRequest request) {
        return getUSMSUserById((String) request.getSession().getAttribute(SessionConst.KEY_USER_ID));
    }

    /**
     * 获取当前登录用户的IP
     *
     * @param request
     * @return
     */
    protected String getCurrentIp(HttpServletRequest request) {
        return IPAddressUtil.getRemoteIP(request);
    }

    /**
     * 根据ID获取USMS用户
     *
     * @param usmsUUID
     * @return
     */
    protected USMSUser getUSMSUserById(String usmsUUID) {
        return usmsUUID != null ? USMSProxyTool.getUserById(usmsUUID) : null;
    }
}