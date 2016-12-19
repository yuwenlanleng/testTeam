/*
 * PersistenceUtil.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-10-19 15:36:04
 */
package com.nazca.inside.guide.server.util;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author pengruirui
 */
public class PersistenceUtil {
    public static File getTemplateForDownloadFile(String fileName, String AttachmentDirType) throws IOException {
        String destPath = null;
        destPath = ImgServerConfig.getConfig().getValue(ImgServerConfig.DEST_PATH) + AttachmentDirType;

        File dir = new File(destPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String path = destPath + File.separator + fileName;
        File f = new File(path);
        if (!f.exists()) {
            f.createNewFile();
        }
        return f;
    }

//    public static Date getMaxTimeOfDay(Date d) {
//        if (d == null) {
//            return d;
//        }
//        Calendar c = Calendar.getInstance();
//        c.setTime(d);
//        c.set(Calendar.HOUR_OF_DAY, 23);
//        c.set(Calendar.MINUTE, 59);
//        c.set(Calendar.SECOND, 59);
//        return c.getTime();
//    }
//    public static boolean isLinux() {
//        if (System.getProperty("os.name").contains("linux") || System.getProperty("os.name").contains("LINUX")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static int buildCurrentYear(Date d) {
//        Calendar c = Calendar.getInstance();
//        c.setTime(d);
//        return c.get(Calendar.YEAR);
//    }  
}
