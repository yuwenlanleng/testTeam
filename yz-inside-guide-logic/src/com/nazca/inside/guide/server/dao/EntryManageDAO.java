/*
 * EntryManageDAO.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-27 16:30:35
 */
package com.nazca.inside.guide.server.dao;

import com.nazca.inside.guide.common.enums.EntryTypeEnum;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.inside.guide.common.model.EntryTableModelWrap;
import com.nazca.inside.guide.common.table.consts.EntryManageTableConsts;
import com.nazca.sql.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author pengruirui
 */
public class EntryManageDAO extends AbstractDAO {
    private static final String COUNT = "count";
    private static final String ALLPAGCOUNT = "allPagCount";//状态总数
    private static final String ALLCOUNT = "allCount";//总数
    private static final String MAXORDER = "maxorder";//最大排序值
    private static final String MINORDER = "minorder";//最小排序值
    private static final String MAXORDERBYTYPE = "maxOrderByType";

    public EntryManageDAO(Connection conn) {
        super(conn);
    }
    /**
     * 查询所有SQL  
     */
    private static final String QUERY_ALL_ENTRYMANAGE_SQL = "select * from "
            + EntryManageTableConsts.TABLE_NAME.getName()
            + " order by "
            + EntryManageTableConsts.SORT_ORDER.getName()
            + " limit ?,?";
     private static final String QUERY_ALL_ENTRYMANAGE_LOCAL_SQL="select * from "
             + EntryManageTableConsts.TABLE_NAME.getName()
            + " order by "
            + EntryManageTableConsts.SORT_ORDER.getName();
            
    /**
     * 根据状态查询页面总数
     */
    private static final String PAGALLCOUNT = "select count(*) as "
            + ALLPAGCOUNT + " from "
            + EntryManageTableConsts.TABLE_NAME.getName() + " where "
            + EntryManageTableConsts.GUIDE_TYPE.getName()
            + " =?";
    /**
     * 查询页面总数
     */
    private static final String AllCount = "select count(*) as " + ALLCOUNT
            + " from "
            + EntryManageTableConsts.TABLE_NAME.getName();
    /**
     * 查询最大排序值    MAX_SORT_ORDER_BY_TYPE
     */
    private static final String MAX_SORT_ORDER = "select max("
            + EntryManageTableConsts.SORT_ORDER.getName()
            + ") as " + MAXORDER + " from "           
            + EntryManageTableConsts.TABLE_NAME.getName();
    /**
     * 查询最大排序值    MAX_SORT_ORDER_BY_TYPE
     */
    private static final String MAX_SORT_ORDER_BY_TYPE = "select max("
            + EntryManageTableConsts.SORT_ORDER.getName()
            + ") as " + MAXORDER + " from "           
            + EntryManageTableConsts.TABLE_NAME.getName()
            +" where "+EntryManageTableConsts.GUIDE_TYPE.getName()+"=?";
    /**
     * 查询最小排序值   UPDATE_SORTORDER_SQL
     */
    private static final String MIN_SORT_ORDER = "select min("
            + EntryManageTableConsts.SORT_ORDER.getName()
            + ") as " + MINORDER + " from "       
            + EntryManageTableConsts.TABLE_NAME.getName();
     /**
     * 修改sortorder   
     */
    private static final String UPDATE_SORTORDER_SQL = "update "
            + EntryManageTableConsts.TABLE_NAME.getName()
            + " set " +  EntryManageTableConsts.SORT_ORDER
            +"=? where "+ EntryManageTableConsts.SORT_ORDER+"=?";
    /**
     * 查询范围内数据    QUERY_ENTRY_BY_LIMIT_AND_TYPE_SQL;
     */  
     private static final String QUERY_ENTRY_BY_LIMIT_SQL = "select * from "
            + EntryManageTableConsts.TABLE_NAME.getName()
            + " where " +  EntryManageTableConsts.SORT_ORDER
            +" between ? and ? order by "+EntryManageTableConsts.SORT_ORDER+" asc";
     
      /**
     * 查询类别范围内数据    select * from guideitem where guide_type='sys' and (sort_order between 8 and 10);
     */ 
      private static final String QUERY_ENTRY_BY_LIMIT_AND_TYPE_SQL = "select * from "
            + EntryManageTableConsts.TABLE_NAME.getName()
            + " where " +  EntryManageTableConsts.GUIDE_TYPE
            +"=? and ("+EntryManageTableConsts.SORT_ORDER+" between ? and ?) order by "+EntryManageTableConsts.SORT_ORDER+" asc";
           
    /**
     * 查询列表SQL
     *
     */
    private static final String QUERY_ALL_ENTRYMANAGE_LIST_SQL = "select "
            + EntryManageTableConsts.GUIDE_TYPE.getName() + ", count(*) as "
            + COUNT + " from "
            + EntryManageTableConsts.TABLE_NAME.getName() + " group by "
            + EntryManageTableConsts.GUIDE_TYPE.getName();

    /**
     * 查询不同SQL
     */
    private static final String QUERY_ALL_ENTRYMANAGE_BYTYPE_SQL
            = "select * from " + EntryManageTableConsts.TABLE_NAME.getName()
            + " where " + EntryManageTableConsts.GUIDE_TYPE.getName()
            + " = ?   "
            + " order by "
            + EntryManageTableConsts.SORT_ORDER.getName()
            + " limit ?,?";
    private static final String QUERY_ALL_ENTRYMANAGE_LOCAL_BYTYPE_SQL
            = "select * from " + EntryManageTableConsts.TABLE_NAME.getName()
            + " where " + EntryManageTableConsts.GUIDE_TYPE.getName()
            + " = ?   "
            + " order by "
            + EntryManageTableConsts.SORT_ORDER.getName();
            

    /**
     * 查询单条idSQL
     */
    private static final String QUERY_ENTRYMANAGE_BYID_SQL = "SELECT * FROM  "
            + EntryManageTableConsts.TABLE_NAME.getName() + "  WHERE  "
            + EntryManageTableConsts.ID.getName() + "=?";
    /**
     * 查询单条entrySQL
     */
    private static final String QUERY_ENTRYMANAGE_BYENTRY_SQL
            = "SELECT * FROM  "
            + EntryManageTableConsts.TABLE_NAME.getName() + "  WHERE  "
            + EntryManageTableConsts.NAME.getName() + "=? and "
            + EntryManageTableConsts.INNER_URL.getName() + "=? and "
            + EntryManageTableConsts.OUTER_URL.getName() + "=? and "
            + EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName()+ "=? and "           
            + EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName() + "=? and "
            + EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName() + "=? and "
            + EntryManageTableConsts.DES.getName() + "=? and "
            + EntryManageTableConsts.GUIDE_TYPE.getName() + "=? ";
    /**
     * 添加团队SQL
     */
    private static final String ADD_ENTRYMANAGE_SQL = "INSERT INTO "
            + EntryManageTableConsts.TABLE_NAME.getName() + "("
            + EntryManageTableConsts.GUIDE_TYPE.getName() + ","
            + EntryManageTableConsts.SORT_ORDER.getName() + ","
            + EntryManageTableConsts.ID.getName() + ","
            + EntryManageTableConsts.NAME.getName() + ","
            + EntryManageTableConsts.DES.getName() + ","
            + EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName() + ","
            + EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName() + ","
            + EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName() + ","
            + EntryManageTableConsts.INNER_URL.getName() + ","
            + EntryManageTableConsts.OUTER_URL.getName() + ","
            + EntryManageTableConsts.CREATOR.getName() + ","
            + EntryManageTableConsts.CREATE_TIME.getName() + ","
            + EntryManageTableConsts.MODIFIER.getName() + ","
            + EntryManageTableConsts.MODIFY_TIME.getName()+","
            + EntryManageTableConsts.UPLOAD_PICTURE.getName()
            + ")"
            + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    /**
     * 上,下移SQL      UPDATE guideitem
         SET sort_order = CASE sort_order
        WHEN 2 THEN 1
        WHEN 1 THEN 2    
         END
        WHERE sort_order IN (1,2)
    * 
     */
     private static final String UP_OR_DOWN_ENTRY_SQL = "UPDATE "
             + EntryManageTableConsts.TABLE_NAME.getName() +" SET "          
             + EntryManageTableConsts.SORT_ORDER.getName()+" = CASE "
             + EntryManageTableConsts.SORT_ORDER.getName()
             +" WHEN ? "+"THEN ? "
             +" WHEN ? "+"THEN ? "
             +" END "+" WHERE "
             + EntryManageTableConsts.SORT_ORDER.getName()
             +" IN (?,?)";
     

    /**
     * 修改SQL
     */
    private static final String MODIFY_ENTRYMANAGE_SQL = "UPDATE  "
            + EntryManageTableConsts.TABLE_NAME.getName() + "  SET "
            + EntryManageTableConsts.GUIDE_TYPE.getName() + "=?,"
            + EntryManageTableConsts.NAME.getName() + "=?,"
            + EntryManageTableConsts.DES.getName() + "=?,"
            + EntryManageTableConsts.INNER_URL.getName() + "=?,"
            + EntryManageTableConsts.OUTER_URL.getName() + "=?,"
            + EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName() + "=?,"
            + EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName() + "=?,"
            + EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName() + "=?,"
            + EntryManageTableConsts.CREATOR.getName() + "=?,"
            + EntryManageTableConsts.CREATE_TIME.getName() + "=?,"
            + EntryManageTableConsts.MODIFIER.getName() + "=?,"
            + EntryManageTableConsts.MODIFY_TIME.getName() + "=?,"
            + EntryManageTableConsts.SORT_ORDER.getName() + "=?,"
            + EntryManageTableConsts.UPLOAD_PICTURE.getName()+"=?"
            + "  where " + EntryManageTableConsts.ID.getName() + "=?";

    /**
     * 删除团队SQL
     */
    private static final String DELETE_ENTRYMANAGE_SQL = "DELETE  from "
            + EntryManageTableConsts.TABLE_NAME.getName()
            + "  where " + EntryManageTableConsts.ID.getName() + "=?";

    /**
     * 查询总记录数
     */
    public int queryAllTotalCount() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int totols = 0;
        try {
            ps = conn.prepareStatement(AllCount);
            rs = ps.executeQuery();
            if (rs.next()) {
                totols = rs.getInt(ALLCOUNT);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return totols;
    }
  

    /**
     * 根据id查询单条
     */
    public Entry queryEntryById(String id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Entry data = null;
        try {
            ps = conn.prepareStatement(QUERY_ENTRYMANAGE_BYID_SQL);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                data = new Entry();
                data.setId(id);
                data.setGuideType(EntryTypeEnum.valueOf(rs.getString(EntryManageTableConsts.GUIDE_TYPE.getName())));
                data.setName(rs.getString(EntryManageTableConsts.NAME.getName()));
                data.setDes(rs.getString(EntryManageTableConsts.DES.getName()));
                data.setInnerUrl(rs.getString(EntryManageTableConsts.INNER_URL.getName()));
                data.setOuterUrl(rs.getString(EntryManageTableConsts.OUTER_URL.getName()));
                data.setDownloadUrlForIos(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName()));
                data.setDownloadUrlForAndroid(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName()));
                data.setDownloadUrlForPc(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName()));
                data.setSortOrder(rs.getDouble(EntryManageTableConsts.SORT_ORDER.getName()));
                data.setCreator(rs.getString(EntryManageTableConsts.CREATOR.getName()));
                data.setModifier(rs.getString(EntryManageTableConsts.MODIFIER.getName()));
                data.setCreateTime(rs.getTimestamp(EntryManageTableConsts.CREATE_TIME.getName()));
                data.setModifyTime(rs.getTimestamp(EntryManageTableConsts.MODIFY_TIME.getName()));
                data.setUploadPicture(rs.getString(EntryManageTableConsts.UPLOAD_PICTURE.getName()));
            }else{
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return data;
    }

    //修改
    public Entry modifyEntryManage(Entry entryManage)
            throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(MODIFY_ENTRYMANAGE_SQL);
            ps.setString(1, entryManage.getGuideType().name());
            ps.setString(2, entryManage.getName());
            ps.setString(3, entryManage.getDes());
            ps.setString(4, entryManage.getInnerUrl());
            ps.setString(5, entryManage.getOuterUrl());
            ps.setString(6, entryManage.getDownloadUrlForIos());
            ps.setString(7, entryManage.getDownloadUrlForAndroid());
            ps.setString(8, entryManage.getDownloadUrlForPc());
            ps.setString(9, entryManage.getCreator());
            ps.setTimestamp(10, new Timestamp(entryManage.getCreateTime().getTime()));
            ps.setString(11, entryManage.getModifier());
            ps.setTimestamp(12, new Timestamp(entryManage.getModifyTime().getTime()));
            ps.setDouble(13, entryManage.getSortOrder());
            ps.setString(14, entryManage.getUploadPicture());
            ps.setString(15, entryManage.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(null, ps, null);
        }
        return entryManage;
    }

    //查询列表所有
    public List<EntryTableModelWrap> queryAllEntryListManageList() throws
            Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        EntryTableModelWrap data = null;
        List<EntryTableModelWrap> list = null;
        Integer tol = 0;
        try {
            list = new ArrayList<>();
            ps = conn.prepareStatement(QUERY_ALL_ENTRYMANAGE_LIST_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                data = new EntryTableModelWrap();
                data.setGuideType(EntryTypeEnum.valueOf(rs.getString(EntryManageTableConsts.GUIDE_TYPE.getName())));
                final String count = rs.getString(COUNT);
                data.setCount(count);
                tol += Integer.valueOf(count);
                list.add(data);
            }
            EntryTableModelWrap wrap = new EntryTableModelWrap();
            wrap.setGuideType(null);
            wrap.setCount(tol.toString());
            list.add(0, wrap);//从第一个开始增加
        } catch (SQLException ex) {
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return list;
    }

    //查询所有
    public List<Entry> queryAllEntryManageList(int start, int pageSize) throws
            Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Entry data = null;
        List<Entry> list = null;
        try {
            list = new ArrayList<>();
            ps = conn.prepareStatement(QUERY_ALL_ENTRYMANAGE_SQL);
            ps.setInt(1, start);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();            
            while (rs.next()) {
                data = new Entry();   
                data.setId(rs.getString(EntryManageTableConsts.ID.getName()));
                data.setGuideType(EntryTypeEnum.valueOf(rs.getString(EntryManageTableConsts.GUIDE_TYPE.getName())));
                data.setName(rs.getString(EntryManageTableConsts.NAME.getName()));
                data.setDes(rs.getString(EntryManageTableConsts.DES.getName()));
                data.setInnerUrl(rs.getString(EntryManageTableConsts.INNER_URL.getName()));
                data.setOuterUrl(rs.getString(EntryManageTableConsts.OUTER_URL.getName()));
                data.setDownloadUrlForIos(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName()));
                data.setDownloadUrlForAndroid(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName()));
                data.setDownloadUrlForPc(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName()));
                data.setSortOrder(rs.getDouble(EntryManageTableConsts.SORT_ORDER.getName()));
                data.setCreator(rs.getString(EntryManageTableConsts.CREATOR.getName()));
                data.setModifier(rs.getString(EntryManageTableConsts.MODIFIER.getName()));             
                data.setCreateTime(rs.getTimestamp(EntryManageTableConsts.CREATE_TIME.getName()));
                data.setModifyTime(rs.getTimestamp(EntryManageTableConsts.MODIFY_TIME.getName()));
                data.setUploadPicture(rs.getString(EntryManageTableConsts.UPLOAD_PICTURE.getName()));
                list.add(data);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return list;
    }

    /**
     * 根据状态查询页面总数
     */
    public int queryEntryTotalCount(EntryTypeEnum type) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int totol = 0;
        try {
            ps = conn.prepareStatement(PAGALLCOUNT);
            int dexs = 0;
            if (type != null) {
                ps.setString(++dexs, type.name());
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                totol = rs.getInt(ALLPAGCOUNT);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return totol;
    }

    //查询不同资源的信息
    public List<Entry> queryAllEntryManageByType(EntryTypeEnum type, int start, int pageSize)
            throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Entry data = null;
        List<Entry> list = null;
        try {
            list = new ArrayList<>();
            ps = conn.prepareStatement(QUERY_ALL_ENTRYMANAGE_BYTYPE_SQL);
            ps.setString(1, type.name());
            ps.setInt(2, start);
            ps.setInt(3, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                data = new Entry();
                data.setId(rs.getString(EntryManageTableConsts.ID.getName()));
                data.setGuideType(EntryTypeEnum.valueOf(rs.getString(EntryManageTableConsts.GUIDE_TYPE.getName())));
                data.setName(rs.getString(EntryManageTableConsts.NAME.getName()));
                data.setDes(rs.getString(EntryManageTableConsts.DES.getName()));
                data.setInnerUrl(rs.getString(EntryManageTableConsts.INNER_URL.getName()));
                data.setOuterUrl(rs.getString(EntryManageTableConsts.OUTER_URL.getName()));
                data.setDownloadUrlForIos(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName()));
                data.setDownloadUrlForAndroid(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName()));
                data.setDownloadUrlForPc(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName()));
                data.setSortOrder(rs.getDouble(EntryManageTableConsts.SORT_ORDER.getName()));
                data.setCreator(rs.getString(EntryManageTableConsts.CREATOR.getName()));
                data.setModifier(rs.getString(EntryManageTableConsts.MODIFIER.getName()));
                data.setCreateTime(rs.getTimestamp(EntryManageTableConsts.CREATE_TIME.getName()));
                data.setModifyTime(rs.getTimestamp(EntryManageTableConsts.MODIFY_TIME.getName()));
                data.setUploadPicture(rs.getString(EntryManageTableConsts.UPLOAD_PICTURE.getName()));
                list.add(data);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return list;
    }
    //查询类别范围内数据
    
    public List<Entry> queryEntryByLimitAndType(EntryTypeEnum entryTypeEnum,int start, int end)
     throws Exception {
    PreparedStatement ps = null;
        ResultSet rs = null;
        Entry data = null;
        List<Entry> list = null;
        try {
            list = new ArrayList<>();
            ps = conn.prepareStatement(QUERY_ENTRY_BY_LIMIT_AND_TYPE_SQL);
            ps.setString(1, entryTypeEnum.name());
            ps.setInt(2, start);
            ps.setInt(3, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                data = new Entry();
                data.setId(rs.getString(EntryManageTableConsts.ID.getName()));
                data.setGuideType(EntryTypeEnum.valueOf(rs.getString(EntryManageTableConsts.GUIDE_TYPE.getName())));
                data.setName(rs.getString(EntryManageTableConsts.NAME.getName()));
                data.setDes(rs.getString(EntryManageTableConsts.DES.getName()));
                data.setInnerUrl(rs.getString(EntryManageTableConsts.INNER_URL.getName()));
                data.setOuterUrl(rs.getString(EntryManageTableConsts.OUTER_URL.getName()));
                data.setDownloadUrlForIos(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName()));
                data.setDownloadUrlForAndroid(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName()));
                data.setDownloadUrlForPc(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName()));
                data.setSortOrder(rs.getDouble(EntryManageTableConsts.SORT_ORDER.getName()));
                data.setCreator(rs.getString(EntryManageTableConsts.CREATOR.getName()));
                data.setModifier(rs.getString(EntryManageTableConsts.MODIFIER.getName()));
                data.setCreateTime(rs.getTimestamp(EntryManageTableConsts.CREATE_TIME.getName()));
                data.setModifyTime(rs.getTimestamp(EntryManageTableConsts.MODIFY_TIME.getName()));
                data.setUploadPicture(rs.getString(EntryManageTableConsts.UPLOAD_PICTURE.getName()));
                list.add(data);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
    
    return list;
    }
    
    //查询范围内数据
    public List<Entry> queryEntryByLimit(int start, int end)
     throws Exception {
    PreparedStatement ps = null;
        ResultSet rs = null;
        Entry data = null;
        List<Entry> list = null;
        try {
            list = new ArrayList<>();
            ps = conn.prepareStatement(QUERY_ENTRY_BY_LIMIT_SQL);
            ps.setInt(1, start);
            ps.setInt(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                data = new Entry();
                data.setId(rs.getString(EntryManageTableConsts.ID.getName()));
                data.setGuideType(EntryTypeEnum.valueOf(rs.getString(EntryManageTableConsts.GUIDE_TYPE.getName())));
                data.setName(rs.getString(EntryManageTableConsts.NAME.getName()));
                data.setDes(rs.getString(EntryManageTableConsts.DES.getName()));
                data.setInnerUrl(rs.getString(EntryManageTableConsts.INNER_URL.getName()));
                data.setOuterUrl(rs.getString(EntryManageTableConsts.OUTER_URL.getName()));
                data.setDownloadUrlForIos(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName()));
                data.setDownloadUrlForAndroid(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName()));
                data.setDownloadUrlForPc(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName()));
                data.setSortOrder(rs.getDouble(EntryManageTableConsts.SORT_ORDER.getName()));
                data.setCreator(rs.getString(EntryManageTableConsts.CREATOR.getName()));
                data.setModifier(rs.getString(EntryManageTableConsts.MODIFIER.getName()));
                data.setCreateTime(rs.getTimestamp(EntryManageTableConsts.CREATE_TIME.getName()));
                data.setModifyTime(rs.getTimestamp(EntryManageTableConsts.MODIFY_TIME.getName()));
                data.setUploadPicture(rs.getString(EntryManageTableConsts.UPLOAD_PICTURE.getName()));
                list.add(data);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
    
    return list;
    }

    //增加
    public Entry createEntryManage(Entry entryManage)
            throws
            Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(ADD_ENTRYMANAGE_SQL);
            ps.setString(1, entryManage.getGuideType().name());
            ps.setDouble(2, entryManage.getSortOrder());  //sort
            ps.setString(3, UUID.randomUUID().toString());//id 
            entryManage.setId(UUID.randomUUID().toString());
            ps.setString(4, entryManage.getName());
            ps.setString(5, entryManage.getDes());
            ps.setString(6, entryManage.getDownloadUrlForIos());
            ps.setString(7, entryManage.getDownloadUrlForAndroid());
            ps.setString(8, entryManage.getDownloadUrlForPc());
            ps.setString(9, entryManage.getInnerUrl());
            ps.setString(10, entryManage.getOuterUrl());
            //创建时间
            ps.setString(11, entryManage.getCreator());
            ps.setTimestamp(12, entryManage.getCreateTime() != null
                    ? new Timestamp(entryManage.getCreateTime().getTime())
                    : null);
            //修改时间
            ps.setString(13, entryManage.getModifier());
            ps.setTimestamp(14, entryManage.getModifyTime() != null
                    ? new Timestamp(entryManage.getModifyTime().getTime())
                    : null);
            ps.setString(15, entryManage.getUploadPicture());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(null, ps, null);
        }
        return entryManage;
    }

    //删除
    public void deleteEntryManage(String id) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(DELETE_ENTRYMANAGE_SQL);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("删除成功后                    " + id);
        } catch (SQLException ex) {
            conn.rollback();
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(null, ps, null);
        }
    }


    // 根据Entry查询单条
    public Entry queryEntryByEntry(Entry entryManage) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Entry data = null;
        try {
            ps = conn.prepareStatement(QUERY_ENTRYMANAGE_BYENTRY_SQL);
            ps.setString(1, entryManage.getName());
            ps.setString(2, entryManage.getInnerUrl());
            ps.setString(3, entryManage.getOuterUrl());
            ps.setString(4, entryManage.getDownloadUrlForAndroid());
            ps.setString(5, entryManage.getDownloadUrlForIos());
            ps.setString(6, entryManage.getDownloadUrlForPc());
            ps.setString(7, entryManage.getDes());
            ps.setString(8, entryManage.getGuideType().name());
            rs = ps.executeQuery();
            data = new Entry();
            data.setId(rs.getString(EntryManageTableConsts.ID.getName()));
            data.setGuideType(EntryTypeEnum.valueOf(rs.getString(EntryManageTableConsts.GUIDE_TYPE.getName())));
            data.setName(rs.getString(EntryManageTableConsts.NAME.getName()));
            data.setDes(rs.getString(EntryManageTableConsts.DES.getName()));
            data.setInnerUrl(rs.getString(EntryManageTableConsts.INNER_URL.getName()));
            data.setOuterUrl(rs.getString(EntryManageTableConsts.OUTER_URL.getName()));
            data.setDownloadUrlForIos(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName()));
            data.setDownloadUrlForAndroid(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName()));
            data.setDownloadUrlForPc(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName()));
            data.setSortOrder(rs.getDouble(EntryManageTableConsts.SORT_ORDER.getName()));
            data.setCreator(rs.getString(EntryManageTableConsts.CREATOR.getName()));
            data.setModifier(rs.getString(EntryManageTableConsts.MODIFIER.getName()));
            data.setCreateTime(rs.getTimestamp(EntryManageTableConsts.CREATE_TIME.getName()));
            data.setModifyTime(rs.getTimestamp(EntryManageTableConsts.MODIFY_TIME.getName()));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return data;

    }
//上移
     /**
     * 上移SQLUPDATE guideitem
    SET sort_order = CASE sort_order
        WHEN 2 THEN 1     2  3
        WHEN 1 THEN 2     3  4
    END
WHERE sort_order IN (1,2)
* 
     */
//    public void upEntryManage(Entry entry) throws SQLException {
//        PreparedStatement ps = null;
//        try {
//            ps = conn.prepareStatement(UP_OR_DOWN_ENTRY_SQL);
//            ps.setInt(1, entry.getSortOrder()-1);  //1
//            ps.setInt(2, entry.getSortOrder());    //2
//            ps.setInt(3, entry.getSortOrder());   //2
//            ps.setInt(4, entry.getSortOrder()-1);  
//            ps.setInt(5, entry.getSortOrder()-1);  
//            ps.setInt(6, entry.getSortOrder());    
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            throw ex;
//        } finally {
//            JDBCUtil.closeConnection(null, ps, null);
//        }     
//    }
 //下移   
//     public void downEntryManage(Entry entry) throws SQLException {
//        PreparedStatement ps = null;
//        try {
//            ps = conn.prepareStatement(UP_OR_DOWN_ENTRY_SQL);
//            ps.setInt(1, entry.getSortOrder());  
//            ps.setInt(2, entry.getSortOrder()+1);   
//            ps.setInt(3, entry.getSortOrder()+1);   
//            ps.setInt(4, entry.getSortOrder());  
//            ps.setInt(5, entry.getSortOrder());  
//            ps.setInt(6, entry.getSortOrder()+1);    
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            throw ex;
//        } finally {
//            JDBCUtil.closeConnection(null, ps, null);
//        }         }
    
    
//查询最大排序值
    public int getSortOrderMax() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int max = 0;
        try {
            ps = conn.prepareStatement(MAX_SORT_ORDER);
            rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getInt(MAXORDER);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return max;
    }
//查询最小排序值
    public int getSortOrderMin() {
       PreparedStatement ps = null;
        ResultSet rs = null;
        int min = 0;
        try {
            ps = conn.prepareStatement(MIN_SORT_ORDER);
            rs = ps.executeQuery();
            if (rs.next()) {
                min = rs.getInt(MINORDER);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return min;    }

    
    
//修改全部sortorder  ;
    public Entry updateSortOrder(Entry entry,int i) throws SQLException {
       PreparedStatement ps = null;
        try {
            String sql =" update  " + EntryManageTableConsts.TABLE_NAME.getName()
            +" set "+EntryManageTableConsts.SORT_ORDER.getName() +"=?"+" where "+EntryManageTableConsts.ID+"=? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, i);
            ps.setString(2, entry.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(null, ps, null);
        }
        return entry;
      }
//查询最大排序值
    public int getSortOrderMaxByType(Entry entryManage) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int max = 0;
        try {
            ps = conn.prepareStatement(MAX_SORT_ORDER_BY_TYPE);
            ps.setString(8, entryManage.getGuideType().name());
            rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getInt(MAXORDERBYTYPE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return max;


    }
//增加位置变化
    /**
     * 上,下移SQL      UPDATE guideitem
         SET sort_order = CASE sort_order
        WHEN max THEN s
        WHEN s THEN max  
         END
        WHERE sort_order IN (s,m)
    * 
     */
    public void updateSortOrderfindByUpOrDown(int sortOrderByUpOrDown, int addSortOrder) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(UP_OR_DOWN_ENTRY_SQL);
            ps.setInt(1, addSortOrder);  //1
            ps.setInt(2, sortOrderByUpOrDown);    //2
            ps.setInt(3, sortOrderByUpOrDown);   //2
            ps.setInt(4, addSortOrder);  
            ps.setInt(5, sortOrderByUpOrDown);  
            ps.setInt(6, addSortOrder);    
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(null, ps, null);
        }    
        
    }

    public void updateSortOrderNum(int change,int sortOrder) throws SQLException {
         PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(UPDATE_SORTORDER_SQL);
            ps.setInt(1, change);  //1
            ps.setInt(2, sortOrder);    //2
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            JDBCUtil.closeConnection(null, ps, null);
        }    
        
    }

    public List<Entry> queryAllEntryManageList() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Entry data = null;
        List<Entry> list = null;
        try {
            list = new ArrayList<>();
            ps = conn.prepareStatement(QUERY_ALL_ENTRYMANAGE_LOCAL_SQL);
            rs = ps.executeQuery();            
            while (rs.next()) {
                data = new Entry();   
                data.setId(rs.getString(EntryManageTableConsts.ID.getName()));
                data.setGuideType(EntryTypeEnum.valueOf(rs.getString(EntryManageTableConsts.GUIDE_TYPE.getName())));
                data.setName(rs.getString(EntryManageTableConsts.NAME.getName()));
                data.setDes(rs.getString(EntryManageTableConsts.DES.getName()));
                data.setInnerUrl(rs.getString(EntryManageTableConsts.INNER_URL.getName()));
                data.setOuterUrl(rs.getString(EntryManageTableConsts.OUTER_URL.getName()));
                data.setDownloadUrlForIos(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName()));
                data.setDownloadUrlForAndroid(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName()));
                data.setDownloadUrlForPc(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName()));
                data.setSortOrder(rs.getDouble(EntryManageTableConsts.SORT_ORDER.getName()));
                data.setCreator(rs.getString(EntryManageTableConsts.CREATOR.getName()));
                data.setModifier(rs.getString(EntryManageTableConsts.MODIFIER.getName()));             
                data.setCreateTime(rs.getTimestamp(EntryManageTableConsts.CREATE_TIME.getName()));
                data.setModifyTime(rs.getTimestamp(EntryManageTableConsts.MODIFY_TIME.getName()));
                data.setUploadPicture(rs.getString(EntryManageTableConsts.UPLOAD_PICTURE.getName()));
                list.add(data);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return list;
    }

    public List<Entry> queryEntryManageLocalByType(EntryTypeEnum state) throws SQLException {
       PreparedStatement ps = null;
        ResultSet rs = null;
        Entry data = null;
        List<Entry> list = null;
        try {
            list = new ArrayList<>();
            ps = conn.prepareStatement(QUERY_ALL_ENTRYMANAGE_LOCAL_BYTYPE_SQL);
            ps.setString(1, state.name());
            rs = ps.executeQuery();
            while (rs.next()) {
                data = new Entry();
                data.setId(rs.getString(EntryManageTableConsts.ID.getName()));
                data.setGuideType(EntryTypeEnum.valueOf(rs.getString(EntryManageTableConsts.GUIDE_TYPE.getName())));
                data.setName(rs.getString(EntryManageTableConsts.NAME.getName()));
                data.setDes(rs.getString(EntryManageTableConsts.DES.getName()));
                data.setInnerUrl(rs.getString(EntryManageTableConsts.INNER_URL.getName()));
                data.setOuterUrl(rs.getString(EntryManageTableConsts.OUTER_URL.getName()));
                data.setDownloadUrlForIos(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORIOS.getName()));
                data.setDownloadUrlForAndroid(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORANDROID.getName()));
                data.setDownloadUrlForPc(rs.getString(EntryManageTableConsts.DOWNLOAD_URL_FORPC.getName()));
                data.setSortOrder(rs.getDouble(EntryManageTableConsts.SORT_ORDER.getName()));
                data.setCreator(rs.getString(EntryManageTableConsts.CREATOR.getName()));
                data.setModifier(rs.getString(EntryManageTableConsts.MODIFIER.getName()));
                data.setCreateTime(rs.getTimestamp(EntryManageTableConsts.CREATE_TIME.getName()));
                data.setModifyTime(rs.getTimestamp(EntryManageTableConsts.MODIFY_TIME.getName()));
                data.setUploadPicture(rs.getString(EntryManageTableConsts.UPLOAD_PICTURE.getName()));
                list.add(data);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            JDBCUtil.closeConnection(rs, ps, null);
        }
        return list;
    }


   
}
