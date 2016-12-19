/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-06-16 12:00:29
 */
package com.nazca.inside.guide.client.util;

import com.nazca.usm.model.USMSUser;
import com.nazca.util.StringUtil;

/**
 *
 * @author fred
 */
public class UserUtil {
    public static String getName(USMSUser user){
        if(user != null){
            if(StringUtil.isEmpty(user.getName())){
                if(StringUtil.isEmpty(user.getLoginName())){
                    return user.getId();
                }else{
                    return user.getLoginName();
                }
            }else{
                return user.getName();
            }
        }else{
            return "";
        }
    }
}
