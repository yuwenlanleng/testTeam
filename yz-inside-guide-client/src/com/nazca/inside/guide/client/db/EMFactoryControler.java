/*
 * Copyright(c) 2007-2011 by Yingzhi Tech All Rights Reserved
 *
 * Created at 2011-03-21 18:07:15
 */
package com.nazca.inside.guide.client.db;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author liqin
 */
public class EMFactoryControler {
    private static String jdbcPathForCache = "jdbc:hsqldb:hsql://127.0.0.1:" + DBServiceControler.PORT + "/"
            + DBServiceControler.CACHE_DB_NAME + ";";
    private static EntityManagerFactory factoryForCache = null;

    private EMFactoryControler() {
    }

    public synchronized static EntityManagerFactory getEMFactory() {
        if (factoryForCache == null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("javax.persistence.jdbc.url", jdbcPathForCache);
            factoryForCache = Persistence.createEntityManagerFactory("indideGuideCachePU", map);
        }
        return factoryForCache;
    }

    public synchronized static EntityManager createEM() {
        return getEMFactory().createEntityManager();
    }

    public synchronized static void closeEMFactory() {
        if (factoryForCache != null && factoryForCache.isOpen()) {
            factoryForCache.close();
            factoryForCache = null;
        }
    }
}
