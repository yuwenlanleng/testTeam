/*
 * ConnectionFactory.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-11-04 17:39:51
 */
package com.nazca.inside.guide.server.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Chen Jianan
 */
public class ConnectionFactory {

    private static final String CONNECTION_POOL = "jdbc/yz_inside_guide";
    private static DataSource connPool;
    private static Log log = LogFactory.getLog(ConnectionFactory.class);

    static {
        try {
            Context env = new InitialContext();
            connPool = (DataSource) env.lookup(CONNECTION_POOL);
            if (connPool == null) {
                log.warn("Connection pool is NULL");
            } else {
                log.info("connection pool looked up");
            }
        } catch (NamingException ex) {
            log.error("Failed to look up connection pool from context", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return connPool.getConnection();
    }
}
