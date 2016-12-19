/*
 * TimeServiceImpl.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-28 20:39:02
 */
package com.nazca.inside.guide.server.rpcimpl;

import com.nazca.io.httprpc.HttpRPCException;
import java.util.Calendar;
import com.nazca.inside.guide.common.rpc.TimeService;

/**
 *
 * @author Zhang Chun Nan
 */
public class TimeServiceImpl implements TimeService {
    @Override
    public Calendar getCurrentTime() throws HttpRPCException {
        return Calendar.getInstance();
    }
}
