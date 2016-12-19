/*
 * UpBtu.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-08 08:33:14
 */
package com.nazca.inside.guide.client.agent.entry;

import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.agent.AbstractAgent;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.inside.guide.common.rpc.EntryService;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.util.TimeFairy;

/**
 *
 * @author pengruirui
 */
public class UpEntryAgent extends AbstractAgent<Entry>{
    private Entry entry;

    public void setParam(Entry entry) {
	this.entry = entry;
    }
    @Override
    protected Entry doExecute() throws HttpRPCException {
            TimeFairy tf = new TimeFairy();
            EntryService service = HttpRPC.getService(EntryService.class, ClientContext.getInsideGuideServerRPCURL());
	    service.upEntryManage(entry);
             tf.sleepIfNecessary();
            return entry;
    }


    }
  
  
