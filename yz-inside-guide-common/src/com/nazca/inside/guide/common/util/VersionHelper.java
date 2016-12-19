/*
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at Nov 11, 2009 11:34:53 AM
 */
package com.nazca.inside.guide.common.util;

import com.nazca.util.PropertyTool;
import com.nazca.inside.guide.common.consts.ProjectConst;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 *
 * @author fred
 */
public final class VersionHelper {
    private static final String BUILD_VERSION_PATH = "/build-version.txt";
    private static final String RELEASE_VERSION_PATH = "/release-version.txt";
    private static String totalVersion;
    private static String releaseVersion;
    private static String buildVersion;
    private static boolean usingBetaChannel = false;
    private static final String CONFIG_FILE = "version-config.xml";
    private static final String KEY_USING_BETA_CHANNEL = "usingBetaChannel";

    public static void setUsingBetaChannel(boolean flag) {
        usingBetaChannel = flag;

    }

    public static boolean isUsingBetaChannel() {
        return usingBetaChannel;
    }

    static {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(VersionHelper.class.getResourceAsStream(RELEASE_VERSION_PATH)));
            releaseVersion = reader.readLine();
        } catch (Throwable ex) {
            System.out.println("can't get release version");
        }
        releaseVersion = releaseVersion == null ? "1.0.0" : releaseVersion;

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(VersionHelper.class.getResourceAsStream(BUILD_VERSION_PATH)));
            buildVersion = reader.readLine();
        } catch (Throwable ex) {
            System.out.println("can't get build version");
        }
        buildVersion = buildVersion == null ? "" : buildVersion;

        totalVersion = releaseVersion + ' ' + buildVersion;
        
        try {
            Properties props = PropertyTool.loadProperty(new File(ProjectConst.PLATFORM_CONFIG_DIR_PATH), ProjectConst.CLIENT_PRJ_ID, CONFIG_FILE);
            String value = props.getProperty(KEY_USING_BETA_CHANNEL);
            if ("true".equals(value)) {
                usingBetaChannel = true;
            } else {
                usingBetaChannel = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Properties props = new Properties();
            props.setProperty(KEY_USING_BETA_CHANNEL, "true");
            try {
                PropertyTool.saveProperty(props, new File(ProjectConst.PLATFORM_CONFIG_DIR_PATH), ProjectConst.CLIENT_PRJ_ID, CONFIG_FILE);
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
        }
    }

    /**
     * 完整版本号 样例：1.0.0 Build 200909021123 说明：完整版本号 = 发布号 +
     * 构建号，详见#getReleaseVersion()和#getBuildVersion()
     *
     * @return
     */
    public static final String getTotalVersion() {
        return totalVersion;
    }

    /**
     * 发布号 样例：1.0.0
     * 说明：第1位为大版本，在发生重大变动时增加（一般不改变）；第2位为次版本，在发生重要功能变动时增加；第3位为小版本，在发生小功能变动时增加
     *
     * @return
     */
    public static final String getReleaseVersion() {
        return releaseVersion;
    }

    /**
     * 构建号 样例：Build 200909021123 说明：前8位是Build日期，后4位是SVN版本号
     *
     * @return
     */
    public static final String getBuildVersion() {
        return buildVersion;
    }
}