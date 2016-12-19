/*
 * ClientConfig.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-20 15:57:18
 */
package com.nazca.inside.guide.client;

import com.nazca.util.PropertyTool;
import com.nazca.util.StringUtil;
import com.nazca.inside.guide.common.consts.ProjectConst;
import java.io.File;
import java.util.Properties;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author chenjianan
 */
public class ClientConfig {

    public static final String CONFIG_FILE_NAME = "inside_guide_config.cfg";
    public static String INSTALL_SVN_CLIENT = "";
    public static String INSTALL_SVN_UPDATE = "";
    public static String PROJECT_NAME_OA = "yz-inside-guide";
    public static String HTTPRPC_USM_SERVER = "http://172.16.100.83:8090/usms-web/rpc";
    public static String HTTPRPC_INSIDE_GUIDE_SERVER = "http://172.16.100.115:8081/yz-inside-guide-server/rpc";
//    public static String HTTPRPC_INSIDE_GUIDE_SERVER = "http://localhost:8080/yz-inside-guide-server/rpc";
    public static String DEFAULT_MEDIA_LIB_SERVER = "http://172.16.100.115:8080/media-lib-server/rpc";
    public static String DEFAULT_MEDIA_LIB_IMG_ADDR = "http://172.16.100.115/images/";
    public static String DEFAULT_MEDIA_LIB_FILE_ADDR = "http://172.16.100.115/file/";
    public static String HELP_URL = "";
    public static final String KEY_USER_NAME = "loginName";
    public static final String KEY_USER_PWD = "password";
    public static final String KEY_REBUILD_CACHE_DB = "rebuildCacheDBOnStart";
    public static final String KEY_CACHE_DB_VERSION = "cacheDBVersion";
    public static final String KEY_CONFIG_VERSION = "configVersion";
    public static final String TARGET_CACHE_DB_VERSION = "1.3";
    public static final String TARGET_CONFIG_VERSION = "1.0";
    public static final String VAL_AUTOLOGIN_TRUE = "true";
    public static final String VAL_AUTOLOGIN_FALSE = "false";
    private static final String MY_APP = "inside.guide.client.myapp=*(lkgf904523f@#$*(*%^#ds*^";

    public static final String KEY_MEDIA_LIB_RPC_ADDR = "inside.guide.rpc.addr";
    public static final String KEY_MEDIA_IMG_URL = "inside.guide.img.url";
    public static final String KEY_MEDIA_DOC_URL = "inside.guide.doc.url";

    private static Properties prop = new Properties();

    static {

        try {
            prop = PropertyTool.
                    loadProperty(new File(ProjectConst.PLATFORM_CONFIG_DIR_PATH), ProjectConst.CLIENT_PRJ_ID,
                            CONFIG_FILE_NAME);
            //临时代码，主动修改配置文件
            if (StringUtil.isEmpty(prop.getProperty(KEY_MEDIA_LIB_RPC_ADDR))) {
                prop = createConfigFile();
            }

            String version = prop.getProperty(KEY_CONFIG_VERSION, "");
            if (!TARGET_CONFIG_VERSION.equals(version)) {
                prop.setProperty(KEY_CONFIG_VERSION, TARGET_CONFIG_VERSION);
                String pwd = prop.getProperty(KEY_USER_PWD, "");
                if (!StringUtil.isEmpty(pwd)) {
                    String userId = getUserId();
                    saveUserIdAndPassword(userId, pwd);
                } else {
                    saveConfig();
                }
            }
        } catch (Exception ex) {
            prop = createConfigFile();
            ex.printStackTrace();
        }
    }

    private static Properties createConfigFile() {
        Properties p = new Properties();
        try {
            p.put(KEY_USER_NAME, "");
            p.put(KEY_CONFIG_VERSION, TARGET_CONFIG_VERSION);
            prop.put(KEY_REBUILD_CACHE_DB, "true");
            p.put(KEY_MEDIA_LIB_RPC_ADDR, DEFAULT_MEDIA_LIB_SERVER);
            p.put(KEY_MEDIA_IMG_URL, DEFAULT_MEDIA_LIB_IMG_ADDR);
            p.put(KEY_MEDIA_DOC_URL, DEFAULT_MEDIA_LIB_FILE_ADDR);
            PropertyTool.
                    saveProperty(p, new File(ProjectConst.PLATFORM_CONFIG_DIR_PATH), ProjectConst.CLIENT_PRJ_ID,
                            CONFIG_FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    public static String getProjectConfigPath() {
        return ProjectConst.PLATFORM_CONFIG_DIR_PATH + File.separator + "." + ProjectConst.CLIENT_PRJ_ID;
    }

    private static void saveConfig() {
        try {
            PropertyTool.saveProperty(prop, new File(ProjectConst.PLATFORM_CONFIG_DIR_PATH), ProjectConst.CLIENT_PRJ_ID,
                    CONFIG_FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveUserId(String userId) {
        prop.setProperty(KEY_USER_NAME, userId);
        prop.remove(KEY_USER_PWD);
        saveConfig();
    }

    public static void saveUserIdAndPassword(String userId, String password) {
        prop.setProperty(KEY_USER_NAME, userId);
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(MY_APP);
        String myEncryptedText = textEncryptor.encrypt(password);
        prop.setProperty(KEY_USER_PWD, myEncryptedText);
        saveConfig();
    }

    public static String getMediaLibServerAddr() {
        return prop.getProperty(KEY_MEDIA_LIB_RPC_ADDR);
    }

    public static String getMediaLibImgAddr() {
        return prop.getProperty(KEY_MEDIA_IMG_URL);
    }

    public static String getMediaLibFileAddr() {
        return prop.getProperty(KEY_MEDIA_DOC_URL);
    }

    public static void setPassword(String password) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(MY_APP);
        String myEncryptedText = textEncryptor.encrypt(password);
        prop.setProperty(KEY_USER_PWD, myEncryptedText);
        saveConfig();
    }

    public static void removeUserIdAndPassword() {
        prop.remove(KEY_USER_NAME);
        prop.remove(KEY_USER_PWD);
        saveConfig();
    }

    public static String getUserId() {
        return prop.getProperty(KEY_USER_NAME, "");
    }

    public static String getPassword() {
        String encryptedPass = prop.getProperty(KEY_USER_PWD, "");
        if (StringUtil.isEmpty(encryptedPass)) {
            return "";
        } else {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(MY_APP);
            return textEncryptor.decrypt(encryptedPass);
        }
    }

    public static boolean shouldRebuildCacheDB() {
        if (TARGET_CACHE_DB_VERSION.equals(prop.getProperty(KEY_CACHE_DB_VERSION))) {
            return "true".equals(prop.getProperty(KEY_REBUILD_CACHE_DB));
        } else {
            return true;
        }
    }

    public static void markRebuildCacheDBFlag() {
        prop.setProperty(KEY_REBUILD_CACHE_DB, "true");
        saveConfig();
    }

    public static void clearRebuildCacheDBFlag() {
        prop.remove(KEY_REBUILD_CACHE_DB);
        prop.setProperty(KEY_CACHE_DB_VERSION, TARGET_CACHE_DB_VERSION);
        saveConfig();
    }

    public static boolean shouldRebuildConfig() {
        return TARGET_CONFIG_VERSION.equals(prop.getProperty(KEY_CONFIG_VERSION));
    }

    public static void resetConfigVersion() {
        prop.setProperty(KEY_CONFIG_VERSION, TARGET_CONFIG_VERSION);
        saveConfig();
    }
}
