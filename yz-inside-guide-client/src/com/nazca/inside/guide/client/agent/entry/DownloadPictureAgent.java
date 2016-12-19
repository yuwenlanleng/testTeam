/*
 * DownloadPictureAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-19 17:55:18
 */
package com.nazca.inside.guide.client.agent.entry;


import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.agent.AbstractAgent;
import com.nazca.inside.guide.common.rpc.EntryService;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import java.io.InputStream;

/**
 *
 * @author pengruirui
 */
public class DownloadPictureAgent extends AbstractAgent<InputStream>{
    private String fileUrl;
    public void setParame(String fileUrl) {
       this.fileUrl=fileUrl;
    }
    
    @Override
    protected InputStream doExecute() throws HttpRPCException {
         EntryService service = HttpRPC.getService(EntryService.class, ClientContext.getInsideGuideServerRPCURL());
        return service.downloadPicture(fileUrl);
    }

    
    
}
