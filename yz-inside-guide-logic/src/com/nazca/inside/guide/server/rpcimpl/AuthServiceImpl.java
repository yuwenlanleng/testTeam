/*
 * Copyright(c) 2007-2011 by Yingzhi Tech All Rights Reserved
 *
 * Created at 2011-06-11 12:03:40
 */
package com.nazca.inside.guide.server.rpcimpl;

import com.nazca.io.httpdao.HttpSessionTokenManager;
import com.nazca.io.httpdao.Token;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.io.httprpc.HttpRPCInjection;
import com.nazca.io.httprpc.HttpRPCTokenVerifier;
import com.nazca.usm.client.connector.USMSRPCService;
import com.nazca.usm.common.SessionConst;
import com.nazca.inside.guide.common.consts.ProjectConst;
import java.net.MalformedURLException;
import java.net.URL;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.nazca.inside.guide.common.consts.InsideGuideErrorCode;
import com.nazca.inside.guide.common.rpc.AuthService;

/**
 *
 * @author fred
 */
public class AuthServiceImpl implements AuthService {
    private static Log log = LogFactory.getLog(AuthServiceImpl.class);
    @HttpRPCInjection
    private HttpSession session;

    @Override
    public void auth(String userId, String usmTokenId) throws HttpRPCException {
        try {
            HttpRPCTokenVerifier verifier = HttpRPC.getService(HttpRPCTokenVerifier.class, 
                    new URL(USMSRPCService.getInstance(ProjectConst.YZ_INSIDE_GUIDE_MODULE_ID).getConfig().getUsmsServerAddr()));
            Token usmToken = verifier.verifyToken(usmTokenId);
            if (usmToken == null) {
                throw new HttpRPCException("auth failed", InsideGuideErrorCode.LACK_OF_AUTH);
            } else {
                if (!userId.equals(usmToken.get(SessionConst.KEY_USER_ID))) {
                    throw new HttpRPCException("auth failed, diff user",
                            InsideGuideErrorCode.LACK_OF_AUTH);
                }
                Token compToken = new Token();
                compToken.put(SessionConst.KEY_USER_ID, usmToken.get(
                        SessionConst.KEY_USER_ID));
                compToken.put(SessionConst.KEY_USER_LOGINNAME,
                        usmToken.get(SessionConst.KEY_USER_LOGINNAME));
                compToken.put(SessionConst.KEY_USER_NAME, usmToken.get(
                        SessionConst.KEY_USER_NAME));
                session.setAttribute(SessionConst.KEY_USER_ID,
                        usmToken.get(SessionConst.KEY_USER_ID));
                session.setAttribute(SessionConst.KEY_USER_LOGINNAME,
                        usmToken.get(SessionConst.KEY_USER_LOGINNAME));
                session.setAttribute(SessionConst.KEY_USER_NAME,
                        usmToken.get(SessionConst.KEY_USER_NAME));
                HttpSessionTokenManager.getManager().giveToken(session, compToken);
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void logout() throws HttpRPCException {
        HttpSessionTokenManager.getManager().confiscateToken(session);
        session.invalidate();
    }
}
