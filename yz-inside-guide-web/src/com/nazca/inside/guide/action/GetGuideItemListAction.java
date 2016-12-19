/*
 * GetGuideItemListAction.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-05-17 02:42:37
 */
package com.nazca.inside.guide.action;

import com.nazca.inside.guide.action.common.AbstractAction;
import com.nazca.inside.guide.enums.GuideType;
import com.nazca.inside.guide.model.GuideItem;
import com.nazca.inside.guide.server.util.ImgServerConfig;
import com.nazca.inside.guide.service.GuideItemService;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
/**
 * 获得GuideItemList的action
 *
 * @author 赵洪坤 <zhaohongkun@yzhtech.com>
 */
public class GetGuideItemListAction extends AbstractAction {

    private List<GuideItem> guideItemsWebs;
    private List<GuideItem> guideItemsSyss;
    private List<GuideItem> guideItemsDocs;
     /**
     * 在list方法中获得通用服务网站、工具、常用文档的查询List
     *
     * @return 返回action识别的字符串success
     * @throws NamingException
     */
    public String list() throws NamingException {
        guideItemsWebs = guideItemService.getGuideItemListByguideType(GuideType.web.name());
        guideItemsSyss = guideItemService.getGuideItemListByguideType(GuideType.sys.name());
        guideItemsDocs = guideItemService.getGuideItemListByguideType(GuideType.doc.name());
        return SUCCESS;
    }
    public List<GuideItem> getGuideItemsSyss() {
        return guideItemsSyss;
    }

    public void setGuideItemsSyss(List<GuideItem> guideItemsSyss) {
        this.guideItemsSyss = guideItemsSyss;
    }

    public List<GuideItem> getGuideItemsDocs() {
        return guideItemsDocs;
    }

    public void setGuideItemsDocs(List<GuideItem> guideItemsDocs) {
        this.guideItemsDocs = guideItemsDocs;
    }

    public List<GuideItem> getGuideItemsWebs() {
        return guideItemsWebs;
    }

    public void setguideItemsWebs(List<GuideItem> guideItemsWebs) {
        this.guideItemsWebs = guideItemsWebs;
    }

    public List<GuideItem> getguideItemsSyss() {
        return guideItemsSyss;
    }

    public void setguideItemsSyss(List<GuideItem> guideItemsSyss) {
        this.guideItemsSyss = guideItemsSyss;
    }

    public List<GuideItem> getguideItemsDocs() {
        return guideItemsDocs;
    }

    public void setguideItemsDocs(List<GuideItem> guideItemsDocs) {
        this.guideItemsDocs = guideItemsDocs;
    }

    public GuideItemService getGuideItemService() {
        return guideItemService;
    }

    public void setGuideItemService(GuideItemService guideItemService) {
        this.guideItemService = guideItemService;
    }

    private GuideItemService guideItemService = new GuideItemService();

    public void showPicture(){
        String uploadPicture = ServletActionContext.getRequest().getParameter("uploadPicture");//前台传来的存图片路径
        HttpServletResponse response = ServletActionContext.getResponse();//struts2获取response  
        if(uploadPicture != null && !"".equals(uploadPicture)){  
            FileInputStream is;  
            try {  
                File f = new File(ImgServerConfig.getConfig().getValue(ImgServerConfig.DEST_PATH) +uploadPicture);
                is = new FileInputStream(f);  
                int i = is.available(); // 得到文件大小  
                byte data[] = new byte[i];  
                is.read(data); // 读数据  
                is.close();  
                response.setContentType("image/*"); // 设置返回的文件类型  
                OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
                toClient.write(data); // 输出数据  
                toClient.close();  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }    

    @Override
    public String execute() throws Exception {
        return super.execute(); //To change body of generated methods, choose Tools | Templates.
    }
    

}
