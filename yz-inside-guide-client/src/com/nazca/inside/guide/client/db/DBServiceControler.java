/*
 * Copyright(c) 2007-2011 by Yingzhi Tech All Rights Reserved
 *
 * Created at 2011-10-01 22:46:08
 */
package com.nazca.inside.guide.client.db;

import com.nazca.inside.guide.client.ClientConfig;
import com.nazca.inside.guide.client.ClientContext;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.commons.io.FileUtils;
import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

/**
 *
 * @author Zhang Chun Nan
 */
public class DBServiceControler {
    private static Server server = null;
    private static final String parentPathForCache = "cacheDatas";
    public static final String CACHE_DB_NAME = "cacheDB"; //缓存各类代码
    public static final String DB_FILE = "db";
    public static final int PORT = 37939;
    private static boolean serverRunning = false;

    public synchronized static void startDBServer() {
        if (!serverRunning) {
            if (ClientConfig.shouldRebuildCacheDB()) {
                rebulidDBForCache();
                ClientConfig.clearRebuildCacheDBFlag();
            }

            server = new Server();
            HsqlProperties p = new HsqlProperties();
            p.setProperty("server.database.0", ClientConfig.getProjectConfigPath() + File.separator
                    + parentPathForCache + File.separator + ClientContext.getInsideGuideServerAddrWithoutPort()
                    + File.separator + File.separator + CACHE_DB_NAME);
            p.setProperty("server.dbname.0", CACHE_DB_NAME);
            p.setProperty("hsqldb.default_table_type", "cached");

            try {
                server.setProperties(p);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            server.setLogWriter(new PrintWriter(System.out));
            server.setErrWriter(new PrintWriter(System.err));
            server.setAddress("127.0.0.1");
            server.setPort(PORT);
            server.start();
            System.out.println("db server started...");
            serverRunning = true;
        }
    }

    public static void stopDBServer() {
        if (server != null && serverRunning) {
            EMFactoryControler.closeEMFactory();
            server.stop();
            System.out.println("db server stopped ....");
            server = null;
            serverRunning = false;
        }
    }

    public static void rebulidDBForCache() {
        stopDBServer();
        try {
            File dir = new File(ClientConfig.getProjectConfigPath() + File.separator + parentPathForCache);
            FileUtils.deleteDirectory(dir);
            System.out.println(dir + " deleted!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
