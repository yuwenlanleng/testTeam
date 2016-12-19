/*
 * ProjectConst.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-15 15:06:58
 */
package com.nazca.inside.guide.common.consts;

import java.io.File;

/**
 *
 * @author chenjianan
 */
public interface ProjectConst {
    String YZ_INSIDE_GUIDE_MODULE_ID = "yz-inside-guide-module";
    String PLATFORM_CONFIG_DIR_PATH = System.getProperty("user.home") + File.separator + ".yz-inside-guide";
    String CLIENT_PRJ_ID = "yz-inside-guide-client";
    String SERVER_PRJ_ID = "yz-inside-guide-server";
}
