/*
 * DeleteEntrytAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-28 14:57:03
 */
package com.nazca.inside.guide.client.agent.entry;

import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.agent.AbstractAgent;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.inside.guide.common.rpc.EntryService;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;

/**
 *
 * @author pengruirui
 */
public class DeleteEntryAgent extends AbstractAgent<String>{
    private String id;
    public void setParam(String id) {
	this.id = id;
    }

    @Override
    protected String doExecute() throws HttpRPCException {
         EntryService service = HttpRPC.getService(EntryService.class, ClientContext.getInsideGuideServerRPCURL());
	    service.deleteEntryManage(id);
            return id;   
    }
    


}
