/*
 * CreateEntryAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-28 10:32:10
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
public class CreateEntryAgent extends AbstractAgent<Entry> {
    private Entry entry;
    private boolean isSelectType;
    private boolean setSelectRadio;
    
    public void setParam(Entry ent,boolean isSelectType){
        this.entry = ent;
        this.isSelectType = isSelectType;
    }
     public void setSelectRadio(boolean setSelectRadio) {
      this.setSelectRadio=setSelectRadio;
    }
    @Override
    protected Entry doExecute() throws HttpRPCException {
         EntryService service = HttpRPC.getService(EntryService.class, ClientContext.getInsideGuideServerRPCURL());
	 System.out.println("CreateEntryAgent============="+entry);   
         return service.createEntryManage(entry,setSelectRadio,isSelectType);
            
    }
}
