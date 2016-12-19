/*
 * SyncServerDAO.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-28 18:00:10
 */
package com.nazca.inside.guide.server.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * 同步服务
 * @author Zhang Chun Nan
 */
public class SyncServerDAO extends AbstractDAO {

    public SyncServerDAO(Connection conn) {
        super(conn);
    }
}
