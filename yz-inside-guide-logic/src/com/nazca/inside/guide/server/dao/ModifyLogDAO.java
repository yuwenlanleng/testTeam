/*
 * ModifyLogDAO.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-15 15:56:43
 */
package com.nazca.inside.guide.server.dao;

import com.nazca.inside.guide.common.jpa.ModifyLog;
import java.sql.Connection;
import java.util.List;

/**
 * 修改日志（系统）
 *
 * @author Lijin
 */
public class ModifyLogDAO extends AbstractDAO {
    public ModifyLogDAO(Connection conn) {
        super(conn);
    }
}
