/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazca.inside.guide.client.agent;

import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.usm.service.rpc.UserRPCService;
import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.listener.ChgPwdAgentListener;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blader
 */
public class ChgPwdAgent {

    public ChgPwdAgent() {
        this.listeners = new Vector<ChgPwdAgentListener>();
    }
    private List<ChgPwdAgentListener> listeners;

    public void startChgPwd(final String userid, final String orgPwd, final String newPwd) {
        Thread thd = new Thread(new Runnable() {

            public void run() {
                fireOnStartChgPwd(userid);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ChgPwdAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
                doChgPwd(userid, orgPwd, newPwd);
            }
        });
        thd.start();
    }

    public void addChgPwdAgentListener(ChgPwdAgentListener listener) {
        this.listeners.add(listener);
    }

    private void doChgPwd(String userid, String orgPwd, String newPwd) {
        try {
            UserRPCService service = HttpRPC.getService(UserRPCService.class, ClientContext.getUSMServerRPCURL());
            service.changePassword(userid, orgPwd, newPwd);
            fireOnChgPwdSuc(userid);
        } catch (HttpRPCException hexp) {
            int code = hexp.getCode();
            fireOnChgPwdFailed(userid, hexp.getMessage(), code);
            hexp.printStackTrace();
        } catch (Exception ex) {
            fireOnChgPwdFailed(userid, "网络连接失败", 1002);
            ex.printStackTrace();
        }
    }

    private void fireOnStartChgPwd(String userid) {
        for (ChgPwdAgentListener lis : this.listeners) {
            lis.onStartChgPwd(userid);
        }
    }

    private void fireOnChgPwdSuc(String userid) {
        for (ChgPwdAgentListener lis : this.listeners) {
            lis.onPwdChgSuc(userid);
        }
    }

    private void fireOnChgPwdFailed(String userid, String msg, int code) {
        for (ChgPwdAgentListener lis : this.listeners) {
            lis.onPwdChgFailed(userid, msg, code);
        }
    }
}
