/*
 * GuideItemService.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-05-17 17:42:37
 */
package com.nazca.inside.guide.service;

import com.nazca.inside.guide.dao.GuideItemDao;
import com.nazca.inside.guide.model.GuideItem;
import java.util.List;
/**
 * 用来获得GuideItemList的的service层的实现的类
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class GuideItemService {

    /**
     * 用来获得GuideItemList的的service层的实现的方法
     * @param guideType 传入用来区分查询web、doc、sys
     * @return GuideItem的list
     */
    public List<GuideItem> getGuideItemListByguideType(String guideType) {
        GuideItemDao dao = new GuideItemDao();
        List<GuideItem> guideItemList = null;
        try {
            guideItemList = dao.queryList(guideType);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return guideItemList;
    }
}
