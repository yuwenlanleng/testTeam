/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazca.inside.guide.client.agent;

import com.nazca.io.httpdao.HttpClientContext;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.inside.guide.client.ClientContext;

/**
 *
 * @author blader
 */
public class USMSessionKeeperAgent {

    private USMSessionKeeperAgent() {
    }
    private static USMSessionKeeperAgent agent = null;

    public synchronized static USMSessionKeeperAgent getAgent() {
        if (agent == null) {
            agent = new USMSessionKeeperAgent();
        }
        return agent;
    }

    public void startKeepSession() {
        USMClockSyncAgent.getInstance().startSyncTime();
    }

    public void stopKeepSession() {
        USMClockSyncAgent.getInstance().stopSyncTime();
        try {
            HttpClientContext context = HttpRPC.getClientContext(ClientContext.getUSMServerRPCURL());
            context.setUserHttpSession("");
            context.setUserToken("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
