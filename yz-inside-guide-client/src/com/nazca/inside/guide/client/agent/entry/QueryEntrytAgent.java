/*
 * QueryEntrytAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-28 10:34:08
 */
package com.nazca.inside.guide.client.agent.entry;

import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.agent.AbstractAgent;
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
public class QueryEntrytAgent extends AbstractAgent<PageResult<Entry>> {
    PageResult<Entry> allEntryResult = null;

    private int curPage;
    private int pageSize;

    public void setParam(int curPage, int pageSize) {
        this.curPage = curPage;
        this.pageSize = pageSize;
    }

    @Override
    protected PageResult<Entry> doExecute() throws HttpRPCException {
        TimeFairy tf = new TimeFairy();
        EntryService service
                = HttpRPC.getService(EntryService.class, ClientContext.getInsideGuideServerRPCURL());
        allEntryResult = service.queryAllEntryManage(curPage, pageSize);
        tf.sleepIfNecessary();
        return allEntryResult;
    }

}
