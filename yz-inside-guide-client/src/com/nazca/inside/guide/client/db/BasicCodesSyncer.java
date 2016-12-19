/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-03-22 09:48:33
 */
package com.nazca.inside.guide.client.db;

import com.nazca.io.httprpc.HttpRPC;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.util.NazcaFormater;
import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.ClientDataCache;
import com.nazca.inside.guide.client.listener.SyncServerListener;
import com.nazca.inside.guide.common.model.ServerSyncQuery;
import com.nazca.inside.guide.common.model.ServerSyncResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.swing.event.EventListenerList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 基础数据同步器
 *
 * @author liqin
 */
public class BasicCodesSyncer extends AbstractService4JPA {

    private static Log log = LogFactory.getLog(BasicCodesSyncer.class);
    private static BasicCodesSyncer instance = new BasicCodesSyncer();
    /**
     * 休眠间隔：500 ms
     */
    private static final int SLEEP_INTERVAL = 500;
    /**
     * 同步间隔：10分钟
     */
    private static final int SYNC_GAP = 10 * 60 * 2;

    private BasicCodesSyncer() {
    }

    public static BasicCodesSyncer getInstance() {
        return instance;
    }

    public synchronized void start() {
        if (syncThread == null) {
            running = true;
            syncNow();
            syncThread = new SyncThread();
            syncThread.start();
        }
    }

    public synchronized void stop() {
        if (syncThread != null && syncThread.isAlive()) {
            running = false;
            syncThread = null;
        }
    }

    public synchronized void syncNow() {
        count = -1;
    }
    private volatile int count = 0;
    private volatile boolean running = true;
    private EventListenerList listeners = new EventListenerList();
    private SyncThread syncThread = null;

    private class SyncThread extends Thread {

        public SyncThread() {
            super("SyncBasicData-Thread");
        }

        public void run() {
            while (running) {
                if (count % SYNC_GAP == 0) {
                    count = 0;
                    syncAllDatasAndWait();
                }
                try {
                    Thread.sleep(SLEEP_INTERVAL);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                count += 1;
            }
        }
    };

    /**
     * 从列表中移除特定的类
     *
     * @param queryList
     * @param class2remove
     */
    private void removeQueryFromList(List<ServerSyncQuery> queryList,
            Class class2remove) {
        Iterator<ServerSyncQuery> itr = queryList.iterator();
        while (itr.hasNext()) {
            if (itr.next().getClaz().equals(class2remove)) {
                itr.remove();
            }
        }
    }

    /**
     * 更新查询列表中的索引值
     *
     * @param queryList
     * @param class2update
     * @param startIdx
     */
    private void updateQueryInList(List<ServerSyncQuery> queryList,
            Class class2update, int startIdx) {
        Iterator<ServerSyncQuery> itr = queryList.iterator();
        while (itr.hasNext()) {
            ServerSyncQuery query = itr.next();
            if (query.getClaz().equals(class2update)) {
                query.setStartIdx(startIdx);
            }
        }
    }

    private synchronized void syncAllDatasAndWait() {
        List<Class> objClass2Sync = new ArrayList<Class>();
        //基础数据
//        objClass2Sync.add(Area.class);
        
        while (!syncData(objClass2Sync)) {
            log.info("sync restarting...");
            System.gc();
        }
    }

    /**
     * 返回是否发生了同步
     *
     * @param lastModifyTimeMap
     * @return
     */
    private synchronized boolean syncData(List<Class> objClass2Sync) {
        fireOnSyncStart();
//        log.info("start check cache db... " + NazcaFormater.getSimpleDateTimeString(new Date()));
//        EntityManager em = EMFactoryControler.createEM();
//        try {
//            em.getTransaction().begin();
//            boolean isReallySynced = false;
//            SyncService service = HttpRPC.getService(SyncService.class, ClientContext.getInsideGuideServerRPCURL());
//            //根据不同的Class获取远程服务器中修改时间大于相应的lastModifyTime的数据
//            SyncServiceDBHelper dao = new SyncServiceDBHelper(em);
//            List<ServerSyncQuery> queryList = dao.getObjectLastModifyTimes(objClass2Sync);
//            log.info("check cache db done... " + NazcaFormater.getSimpleDateTimeString(new Date()));
//            Map<Class, Date> maxModifyTimeMap = new HashMap<Class, Date>();
//            //同步基础码表
//            while (!queryList.isEmpty()) {
//                List<ServerSyncResult> resultList = service.syncDatas(queryList);
//                log.info("---- queryList ----");
//                for (ServerSyncQuery qu : queryList) {
//                    log.info(qu.getClaz().getSimpleName() + " start " + qu.getStartIdx() + " " + qu.getModifyTime());
//                }
//                log.info("---- queryList ----");
//                if (!resultList.isEmpty()) {
//                    List<Object> idList = new ArrayList<Object>();
//                    for (ServerSyncResult result : resultList) {
//                        log.info(result.getClaz().getSimpleName()
//                                + " " + result.getObjList().size()
//                                + " available " + result.getAvailableCount() + " " + result.getMaxModifyTime());
//
//                        if (maxModifyTimeMap.get(result.getClaz()) != null) {
//                            if (!maxModifyTimeMap.get(result.getClaz()).equals(result.getMaxModifyTime())) {
//                                em.getTransaction().rollback();
//                                return false;
//                            }
//                        } else {
//                            maxModifyTimeMap.put(result.getClaz(), result.getMaxModifyTime());
//                        }
//
//                        List<Object> obj = result.getObjList();
//                        if (obj != null && obj.size() > 0) {
//                            idList.addAll(obj);
//                        }
//                        int avaliableCount = result.getAvailableCount();
//                        if (avaliableCount == 0) {
//                            removeQueryFromList(queryList, result.getClaz());
//                            em.getTransaction().commit();
//                            em.getTransaction().begin();
//                        } else {
//                            updateQueryInList(queryList, result.getClaz(),
//                                    result.getLastDataIdx() + 1);
//                        }
//                    }
//                    if (!idList.isEmpty()) {
//                        isReallySynced = true;
//                        for (Object boj : idList) {
//                            em.merge(boj);
//                        }
//                        log.info("---- synced " + idList.size() + " objects ---");
//                    }
//                } else {
//                    queryList.clear();
//                }
//            }
//            em.getTransaction().commit();
//            if (isReallySynced) {
//                ClientDataCache.clearBasicCodeMap();
//            }
            fireOnSyncDown(true);
//        } catch (Throwable ex) {
//            ex.printStackTrace();
//            HttpRPCException re = dealException(em, ex);
//            fireOnSyncFailed(re.getCode(), re.getMessage());
//        } finally {
//            closeSession(em);
//        }
        return true;
    }

    public void addListener(SyncServerListener listener) {
        listeners.add(SyncServerListener.class, listener);
    }

    public void removeAllListeners() {
        for (SyncServerListener lis : listeners.getListeners(
                SyncServerListener.class)) {
            listeners.remove(SyncServerListener.class, lis);
        }
    }

    private void fireOnSyncStart() {
        log.info("==== sync start  " + NazcaFormater.getSimpleDateTimeString(new Date()));
        for (SyncServerListener syncServerListener
                : listeners.getListeners(SyncServerListener.class)) {
            syncServerListener.onSyncStart();
        }
    }

    private void fireOnSyncDown(boolean isReallySynced) {
        log.info("==== sync down -- isReallySynced = "
                + isReallySynced + "   " + NazcaFormater.getSimpleDateTimeString(new Date()));
        for (SyncServerListener syncServerListener
                : listeners.getListeners(SyncServerListener.class)) {
            syncServerListener.onSyncDone(isReallySynced);
        }
    }

    private void fireOnSyncFailed(int code, String msg) {
        log.info("!!!! sync failed   " + NazcaFormater.getSimpleDateTimeString(new Date()));
        for (SyncServerListener syncServerListener
                : listeners.getListeners(SyncServerListener.class)) {
            syncServerListener.onSyncFailed(code, msg);
        }
    }
}
