/*
 * PIMLoginAgent.java
 * 
 * Copyright(c) 2007-2010 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2010-03-14 18:13:40
 */
package com.nazca.inside.guide.client.agent;

import com.nazca.io.httpdao.HttpClientContext;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.usm.client.ConfigManager;
import com.nazca.usm.client.ServiceConfig;
import com.nazca.usm.client.UsmsClientContext;
import com.nazca.usm.client.service.async.agent.ClientClockAgent;
import com.nazca.usm.common.LoginException;
import com.nazca.usm.common.LoginResult;
import com.nazca.usm.model.USMSUser;
import com.nazca.usm.service.rpc.LoginRPCService;
import com.nazca.inside.guide.client.ClientContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.nazca.inside.guide.common.consts.InsideGuideErrorCode;
import com.nazca.inside.guide.common.rpc.AuthService;

/**
 *
 * @author fred
 */
public class LoginAgent extends AbstractAgent<USMSUser> {

    private Log log = LogFactory.getLog(LoginAgent.class);
    private String loginName;
    private String password;

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    protected USMSUser doExecute() throws HttpRPCException {
        log.info(ClientContext.getUSMServerRPCURL());
        try {
            LoginRPCService usmLoginServ = HttpRPC.getService(LoginRPCService.class, ClientContext.getUSMServerRPCURL(),
                    true);
            LoginResult result = usmLoginServ.login(loginName, password);
            USMSUser usmUser = result.getUser();
            if (usmUser.getRoleSet().size() == 0 && usmUser.getPermissionSet().size() == 0) {
                throw new HttpRPCException("您没有任何权限，不能登录", InsideGuideErrorCode.LACK_OF_ROLES);
            }
            HttpClientContext usmContext = HttpRPC.getClientContext(ClientContext.getUSMServerRPCURL());
            AuthService rServ = HttpRPC.getService(AuthService.class,
                    ClientContext.getInsideGuideServerRPCURL(), true);

            rServ.auth(usmUser.getId(), usmContext.getUserToken());
//            NotifyClientContext.setUsmsToken(result.getToken());
//            try {
////                NotifyClientContext.setNotifyServerAddr(new URL("http://172.16.100.115:8888/notify-appServer/rpc"));
//            } catch (MalformedURLException ex) {
//                ex.printStackTrace();
//            }
//            NotifyClientContext.setUsmServerAddr(ClientContext.getUSMServerRPCURL());
//            NotifyClientContext.setCurOrg(usmUser.getOrg());
            
            TimeServiceAgent.getInstance().startSyncTime();
            USMSessionKeeperAgent.getAgent().startKeepSession();

            RPCSessionHandler.startListenSession();
            UsmsClientContext.setUsmsServerAddr(ClientContext.getUSMServerRPCURL());
            UsmsClientContext.setCurrUser(usmUser);
            UsmsClientContext.setCurOrg(usmUser.getOrg());

            ServiceConfig.setUsmsServerURL(ClientContext.getUSMServerRPCURL());
            ConfigManager.putConfig(ConfigManager.KEY_SELF_MGMT, ConfigManager.VALUE_TRUE);
            ConfigManager.putConfig(ConfigManager.KEY_HAS_ORG, ConfigManager.VALUE_TRUE);

            TimeServiceAgent.getInstance().startSyncTime();
            ClientClockAgent.getInstance().startSyncTime();
            ClientContext.setUser(usmUser);
            ClientContext.setPassword(password);
            
            log.info("-----usm token = " + usmContext.getUserToken());
            return usmUser;
        } catch (LoginException ex) {
            ex.printStackTrace();
            throw new HttpRPCException(ex.getMessage(), ex.getCode() + InsideGuideErrorCode.USMS_ERROR_CODE_START);
        }
    }
}
