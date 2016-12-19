/*
 * ImgServerConfig.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-19 15:48:29
 */
package com.nazca.inside.guide.server.util;

import com.nazca.util.PropertyTool;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author pengruirui
 */
public class ImgServerConfig {
     /**
     * 存储配置文件的文件夹的名称
     */
    private static final String PRJ_NAME = "bheem_file_path";
    /**
     * 配置文件的名称
     */
    private static final String CONFIG_FILE = "filePathConfig.xml";
    /**
     * 上传文件或图片的基本路径
     */
    public static final String DEST_PATH = "destpath";
    /**
     * 读取配置文件对象
     */
    private Properties prop = null;
    /**
     * 单例
     */
    private static ImgServerConfig config = new ImgServerConfig();

    private ImgServerConfig() {
        loadDefaultConfig();
    }

    public static ImgServerConfig getConfig() {
        return config;
    }

    /**
     * 获取配置文件中 Key的 Value值
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        return prop.getProperty(key);
    }

    private void loadDefaultConfig() {
        try {
            prop = PropertyTool.loadProperty(new File(System.getProperty("user.home")), PRJ_NAME, CONFIG_FILE);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("mysqlConn config file not found, try to create one.");
            prop = new Properties();
            prop.put(DEST_PATH, System.getProperty("user.home") + File.separator + "bheem_upload" + File.separator + "download" + File.separator);
            try {
                PropertyTool.saveProperty(prop, new File(System.getProperty("user.home")), PRJ_NAME, CONFIG_FILE);
            } catch (IOException ex1) {
                ex1.printStackTrace();
                System.out.println("create appServer config file failed.");
            }
        }
    }
}
