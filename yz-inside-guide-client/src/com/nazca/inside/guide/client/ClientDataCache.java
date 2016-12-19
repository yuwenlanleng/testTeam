/*
 * Copyright(c) 2007-2011 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2011-06-17 13:18:33
 */
package com.nazca.inside.guide.client;

import java.util.*;

/**
 *
 * @author fred
 */
public class ClientDataCache {
    private enum DataType{
        basicCodeMap
    }
    
    private static Map<DataType, Object> cache = new EnumMap<DataType, Object>(DataType.class);
    
    public static void clearCache(){
        cache.clear();
    }
    
    public static void setBasicCodeName(Class claz, String code, String name){
        Map<String, String> map = (Map<String, String>)cache.get(DataType.basicCodeMap);
        if(map == null){
            map = new HashMap<String, String>();
            cache.put(DataType.basicCodeMap, map);
        }
        map.put(claz.getSimpleName()+code, name);
    }
    
    public static String getBasicCodeName(Class claz, String code){
        Map<String, String> map = (Map<String, String>)cache.get(DataType.basicCodeMap);
        if(map == null){
            map = new HashMap<String, String>();
            cache.put(DataType.basicCodeMap, map);
        }
        return map.get(claz.getSimpleName() + code);
    }
    
    public static void clearBasicCodeMap(){
        cache.remove(DataType.basicCodeMap);
    }
}
