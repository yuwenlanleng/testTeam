/*
 * AbstractDAO.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-19 20:29:48
 */
package com.nazca.inside.guide.server.dao;

import java.sql.Connection;


/**
 *
 * @author Zhang Chun Nan
 */
public abstract class AbstractDAO {
    protected Connection conn = null;

    public AbstractDAO(Connection conn) {
        this.conn = conn;
    }
}
