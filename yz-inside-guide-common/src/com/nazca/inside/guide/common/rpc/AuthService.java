/*
 * AuthService.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-20 19:23:44
 */
package com.nazca.inside.guide.common.rpc;

import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.io.httprpc.InvokingMethod;
import com.nazca.io.httprpc.ServerInvoking;

/**
 *
 * @author Zhang Chun Nan
 */
@ServerInvoking(method = InvokingMethod.SERVICE_MAPPING,
identifier = "com.nazca.inside.guide.server.rpcimpl.AuthServiceImpl")
public interface AuthService {
    void auth(String userId, String token) throws HttpRPCException;
    
    void logout() throws HttpRPCException;
}
