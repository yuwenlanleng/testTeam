/*
 * Copyright(c) 2007-2010 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2010-06-15 19:01:35
 */
package com.nazca.inside.guide.client.agent;

import com.nazca.io.httpdao.HttpClientContext;
import com.nazca.io.httpdao.HttpClientContextListener;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.ui.UIUtilities;
import com.nazca.inside.guide.client.ClientContext;

/**
 * RPC会话处理类
 * @author Zhang Chunnan
 */
public final class RPCSessionHandler {
    private static boolean listenerAdded = false;
    private RPCSessionHandler() {
    }
    private static HttpClientContextListener lis = new HttpClientContextListener() {
        public void onHttpSessionTimeOut(HttpClientContext context) {
            System.out.println("*** session timeout " + context.getUrl() + " " + context.getUserHttpSession());
        }

        public void onSessionTokenNotValid(HttpClientContext context, String msg, int code) {
            System.out.println("*** session notvalid " + context.getUrl() + " " + context.getUserHttpSession());
            UIUtilities.errorDlg(null, "您的会话已超时，请重新登录");
            System.exit(0);
        }

        public void onHttpSessionCreated(HttpClientContext context) {
            System.out.println("*** session crated " + context.getUrl() + " " + context.getUserHttpSession());
        }

        public void startRebinding() {
        }

        public void onBindingFailed(String msg, int code) {
            System.out.println(msg + ", error code = " + code);
        }

        public void onBidingSuc(HttpClientContext context) {
        }
    };

    /**
     * 开始监听session变化事件
     */
    public synchronized static void startListenSession() {
        if(!listenerAdded){
            HttpClientContext ctx = HttpRPC.getClientContext(ClientContext.getInsideGuideServerRPCURL());
            ctx.addHttpContextListener(lis);
            listenerAdded = true;
        }
    }

    /**
     * 结束监听session变化事件
     */
    public synchronized static void stopListenSession(){
        HttpClientContext ctx = HttpRPC.getClientContext(ClientContext.getInsideGuideServerRPCURL());
        ctx.removeHttpContextListener(lis);
        listenerAdded = false;
    }
}
