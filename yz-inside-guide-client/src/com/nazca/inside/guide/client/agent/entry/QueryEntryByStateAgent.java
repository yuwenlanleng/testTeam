/*
 * QueryEntryByStateAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-08 17:01:31
 */
package com.nazca.inside.guide.client.agent.entry;

import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.agent.AbstractAgent;
import com.nazca.inside.guide.common.enums.EntryTypeEnum;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.inside.guide.common.rpc.EntryService;
import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.sql.PageResult;
import com.nazca.util.TimeFairy;

/**
 *
 * @author pengruirui
 */
public class QueryEntryByStateAgent extends AbstractAgent<PageResult<Entry>> {
    private EntryTypeEnum state;
    private int curPage;
    private int pageSize;
    PageResult<Entry> EntryResult = null;

    public void setState(EntryTypeEnum state,int curPage, int pageSize) {
        this.state = state;
        this.curPage=curPage;
        this.pageSize=pageSize;
    }

    @Override
    protected PageResult<Entry> doExecute() throws HttpRPCException {
        TimeFairy tf = new TimeFairy();

        EntryService service
                = HttpRPC.getService(EntryService.class, ClientContext.
                        getInsideGuideServerRPCURL());
        EntryResult = service.queryAllEntryManageByType(state,curPage, pageSize);
        tf.sleepIfNecessary();
        return EntryResult;
    }

}
