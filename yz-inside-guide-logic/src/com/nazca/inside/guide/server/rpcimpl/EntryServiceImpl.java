/*
 * EntryServiceImpl.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-27 16:01:16
 */
package com.nazca.inside.guide.server.rpcimpl;

import com.nazca.inside.guide.common.consts.InsideGuideErrorCode;
import com.nazca.inside.guide.common.enums.EntryTypeEnum;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.inside.guide.common.model.EntryTableModelWrap;
import com.nazca.inside.guide.common.rpc.EntryService;
import com.nazca.inside.guide.common.util.ErrorCodeFormater;
import com.nazca.inside.guide.server.dao.EntryManageDAO;
import com.nazca.inside.guide.server.util.ConnectionFactory;
import com.nazca.inside.guide.server.util.ImgServerConfig;
import com.nazca.inside.guide.server.util.PersistenceUtil;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.io.httprpc.HttpRPCInjection;
import com.nazca.sql.JDBCUtil;
import com.nazca.sql.PageResult;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author pengruirui
 */
public class EntryServiceImpl extends AbstractService4JPA implements EntryService {
    //创建dao的实例对象
    private static final Log log = LogFactory.getLog(EntryServiceImpl.class);
    @HttpRPCInjection
    private HttpServletRequest request;

    @Override
    public PageResult<Entry> queryAllEntryManage(int curPage, int pageSize)
            throws HttpRPCException {
        PageResult<Entry> result = null;
        List<Entry> list = null;//第一边
        List<Entry> twoList = null;//第二边
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);
            int totalCount = entryManageDAO.queryAllTotalCount();
            curPage= PageResult.recalculateCurPage(totalCount, curPage, pageSize);               
            int start = PageResult.getFromIndex(curPage, pageSize);
            list = entryManageDAO.queryAllEntryManageList(start, pageSize); 
              if (list != null) {
                   result = new PageResult<>(totalCount, curPage, pageSize, list);  
                }             
              else {
                   throw new HttpRPCException("参数类型未接受", InsideGuideErrorCode.ARGUMENTS_ERROR);
               }
            conn.commit();//启动
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("query enrty failed!", ex);
            throw new HttpRPCException("查询未成功", InsideGuideErrorCode.DB_ERROR);
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }
        return result;
    }

    @Override
    public List<EntryTableModelWrap> queryAllEntryListManage() throws
            HttpRPCException {
        Connection conn = null;
        List<EntryTableModelWrap> list = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);
            list = entryManageDAO.queryAllEntryListManageList();
            if (list == null) {
                throw new HttpRPCException("对象不存在", InsideGuideErrorCode.OBJECT_NOT_EXIST);
            }
            conn.commit();//启动
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("query team failed!", ex);
//            throw new HttpRPCException(ErrorCodeFormater.explainErrorCode(InsideGuideErrorCode.DB_ERROR), InsideGuideErrorCode.DB_ERROR);
              throw new HttpRPCException("查询失败", InsideGuideErrorCode.DB_ERROR);
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }
        return list;
    }

    @Override
    public Entry createEntryManage(Entry entryManage,boolean setSelectRadio,boolean isSelectType) throws HttpRPCException {
//        int sortOrderByUpOrDown = 0;
        Connection conn = null;
        Entry centryManage = null;
        Entry result=null;
        List<Entry> list = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);           
//                int max = entryManageDAO.getSortOrderMax();               
                entryManage.setCreator(getCurrentUserID(request));
                entryManage.setCreateTime(new Date()); 
                result=entryManageDAO.createEntryManage(entryManage);    
                conn.commit();//启动dhtr           
        } catch (Exception ex) {
            ex.printStackTrace();
            dealException(conn, ex);
            if (centryManage == null) {
                new HttpRPCException("未添加成功", InsideGuideErrorCode.OBJECT_EXIST);
            }
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }
        System.out.println("添加结果为"+result);
        return result;
    }

    private void ModifySortFor(List<Entry> list, EntryManageDAO entryManageDAO, int number)
            throws SQLException {
        for (int i = 0; i <list.size(); i++) {
            entryManageDAO.updateSortOrder(list.get(i), number);//修改sortorder
            number++;
        }
    }

    @Override
    public Entry modifyEntryManage(Entry entryManage, boolean setSelectRadio,boolean isSelectType)
            throws HttpRPCException {
                int changeSortOrder = 0;
        Connection conn = null;
        Entry result = null;
        Entry en=null;
        List<Entry> list = null;
        if (entryManage == null || entryManage.getId() == null) {
            throw new HttpRPCException("参数错误", InsideGuideErrorCode.OBJECT_NOT_EXIST);
        }
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);
            Entry find = entryManageDAO.queryEntryById(entryManage.getId());
            if (find == null) {
                throw new HttpRPCException("对象不存在", InsideGuideErrorCode.OBJECT_NOT_EXIST);
            }
            entryManage.setModifier(getCurrentUserID(request));
            entryManage.setModifyTime(new Date());
            result=entryManageDAO.modifyEntryManage(entryManage);
           
        } catch (Exception ex) {
            ex.printStackTrace();
            dealException(conn, ex);
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }  
        return result;
    }




    @Override
    public void deleteEntryManage(String id) throws HttpRPCException {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);
            Entry find = entryManageDAO.queryEntryById(id);
            if (find == null) {
                throw new HttpRPCException("对象不存在", InsideGuideErrorCode.OBJECT_NOT_EXIST);
            }
            entryManageDAO.deleteEntryManage(id);
            conn.commit();//启动
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("delete team failed!", ex);
            throw new HttpRPCException("删除失败", InsideGuideErrorCode.DB_ERROR);
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }
    }

    @Override
    public PageResult<Entry> queryAllEntryManageByType(EntryTypeEnum type, int curPage, int pageSize)
            throws HttpRPCException {
        PageResult<Entry> result = null;
        List<Entry> list = null;
        List<Entry> twoList = null;//第二边
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);
            if (type != null) {
                int totalCount = entryManageDAO.queryEntryTotalCount(type);
                curPage= PageResult.recalculateCurPage(totalCount, curPage, pageSize);                      
                int start = PageResult.getFromIndex(curPage, pageSize);
                list= entryManageDAO.queryAllEntryManageByType(type, start, pageSize);                      
                if (list != null) {
                   result = new PageResult<>(totalCount, curPage, pageSize, list);  
                }             
              } else {
                   throw new HttpRPCException("参数类型未接受", InsideGuideErrorCode.ARGUMENTS_ERROR);
               }
            conn.commit();//启动
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("查询未成功!", ex);
            throw new HttpRPCException("查询失败", InsideGuideErrorCode.DB_ERROR);
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }
        return result;
    }

    @Override
    public void upEntryManage(Entry entry) throws HttpRPCException {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);
            Entry find = entryManageDAO.queryEntryById(entry.getId());
            if (find == null) {
                throw new HttpRPCException("对象不存在", InsideGuideErrorCode.OBJECT_NOT_EXIST);
            }
            entry.setModifier(getCurrentUserID(request));
            entry.setModifyTime(new Date());
            //update 
            entryManageDAO.modifyEntryManage(entry);
            conn.commit();//启动

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("up enrty failed!", ex);
            throw new HttpRPCException("上移失败", InsideGuideErrorCode.DB_ERROR);
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }
    }

    @Override
    public void downEntryManage(Entry entry) throws HttpRPCException {
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);
            Entry find = entryManageDAO.queryEntryById(entry.getId());
            if (find == null) {
                throw new HttpRPCException("对象不存在", InsideGuideErrorCode.OBJECT_NOT_EXIST);
            }

            int sortOrderMax = entryManageDAO.getSortOrderMax();
            if (entry.getSortOrder() == sortOrderMax) {
                throw new HttpRPCException("该对象为最后一条数据，请重新选择", InsideGuideErrorCode.OBJECT_MODIFY_CONFLICT);
            }
            //update 
            entry.setModifier(getCurrentUserID(request));
            entry.setModifyTime(new Date());
            entryManageDAO.modifyEntryManage(entry);
            conn.commit();//启动
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("up enrty failed!", ex);
            throw new HttpRPCException("下移失败", InsideGuideErrorCode.DB_ERROR);
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }
    }
    
       


    @Override
    public Entry uplaodPicture(String fileName,InputStream is,String attachmentDirType) {
        String fileUrl=null;
        Entry entry =new Entry();
          if (null == is) {
              try {                 
                  throw new HttpRPCException("文件不存在",  InsideGuideErrorCode.FILE_NO_EXIT);
              } catch (HttpRPCException ex) {
                  ex.printStackTrace();
              }
        }
        if (null == fileName) {
              try {
                  throw new HttpRPCException("文件名为空",  InsideGuideErrorCode.FILE_NAME_NO);
              } catch (HttpRPCException ex) {
                  ex.printStackTrace();
              }   
        }
        try {   
            String fileExtention = FilenameUtils.getExtension(fileName);
            fileName = UUID.randomUUID() + "." + fileExtention;
            File f = PersistenceUtil.getTemplateForDownloadFile(fileName, attachmentDirType);
            byte[] readByte = new byte[1024];
            FileOutputStream os = new FileOutputStream(f);
            int length = is.read(readByte);
            while (length > 0) {
                os.write(readByte, 0, length);
                length = is.read(readByte);
            }
            os.flush();
            os.close();
            is.close();
//            fileUrl = ImgServerConfig.getConfig().getValue(ImgServerConfig.DEST_PATH) + attachmentDirType + File.separator + fileName; 
          fileUrl =attachmentDirType + File.separator + fileName; 
          entry.setUploadPicture(fileUrl);
        } catch (IOException ex) {
            ex.printStackTrace();
              try {
                  throw new HttpRPCException("应用服务器内部错误", InsideGuideErrorCode.SERVER_ERROR);
              } catch (HttpRPCException ex1) {
                  ex1.printStackTrace();
              }
        } catch (Exception ex) {
            ex.printStackTrace();
              try {
                  throw new HttpRPCException("应用服务器内部错误",InsideGuideErrorCode.SERVER_ERROR);
              } catch (HttpRPCException ex1) {
                  ex1.printStackTrace();
              }
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
         return entry;
    }

    @Override
    public InputStream downloadPicture(String fileUrl) {
        System.out.println(fileUrl+"+==============================");
           //TODO 读配置文件
        if (null == fileUrl) {
            try {
                throw new HttpRPCException("对不起，你下载的文件已经不存在", InsideGuideErrorCode.FILE_NO_EXIT);
            } catch (HttpRPCException ex) {
                ex.printStackTrace();
            
            }
        } 
        try {
            File f = new File(ImgServerConfig.getConfig().getValue(ImgServerConfig.DEST_PATH) +fileUrl);
            return new FileInputStream(f);          
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
               try {
                  throw new HttpRPCException("服务器图片上传地址有误", InsideGuideErrorCode.SERVER_ERROR);
              } catch (HttpRPCException ex1) {
                  ex1.printStackTrace();
              }
            return null;
        }
     
    }

    @Override
    public List<Entry> queryAllEntryLocalManage() {
        List<Entry> result = null;
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);
            result = entryManageDAO.queryAllEntryManageList();    
            conn.commit();//启动
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("query enrty failed!", ex);
            try {
                throw new HttpRPCException("查询未成功", InsideGuideErrorCode.DB_ERROR);
            } catch (HttpRPCException ex1) {
                ex1.printStackTrace();
            }
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }
        return result;
    }

    @Override
    public List<Entry> queryEntryManageLocalByType(EntryTypeEnum state) {
        List<Entry> result = null;
        Connection conn = null;
        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);//先不开始启动
            EntryManageDAO entryManageDAO = new EntryManageDAO(conn);
            if (state != null) {
                result= entryManageDAO.queryEntryManageLocalByType(state);                                                 
              } else {
                   throw new HttpRPCException("参数类型未接受", InsideGuideErrorCode.ARGUMENTS_ERROR);
               }
            conn.commit();//启动
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("查询未成功!", ex);
            try {
                throw new HttpRPCException("查询失败", InsideGuideErrorCode.DB_ERROR);
            } catch (HttpRPCException ex1) {
                ex1.printStackTrace();
            }
        } finally {
            JDBCUtil.closeConnection(null, null, conn);
        }
        return result;
    }
    
}