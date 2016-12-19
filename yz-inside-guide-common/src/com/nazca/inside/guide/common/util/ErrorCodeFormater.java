/*
 * ErrorCodeFormater.java
 * 
 * Copyright(c) 2007-2009 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2009-12-23 17:44:15
 */

package com.nazca.inside.guide.common.util;

import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 *
 * @author fred
 */
public final class ErrorCodeFormater {
    private ErrorCodeFormater(){}
    private static DecimalFormat df = new DecimalFormat("000000");
    private static ResourceBundle errorBundle = null;
     /**
     * 解释错误代码，返回错误释义
     *
     * @param errorCode
     * @return
     */
    public static String explainErrorCode(int errorCode) {
        try {
            String codeString = df.format(errorCode);
            return errorBundle.getString(codeString);
        } catch (Exception ex) {
            return errorBundle.getString("unknown");
        }
    }
    
    
    /**
     * 格式化错误代码和错误释义
     * @param msg
     * @param code
     * @return 返回结果样例为：用户名或密码错误(#000010)
     */
    public static String formate(String msg, int code){
        if(code >= 0){
            String codeString = df.format(code);
            return msg + "(#" + codeString + ")";
        }else{
            return msg + "(#" + Integer.toHexString(code) + ")";
        }
    }
}
