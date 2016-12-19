/*
 * ManPicAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-18 17:47:56
 */
package com.nazca.inside.guide.client.agent.entry;

import com.nazca.inside.guide.client.agent.AbstractAgent;
import com.nazca.inside.guide.client.listener.AgentListener;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.util.TimeFairy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author pengruirui
 */
public class ManPicAgent extends AbstractAgent<BufferedImage>{
    @Override
    protected BufferedImage doExecute() throws HttpRPCException {
        BufferedImage img=null;
        try {
             img = ImageIO.read(ManPicAgent.class.getResource("/com/nazca/inside/guide/res/password-auth-32.png"));
            new TimeFairy(1000).sleepIfNecessary();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         return img;
    }


}
