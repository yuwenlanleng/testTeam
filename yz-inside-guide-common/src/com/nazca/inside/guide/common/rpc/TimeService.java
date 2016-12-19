/*
 * TimeService.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-20 19:15:25
 */
package com.nazca.inside.guide.common.rpc;

import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.io.httprpc.InvokingMethod;
import com.nazca.io.httprpc.ServerInvoking;
import java.util.Calendar;

/**
 * 时间同步服务
 * @author chenjianan
 */
@ServerInvoking(method = InvokingMethod.SERVICE_MAPPING, 
        identifier = "com.nazca.inside.guide.server.rpcimpl.TimeServiceImpl")
public interface TimeService {
    public Calendar getCurrentTime() throws HttpRPCException;
}
