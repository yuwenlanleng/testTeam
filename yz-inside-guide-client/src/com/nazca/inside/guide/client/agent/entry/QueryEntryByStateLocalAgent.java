/*
 * QueryEntryByStateLocalAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-11-25 16:38:22
 */
package com.nazca.inside.guide.client.agent.entry;

import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.agent.AbstractAgent;
import com.nazca.inside.guide.common.enums.EntryTypeEnum;
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
public class QueryEntryByStateLocalAgent extends AbstractAgent<List<Entry>>{
    private EntryTypeEnum state;
    private List<Entry> enrty;
    
    public void setState(EntryTypeEnum state) {
        this.state = state;
    }
    @Override
    protected List<Entry> doExecute() throws HttpRPCException {
        TimeFairy tf = new TimeFairy();
        EntryService service   = HttpRPC.getService(EntryService.class, ClientContext.  getInsideGuideServerRPCURL());             
        enrty = service.queryEntryManageLocalByType(state);
        tf.sleepIfNecessary();
        return enrty;
    }
    
}
