/*
 * QueryEntrytListAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-30 14:07:53
 */
package com.nazca.inside.guide.client.agent.entry;

import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.agent.AbstractAgent;
import com.nazca.inside.guide.common.model.EntryTableModelWrap;
import com.nazca.inside.guide.common.rpc.EntryService;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.util.TimeFairy;
import java.util.List;

/**
 *
 * @author pengruirui
 */
public class QueryEntrytListAgent extends AbstractAgent<List<EntryTableModelWrap>>{
    List<EntryTableModelWrap> allEntryListResult = null;
    @Override
    protected List<EntryTableModelWrap> doExecute() throws HttpRPCException {
       TimeFairy tf = new TimeFairy();
        EntryService service
                = HttpRPC.getService(EntryService.class, ClientContext.getInsideGuideServerRPCURL());
        allEntryListResult = service.queryAllEntryListManage();
        tf.sleepIfNecessary();
        return allEntryListResult;
    }
    
}
