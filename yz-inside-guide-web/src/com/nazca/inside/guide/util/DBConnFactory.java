/*
 * DBConnFactory.java
 * 
 * Copyright(c) 2007-2015 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2015-05-27 12:08:29
 */
package com.nazca.inside.guide.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * 数据库连接工厂类
 * @author Qiu Dongyue
 */
public class DBConnFactory {
    public static final String DB_YZ_INSIDE_GUIDE = "yz_inside_guide";//数据库名称
    
    private static final String SOURCE_YZ_INSIDE_GUIDE = "jdbc/yz_inside_guide";//连接池
    
    public static Connection getConnection(String dbName) throws Exception {
        Context ctx = null;
        DataSource ds = null;
        Connection conn = null;
        try {
            ctx = new InitialContext();
            if (DB_YZ_INSIDE_GUIDE.equals(dbName)) {
                ds = (DataSource) ctx.lookup(SOURCE_YZ_INSIDE_GUIDE);
            }else {
                throw new Exception("数据源不存在！");
            }
            conn = ds.getConnection();
            return conn;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public static void closeAll(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
