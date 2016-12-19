/*
 * GuideItemDao.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-05-17 15:42:37
 */
package com.nazca.inside.guide.dao;

import com.nazca.inside.guide.consts.GuideItemTable;
import com.nazca.inside.guide.enums.GuideType;
import com.nazca.inside.guide.model.GuideItem;
import com.nazca.inside.guide.util.DBConnFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现获得list的具体方法的类
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class GuideItemDao implements GuideItemTable{
    private static final String GET_GUIDE_ITEM_INFO ="select * from "+ GuideItemTable.TABLE_NAME + " where " + GuideItemTable.GUIDE_TYPE +" = ? order by " + GuideItemTable.SORT_ORDER;
    /**
     * 从数据库获得页面显示的数据的list
     *
     * @param guideType
     * @return 返回guideItemsList
     */
    public List<GuideItem> queryList(String guideType) throws Exception{
        Connection conn = null; 
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<GuideItem> guideItems = null;
        try {
            conn = DBConnFactory.getConnection(DBConnFactory.DB_YZ_INSIDE_GUIDE);
            ps = conn.prepareStatement(GET_GUIDE_ITEM_INFO);
            ps.setString(1, guideType);//指定参数设置为给定 Java String 值。在将此值发送给数据库时，驱动程序将它转换成一个 SQL VARCHAR 或 LONGVARCHAR 
            rs = ps.executeQuery();
            guideItems = new ArrayList<>();
            while (rs.next()) {
                GuideItem gi = new GuideItem();
                gi.setId(rs.getString(GuideItemTable.ID));
                if (rs.getString(GuideItemTable.GUIDE_TYPE).equals(GuideType.web.name())) {
                    gi.setGuideType(GuideType.web);
                }
                if (rs.getString(GuideItemTable.GUIDE_TYPE).equals(GuideType.sys.name())) {
                    gi.setGuideType(GuideType.sys);
                }
                if (rs.getString(GuideItemTable.GUIDE_TYPE).equals(GuideType.doc.name())) {
                    gi.setGuideType(GuideType.doc);
                }
                gi.setName(rs.getString(GuideItemTable.NAME).trim());
                gi.setDes(rs.getString(GuideItemTable.DES));
                gi.setInnerUrl(rs.getString(GuideItemTable.INNER_URL));
                gi.setOuterUrl(rs.getString(GuideItemTable.OUTER_URL));
                gi.setDownloadUrlForIos(rs.getString(GuideItemTable.DOWNLOAD_URL_FORIOS)); 
                gi.setDownloadUrlForAndroid(rs.getString(GuideItemTable.DOWNLOAD_URL_FORANDROID));
                gi.setDownloadUrlForPc(rs.getString(GuideItemTable.DOWNLOAD_URL_FORPC));
                gi.setSortOrder(rs.getInt(GuideItemTable.SORT_ORDER));
                gi.setUploadPicture(rs.getString(GuideItemTable.UPLOAD_PICTURE));
                guideItems.add(gi);
            }
        }catch (Exception e) {
            throw e;
        }finally { 
           DBConnFactory.closeAll(conn, ps, rs);
        }
         return guideItems;
    }
}
