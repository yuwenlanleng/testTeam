/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-10-04 14:55:31
 */
package com.nazca.inside.guide.client.db;

import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.inside.guide.common.model.ServerSyncQuery;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Zhang Chun Nan
 */
public class SyncServiceDBHelper extends AbstractService4JPA {
    private EntityManager em = null;
    public SyncServiceDBHelper(EntityManager em) {
        this.em = em;
    }
    
    /**
     * 找出所有model对应的lastModifyTime
     * @param objClass2Sync
     * @return
     * @throws HttpRPCException 
     */
    public List<ServerSyncQuery> getObjectLastModifyTimes(List<Class> objClass2Sync) throws Exception {
        List<ServerSyncQuery> queryList = new ArrayList<ServerSyncQuery>();
        for (Class c : objClass2Sync) {
            Date lastModifyTime = em.createQuery("select max(modifyTime) from " + c.getSimpleName(),
                    Date.class).getSingleResult();
            if (lastModifyTime == null) {
                lastModifyTime = new Date(0);
            }
            ServerSyncQuery query = new ServerSyncQuery();
            query.setClaz(c);
            query.setModifyTime(lastModifyTime);
            queryList.add(query);
        }
        return queryList;
    }
}
