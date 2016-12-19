/*
 * ClientContext.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-20 19:11:43
 */
package com.nazca.inside.guide.client;

import com.nazca.usm.model.USMSUser;
import com.nazca.inside.guide.common.consts.ProjectConst;
import com.nazca.inside.guide.common.enums.UserPermission;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author chenjianan
 */
public class ClientContext {

    private static USMSUser user = null;
    private static String password = "";
    private static URL mediaLibURL = null;
    private static String mediaImgURL = null;
    private static String mediaDocURL = null;

    public static void setUser(USMSUser user) {
        ClientContext.user = user;
    }

    public static USMSUser getUser() {
        return user;
    }

    public static String getUserId() {
        return user != null ? user.getId() : null;
    }

    public static boolean hasPermission(UserPermission permissionId) {
        return user != null && user.hasPermission(ProjectConst.YZ_INSIDE_GUIDE_MODULE_ID, permissionId.name());
    }

    /**
     * 拥有其中任意一个权限即返回true
     *
     * @param permissions
     * @return
     */
    public static boolean containPermissions(UserPermission... permissions) {
        if (permissions != null) {
            for (UserPermission p : permissions) {
                if (user != null && user.hasPermission(ProjectConst.YZ_INSIDE_GUIDE_MODULE_ID, p.name())) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    public static URL getUSMServerRPCURL() {
        try {
            return new URL(ClientConfig.HTTPRPC_USM_SERVER);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    


    public static URL getInsideGuideServerRPCURL() {
        try {
            return new URL(ClientConfig.HTTPRPC_INSIDE_GUIDE_SERVER);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void setPassword(String password) {
        ClientContext.password = password;
    }

    public static boolean isPasswordCorrect(String password) {
        return ClientContext.password.equals(password);
    }

    public static String getInsideGuideServerAddrWithoutPort() {
        int idx = ClientConfig.HTTPRPC_INSIDE_GUIDE_SERVER.indexOf('/', 7);
        int idx2 = ClientConfig.HTTPRPC_INSIDE_GUIDE_SERVER.indexOf(':', 7);
        if (idx2 > 0 && idx2 < idx) {
            return ClientConfig.HTTPRPC_INSIDE_GUIDE_SERVER.substring(7, idx2);
        } else {
            return ClientConfig.HTTPRPC_INSIDE_GUIDE_SERVER.substring(7, idx);
        }
    }

    public static URL getMediaLibURL() {
        return mediaLibURL;
    }

    public static void setMediaLibURL(URL mediaLibURL) {
        ClientContext.mediaLibURL = mediaLibURL;
    }

    public static String getMediaImgURL() {
        return mediaImgURL;
    }

    public static void setMediaImgURL(String mediaImgURL) {
        ClientContext.mediaImgURL = mediaImgURL;
    }

    public static String getMediaDocURL() {
        return mediaDocURL;
    }

    public static void setMediaDocURL(String mediaDocURL) {
        ClientContext.mediaDocURL = mediaDocURL;
    }

}
