/*
 * EntryService.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-27 15:59:59
 */
package com.nazca.inside.guide.common.rpc;

import com.nazca.inside.guide.common.enums.EntryTypeEnum;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.inside.guide.common.model.EntryTableModelWrap;
import com.nazca.io.httprpc.HttpRPCException;
import com.nazca.io.httprpc.HttpRPCSessionTokenRequired;
import com.nazca.io.httprpc.InvokingMethod;
import com.nazca.io.httprpc.ServerInvoking;
import com.nazca.sql.PageResult;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 *
 * @author pengruirui
 */
@ServerInvoking(method = InvokingMethod.SERVICE_MAPPING, identifier = "com.nazca.inside.guide.server.rpcimpl.EntryServiceImpl")
public interface EntryService {
      /**
     * 获取团队信息
     *
     * @param curPage
     * @param pageSize
     * @return
     * @throws HttpRPCException
     */
    @HttpRPCSessionTokenRequired
    PageResult<Entry> queryAllEntryManage(int curPage, int pageSize) throws HttpRPCException;
    
    
          /**
     * 获取团队列表信息
     *
     * @return
     * @throws HttpRPCException
     */
    @HttpRPCSessionTokenRequired
    List<EntryTableModelWrap> queryAllEntryListManage() throws HttpRPCException;

    /**
     * 添加团队信息
     *
     * @param entryManage
     * @param setSelectRadio
     * @param isSelectType
     * @param team
     * @return
     * @throws HttpRPCException
     */
    @HttpRPCSessionTokenRequired
    Entry createEntryManage(Entry entryManage,boolean setSelectRadio,boolean isSelectType) throws HttpRPCException;

    /**
     * 修改团队信息
     *
     * @param entryManage
     * @param setSelectRadio
     * @param isSelectType
     * @param team
     * @return
     * @throws HttpRPCException
     */
    @HttpRPCSessionTokenRequired
    Entry modifyEntryManage(Entry entryManage,boolean setSelectRadio,boolean isSelectType) throws HttpRPCException;

    /**
     * 删除团队信息
     *
     * @param entry
     * @param id
     * @param teamId
     * @return
     * @throws HttpRPCException
     */
    @HttpRPCSessionTokenRequired
    void deleteEntryManage(String id) throws HttpRPCException;

 

   

    /**
     * 查询不同资源的信息
     *
     * @param type
     * @param curPage
     * @param pageSize
     * @param memberId
     * @return
     * @throws HttpRPCException
     */
    @HttpRPCSessionTokenRequired
    PageResult<Entry> queryAllEntryManageByType(EntryTypeEnum type, int curPage, int pageSize) throws HttpRPCException;

    /**
     *
     * @param entry
     * @param id
     * @throws HttpRPCException
     */
    @HttpRPCSessionTokenRequired
    void upEntryManage(Entry entry) throws HttpRPCException;

    /**
     *
     * @param entry
     * @throws HttpRPCException
     */
    @HttpRPCSessionTokenRequired
    void downEntryManage(Entry entry) throws HttpRPCException;
    
    /**
     *
     * @param fileName
     * @param is
     * @param attachmentDirType
     * @return
     */
    @HttpRPCSessionTokenRequired
    Entry uplaodPicture(String fileName,InputStream is,String attachmentDirType);

    /**
     *
     * @param fileUrl
     * @return
     */
    @HttpRPCSessionTokenRequired
    InputStream downloadPicture(String fileUrl);

    /**
     *
     * @return
     */
    @HttpRPCSessionTokenRequired
    List<Entry> queryAllEntryLocalManage();

    /**
     *
     * @param state
     * @return
     */
    @HttpRPCSessionTokenRequired
    public List<Entry> queryEntryManageLocalByType(EntryTypeEnum state);


    
}
