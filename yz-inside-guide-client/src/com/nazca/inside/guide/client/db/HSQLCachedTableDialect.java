/*
 * Copyright(c) 2007-2012 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2012-03-05 00:51:31
 */

package com.nazca.inside.guide.client.db;

import org.hibernate.dialect.HSQLDialect;

/**
 * 版权所有
 * @author Zhang Chun Nan
 */
public class HSQLCachedTableDialect extends HSQLDialect {

    public HSQLCachedTableDialect() {
	super();
    }

    public String getCreateTableString() {
	return "create cached table";
    }
}
