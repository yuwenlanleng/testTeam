/*
 * ModifyEntryAgent.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-28 10:33:05
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
public class ModifyEntryAgent extends AbstractAgent<Entry>{
     private Entry entry;
     private Entry curEntry;
     private boolean isSelectType;
     private boolean setSelectRadio;
     
    public void setSelectRadio(boolean setSelectRadio) {
      this.setSelectRadio=setSelectRadio;
    }

    public void setParam(Entry curEntry,boolean isSelectType) {
        this.curEntry = curEntry;
        this.isSelectType = isSelectType;
    }
    @Override
    protected Entry doExecute() throws HttpRPCException {
        EntryService service = HttpRPC.getService(EntryService.class, ClientContext.getInsideGuideServerRPCURL());
            System.out.println("修改结果值为            "+curEntry);
//	    return service.modifyEntryManage(entry,isSelectType);   
             return service.modifyEntryManage(curEntry,setSelectRadio,isSelectType);  
    }

  
    
}
