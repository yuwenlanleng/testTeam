/*
 * StartClient.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-15 16:43:45
 */
package com.nazca.inside.guide.client;

import com.nazca.inside.guide.client.ui.ClientFrame;
import com.nazca.ui.laf.NazcaLAFTool;
import com.nazca.usm.client.ServiceConfig;
import com.nazca.util.StringUtil;
import java.net.URL;

/**
 *
 * @author chenjianan
 */
public class StartClient {

    public static void main(String[] args) throws Exception {
        NazcaLAFTool.applyNazcaLAF();
        String usmRPC = System.getProperty("usms.rpc");
//        if (StringUtil.isEmpty(usmRPC)) {
//            ServerChooser chooser = new ServerChooser();
//            chooser.setVisible(true);
//        } else {
            if (StringUtil.isEmpty(usmRPC)) {
                usmRPC = ClientConfig.HTTPRPC_USM_SERVER;
            } else {
                ClientConfig.HTTPRPC_USM_SERVER = usmRPC;
            }
            System.out.println("usms.rpc = " + usmRPC);
            ServiceConfig.setUsmsServerURL(new URL(usmRPC));

            String indideGuideRPC = System.getProperty("indideGuide.rpc");
            if (StringUtil.isEmpty(indideGuideRPC)) {
                indideGuideRPC = ClientConfig.HTTPRPC_INSIDE_GUIDE_SERVER;
            } else {
                ClientConfig.HTTPRPC_INSIDE_GUIDE_SERVER = indideGuideRPC;
            }
            System.out.println("indideGuide.rpc = " + indideGuideRPC);
            ServiceConfig.setUsmsServerURL(new URL(indideGuideRPC));

            String helpUrl = System.getProperty("help.url");
            if (!StringUtil.isEmpty(helpUrl)) {
                ClientConfig.HELP_URL = helpUrl;
            }
            System.out.println("help.url = " + helpUrl);

            ClientContext.setMediaLibURL(new URL(ClientConfig.getMediaLibServerAddr()));
            ClientContext.setMediaImgURL(ClientConfig.getMediaLibImgAddr());
            ClientContext.setMediaDocURL(ClientConfig.getMediaLibFileAddr());
            
            ClientFrame.getInstance().setVisible(true);
//        }
    }
}
