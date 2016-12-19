/*
 * UploadPictureAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-19 14:50:58
 */
package com.nazca.inside.guide.client.agent.entry;

import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.agent.AbstractAgent;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.inside.guide.common.rpc.EntryService;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import java.io.InputStream;

/**
 *
 * @author pengruirui
 */
public class UploadPictureAgent extends AbstractAgent<Entry>{
    private String fileName;
    private InputStream is;
    private String attachmentDirType;
    public void setParame(String fileName,InputStream is,String attachmentDirType){
        this.fileName=fileName;
        this.is=is;
        this.attachmentDirType=attachmentDirType;
    }

    @Override
    protected Entry doExecute() throws HttpRPCException {
          EntryService service = HttpRPC.getService(EntryService.class, ClientContext.getInsideGuideServerRPCURL());
          return service.uplaodPicture(fileName,is,attachmentDirType);
    }

}
