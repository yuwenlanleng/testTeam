/*
 * QueryEntrytLocalAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-11-25 16:38:11
 */
package com.nazca.inside.guide.client.agent.entry;

import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.agent.AbstractAgent;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.inside.guide.common.rpc.EntryService;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.util.TimeFairy;
import java.util.List;

/**
 *
 * @author pengruirui
 */
public class QueryEntrytLocalAgent extends AbstractAgent<List<Entry>>{
    private List<Entry> entry=null;
    @Override
    protected List<Entry> doExecute() throws HttpRPCException {
        TimeFairy tf = new TimeFairy();
        EntryService service
                = HttpRPC.getService(EntryService.class, ClientContext.getInsideGuideServerRPCURL());      
        entry = service.queryAllEntryLocalManage();
        tf.sleepIfNecessary();
        return entry;
    }
    
}
