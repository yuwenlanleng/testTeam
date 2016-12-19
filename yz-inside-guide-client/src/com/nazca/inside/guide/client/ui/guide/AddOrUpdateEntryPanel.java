/*
 * AddOrUpdateEntryPanel.java
 * 
 * Copyright(c) 2007-2016 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2016-09-28 10:21:21
 */
package com.nazca.inside.guide.client.ui.guide;

import com.nazca.inside.guide.client.Renderer.LocationComboxRenderer;
import com.nazca.inside.guide.client.agent.entry.CreateEntryAgent;
import com.nazca.inside.guide.client.agent.entry.DownloadPictureAgent;
import com.nazca.inside.guide.client.agent.entry.ModifyEntryAgent;
import com.nazca.inside.guide.client.agent.entry.QueryEntrytAgent;
import com.nazca.inside.guide.client.agent.entry.UploadPictureAgent;
import com.nazca.inside.guide.client.listener.AgentListener;
import com.nazca.inside.guide.client.ui.OKCancelPanelListener;
import com.nazca.inside.guide.client.util.Jk2cMmgtResourceUtil;
import com.nazca.inside.guide.common.enums.EntryTypeEnum;
import com.nazca.inside.guide.common.model.Entry;
import com.nazca.inside.guide.common.table.consts.EntryManageTableConsts;
import com.nazca.ui.NComponentStyleTool;
import com.nazca.ui.NInternalDiag;
import com.nazca.ui.NTextBox;
import com.nazca.ui.NTextHinter;
import com.nazca.ui.NWaitingPanel;
import com.nazca.ui.model.SimpleObjectListModel;
import com.nazca.ui.util.CardLayoutWrapper;
import com.nazca.util.StringUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author pengruirui
 */
public class AddOrUpdateEntryPanel extends javax.swing.JPanel {
    private boolean addMode;
    private boolean isSelectType;
    private Entry entry;
    private Entry curEntry;
    private EntryTypeEnum curEntryTypeEnum;
    private EntryTypeEnum addEntryTypeEnum;
    private List<Entry> localEntry;
    private CreateEntryAgent creatEntryAgent;
    private ModifyEntryAgent modifyEntryAgent;
    private QueryEntrytAgent queryEntryAgent;
    private UploadPictureAgent uploadPictureAgent;
    private DownloadPictureAgent downloadPictureAgent;
    private String uplaodPictureServer;
    private InputStream downloadPictureServer;
    private CardLayoutWrapper waitCard = null;
    private long manPictureSeq = 0;
    private long downManPictureSeq = 0;
    private  BufferedImage imgs = null;   //文件
    private  BufferedImage imgsModify = null;
    private  BufferedImage imgsOld = null;
    YzInsidePanel yzInsidePanel = null;
    private List<OKCancelPanelListener> listeners = new CopyOnWriteArrayList<OKCancelPanelListener>();


    /**
     * Creates new form AddOrUpdateEntryPanel
     * "/com/nazca/inside/guide/res/password-auth-32.png"
     */
    public AddOrUpdateEntryPanel() {
        initComponents();
        initModel();
        initAgentAndListener();
//        desTextPane.setToolTipText("请输入64个字以内");
//        desTextPane.setAlignmentY(0);
//        desTextPane.setBounds(0, 0, 0, 0);
//        NTextHinter.attach("请输入64个字以内", desTextPane);
       
        NTextHinter.attach("网址格式参照  http://www.123.com", innerUrlTextField);
        NTextHinter.attach("请输入15个字以内", nameTextField);
        waitCard = new CardLayoutWrapper(picPanel);
        try {
            imgsOld=Jk2cMmgtResourceUtil.readImage("manage-pic.png");
            manPicPane.setImage(imgsOld);
            waitCard.show(CardLayoutWrapper.CONTENT);
            bigBtu.setEnabled(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        upRadioButton.setSelected(true);   
        
    }

    public void setIsAdd(boolean addMode) {
        this.addMode = addMode;
    }

    public void isSelectList(boolean isSelectType) {
        this.isSelectType = isSelectType;
    }

    public void setType(EntryTypeEnum curEntryTypeEnum) {
        this.curEntryTypeEnum = curEntryTypeEnum;
    }

    private void initModel() {
        // 类别下拉框
        SimpleObjectListModel<EntryTypeEnum> model
                = new SimpleObjectListModel<>();
        model.add(EntryTypeEnum.select);
        model.add(EntryTypeEnum.web);
        model.add(EntryTypeEnum.sys);
        model.add(EntryTypeEnum.doc);
        typeCom.setModel(model);
        typeCom.setSelectedIndex(0);

    }
    AgentListener<Entry> addOrUpdateEntryAgentListener =new AgentListener<Entry>() {
         
        @Override
        public void onStarted(long seq) {
            if (addMode) {
                oKCancelPanel.gotoWaitMode("正在添加...");
            } else {
                oKCancelPanel.gotoWaitMode("正在修改...");
            }
        }

        @Override
        public void onSucceeded(final Entry result, long seq) {
            if (addMode) {
                oKCancelPanel.gotoSuccessMode("添加成功");
            } else {
                oKCancelPanel.gotoSuccessMode("修改成功");
            }
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    NInternalDiag<Entry, JComponent> diag
                            = NInternalDiag.findNInternalDiag(JPanel1);
//                    diag.hideDiag(result);
                      diag.hideDiag(result);
                }
            });
        }
        @Override
        public void onFailed(String errMsg, int errCode, long seq) {
            oKCancelPanel.gotoErrorMode(errMsg, errCode);
        }
    };

    AgentListener<Entry> upLoadPictureAgentListener
            = new AgentListener<Entry>() {
        @Override
        public void onStarted(long seq) {
            manPictureSeq=seq;
            nWaitingPanel2.showWaitingMode("数据加载中，请稍后...");
            nWaitingPanel2.setIndeterminate(true);
            waitCard.show(CardLayoutWrapper.WAIT);
        }
        @Override
        public void onSucceeded(Entry result, long seq) {
            nWaitingPanel2.setIndeterminate(false);//关闭等待面板
            if(manPictureSeq==seq){
            waitCard.show(CardLayoutWrapper.CONTENT);
            uplaodPictureServer = result.getUploadPicture(); 
            bigBtu.setEnabled(true);
             }
        }

        @Override
        public void onFailed(String errMsg, int errCode, long seq) {
            if(seq==manPictureSeq){
            waitCard.show(CardLayoutWrapper.WAIT);
            nWaitingPanel2.showMsgMode(errMsg, errCode, NWaitingPanel.MSG_MODE_ERROR);
             bigBtu.setEnabled(false);
            }
        }

    };

    AgentListener<InputStream> downLoadPictureAgentListener
            = new AgentListener<InputStream>() {
        @Override
        public void onStarted(long seq) {
            downManPictureSeq=seq;     
            nWaitingPanel2.showWaitingMode("数据加载中，请稍后...");
            nWaitingPanel2.setIndeterminate(true);
            waitCard.show(CardLayoutWrapper.WAIT);
        }
        @Override
        public void onSucceeded(InputStream result, long seq) {
            nWaitingPanel2.setIndeterminate(false);//关闭等待面板
            if(downManPictureSeq==seq){              
                try {
                    waitCard.show(CardLayoutWrapper.CONTENT);
                    imgsModify=ImageIO.read(result); 
                    manPicPane.setImage(imgsModify); 
                    bigBtu.setEnabled(true);
                    result.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }                                                         
            }
        }

        @Override
        public void onFailed(String errMsg, int errCode, long seq) {
            if(seq==downManPictureSeq){ 
            waitCard.show(CardLayoutWrapper.WAIT);         
            nWaitingPanel2.showMsgMode("upload",1, NWaitingPanel.MSG_MODE_ERROR);
            bigBtu.setEnabled(false);
            }
        }
    };
    private boolean isModifyType() {
        if (curEntryTypeEnum != (EntryTypeEnum) typeCom.getSelectedItem()
                && curEntryTypeEnum != null) {
            oKCancelPanel.gotoErrorMode("请在全部中修改类别");
            NComponentStyleTool.errorStyle(typeCom);
            return false;
        }
        return true;
    }
    private boolean isaddType() {
        if (addEntryTypeEnum != (EntryTypeEnum) typeCom.getSelectedItem()
                && addEntryTypeEnum != null) {
            oKCancelPanel.gotoErrorMode("请在全部中修改类别");
            NComponentStyleTool.errorStyle(typeCom);
            return false;
        }
        return true;
    }
    private boolean isFormValid() {
        //名称验证
        if (StringUtil.isEmpty(nameTextField.getText().trim())) {
            oKCancelPanel.gotoErrorMode("名称不能为空!");
            NComponentStyleTool.errorStyle(nameTextField);
            return false;
        } 
        else if (nameTextField.getText().length()>15){
            oKCancelPanel.gotoErrorMode("名称长度不能超过15个字符！");
            NComponentStyleTool.errorStyle(nameTextField);
            return false;
        }
        else if (nameTextField.getText().length()
                > EntryManageTableConsts.NAME.getLength()) {
            oKCancelPanel.gotoErrorMode("名称长度超出范围!");
            NComponentStyleTool.errorStyle(nameTextField);
            return false;
        } else if (isEffect(nameTextField.getText()) == false) {
            oKCancelPanel.gotoErrorMode("名称不合法!");
            NComponentStyleTool.errorStyle(nameTextField);
            return false;
        } else {
            NComponentStyleTool.normalStyle(nameTextField);
        }
        //类别验证
        if (typeCom.getSelectedItem() == EntryTypeEnum.select) {
            oKCancelPanel.gotoErrorMode("请选择类别!");
            NComponentStyleTool.errorStyle(typeCom);
            return false;
        }
        
        //内网入口验证
        if (StringUtil.isEmpty(innerUrlTextField.getText().trim())) {
            oKCancelPanel.gotoErrorMode("内网入口不能为空!");
            NComponentStyleTool.errorStyle(innerUrlTextField);
            return false;
        } 
        else if(isPass(innerUrlTextField.getText())==false){
            oKCancelPanel.gotoErrorMode("内网入口格式不正确，请重新填写！");
            NComponentStyleTool.errorStyle(innerUrlTextField);
            return false;
        }
         else if (innerUrlTextField.getText().length()>150){
            oKCancelPanel.gotoErrorMode("内网入口长度超出范围!");
            NComponentStyleTool.errorStyle(innerUrlTextField);
            return false;
        }
        else {
            NComponentStyleTool.normalStyle(innerUrlTextField);
        }
        
        //外网入口验证
        if(!StringUtil.isEmpty(outerUrlTextField.getText().trim())){
        if(isPass(outerUrlTextField.getText())==false){
            oKCancelPanel.gotoErrorMode("外网入口格式不正确，请重新填写!");
            NComponentStyleTool.errorStyle(outerUrlTextField);
            return false;
        }
         else if (outerUrlTextField.getText().length()>150){
            oKCancelPanel.gotoErrorMode("外网入口长度超出范围!");
            NComponentStyleTool.errorStyle(outerUrlTextField);
            return false;
        }
        else {
            NComponentStyleTool.normalStyle(outerUrlTextField);
        }
        }
         //URL下载验证
         if (!StringUtil.isEmpty(downloadUrlForiosTextField.getText().trim())) {       
         if(isPass(downloadUrlForiosTextField.getText())==false){
            oKCancelPanel.gotoErrorMode("URL下载格式不正确，请重新填写!");
            NComponentStyleTool.errorStyle(downloadUrlForiosTextField);
            return false;
        }
         else if (downloadUrlForiosTextField.getText().length()>150){
            oKCancelPanel.gotoErrorMode("URL下载长度超出范围!");
            NComponentStyleTool.errorStyle(downloadUrlForiosTextField);
            return false;
        }
        else {
            NComponentStyleTool.normalStyle(downloadUrlForiosTextField);
        }
           } 
        //android下载验证
        if(!StringUtil.isEmpty(downloadUrlForandroidTextField.getText().trim())){      
        if(isPass(downloadUrlForandroidTextField.getText())==false){
            oKCancelPanel.gotoErrorMode("android下载格式不正确，请重新填写!");
            NComponentStyleTool.errorStyle(downloadUrlForandroidTextField);
            return false;
        }
         else if (downloadUrlForandroidTextField.getText().length()>150){
            oKCancelPanel.gotoErrorMode("android下载长度超出范围!");
            NComponentStyleTool.errorStyle(downloadUrlForandroidTextField);
            return false;
        }
        else {
            NComponentStyleTool.normalStyle(downloadUrlForandroidTextField);
        }
        }     
         //IOS下载验证
         if(!StringUtil.isEmpty(downloadUrlForpcTextField.getText().trim())){   
        if(isPass(downloadUrlForpcTextField.getText())==false){
            oKCancelPanel.gotoErrorMode("IOS下载格式不正确，请重新填写!");
            NComponentStyleTool.errorStyle(downloadUrlForpcTextField);
            return false;
        }
         else if (downloadUrlForpcTextField.getText().length()>150){
            oKCancelPanel.gotoErrorMode("IOS下载长度超出范围!");
            NComponentStyleTool.errorStyle(downloadUrlForpcTextField);
            return false;
        }
        else {
            NComponentStyleTool.normalStyle(downloadUrlForpcTextField);
        }     
         }
           //描述验证
         if(!StringUtil.isEmpty(desTextPane.getText().trim())){
     
         if (desTextPane.getText().length()>64){
            oKCancelPanel.gotoErrorMode("描述长度不能超过64个字符");
            NComponentStyleTool.errorStyle(desTextPane);
            return false;
        }
        else {
            NComponentStyleTool.normalStyle(desTextPane);
        }     
         }
         
         
        return true;
    }
    private boolean isEffect(String text) {
        //长度为1-64的所有字符   
        String reg = "^.{1,64}$";
        if (!text.matches(reg)) {
            return false;
        }
        return true;
    }
    private boolean isPass(String text) {
          String urls="^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"   
           + "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"   
           + "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"   
           + "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"   
           + "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"   
           + "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"   
           + "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"   
           + "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$"; 
           if (!text.matches(urls)) {
            return false;
        }
        return true;
    }
    
        private void initAgentAndListener() {
        creatEntryAgent = new CreateEntryAgent();
        modifyEntryAgent = new ModifyEntryAgent();
        creatEntryAgent.addListener(addOrUpdateEntryAgentListener);
        modifyEntryAgent.addListener(addOrUpdateEntryAgentListener);
        uploadPictureAgent = new UploadPictureAgent();
        uploadPictureAgent.addListener(upLoadPictureAgentListener);       
        oKCancelPanel.addOKCancelListener(new OKCancelPanelListener() {
            @Override
            @SuppressWarnings("StringEquality")
            public void onOKClicked() {
                if (isFormValid()) {
                    if (addMode) {

                        if (isaddType()) {
                            entry = new Entry();
                            entry.setName(nameTextField.getText());
                            entry.setInnerUrl(innerUrlTextField.getText());
                            entry.setOuterUrl(outerUrlTextField.getText());
                            entry.setDownloadUrlForIos(downloadUrlForiosTextField.getText());
                            entry.setDownloadUrlForAndroid(downloadUrlForandroidTextField.getText());
                            entry.setDownloadUrlForPc(downloadUrlForpcTextField.getText());
                            entry.setDes(desTextPane.getText());
                            entry.setGuideType((EntryTypeEnum) typeCom.getSelectedItem());
                            entry.setUploadPicture(uplaodPictureServer);
                               localComboBox.getSelectedItem();
                            if (upRadioButton.isSelected()) {
                            creatEntryAgent.setSelectRadio(true);
                              if(localComboBox.getSelectedIndex()==0){
                               Entry e1=(Entry)localComboBox.getSelectedItem();
                               entry.setSortOrder( e1.getSortOrder()/2);
                            }
                              else{
                               Object entry1=localComboBox.getItemAt(localComboBox.getSelectedIndex()-1);
                               Entry entry11=(Entry)entry1;
                               Object entry2=localComboBox.getItemAt(localComboBox.getSelectedIndex());
                               Entry entry22=(Entry)entry2;
                               entry.setSortOrder((entry11.getSortOrder()+entry22.getSortOrder())/2); 
                            }
                         
                              }
                            else if (downRadioButton.isSelected()) {
                            creatEntryAgent.setSelectRadio(false);
                             if(localComboBox.getItemCount()-1==localComboBox.getSelectedIndex()){
                               Entry en1=(Entry)localComboBox.getSelectedItem();
                               entry.setSortOrder( en1.getSortOrder()+1);
                           }
                           else{
                               Object ent1=localComboBox.getItemAt(localComboBox.getSelectedIndex()+1);
                               Entry ent11=(Entry)ent1;
                               Object ent2=localComboBox.getItemAt(localComboBox.getSelectedIndex());
                               Entry ent22=(Entry)ent2;
                               entry.setSortOrder((ent11.getSortOrder()+ent22.getSortOrder())/2); 
                            } 
                              }                               
//                            entry.setChangeSort(localComboBox.getSelectedItem().toString());                
                            creatEntryAgent.setParam(entry,isSelectType);
                            creatEntryAgent.start();
                        }
                    } else if (isModifyType()) {
                        curEntry.setName(nameTextField.getText());
                        curEntry.setInnerUrl(innerUrlTextField.getText());
                        curEntry.setOuterUrl(outerUrlTextField.getText());
                        curEntry.setDownloadUrlForIos(downloadUrlForiosTextField.getText());
                        curEntry.setDownloadUrlForAndroid(downloadUrlForandroidTextField.getText());
                        curEntry.setDownloadUrlForPc(downloadUrlForpcTextField.getText());
                        curEntry.setDes(desTextPane.getText());
                        curEntry.setGuideType((EntryTypeEnum) typeCom.getSelectedItem()); 
                        if(uplaodPictureServer!=null){
                         curEntry.setUploadPicture(uplaodPictureServer);
                        }
                       else{
                         curEntry.setUploadPicture(curEntry.getUploadPicture());
                        }       
                        if (upRadioButton.isSelected()) {
                            modifyEntryAgent.setSelectRadio(true);
                            if(localComboBox.getSelectedIndex()==0){
                               Entry e1=(Entry)localComboBox.getSelectedItem();
                               curEntry.setSortOrder( e1.getSortOrder()/2);
                            }
                         else{
                               Object entry1=localComboBox.getItemAt(localComboBox.getSelectedIndex()-1);
                               Entry entry11=(Entry)entry1;
                               Object entry2=localComboBox.getItemAt(localComboBox.getSelectedIndex());
                               Entry entry22=(Entry)entry2;
                               curEntry.setSortOrder((entry11.getSortOrder()+entry22.getSortOrder())/2); 
                            }
                        } 
                        else if (downRadioButton.isSelected()) {
                            modifyEntryAgent.setSelectRadio(false);
                           if(localComboBox.getItemCount()-1==localComboBox.getSelectedIndex()){
                               Entry en1=(Entry)localComboBox.getSelectedItem();
                               curEntry.setSortOrder( en1.getSortOrder()+1);
                           }
                           else{
                               Object ent1=localComboBox.getItemAt(localComboBox.getSelectedIndex()+1);
                               Entry ent11=(Entry)ent1;
                               Object ent2=localComboBox.getItemAt(localComboBox.getSelectedIndex());
                               Entry ent22=(Entry)ent2;
                               curEntry.setSortOrder((ent11.getSortOrder()+ent22.getSortOrder())/2); 
                            }
                        }    
//                        curEntry.setChangeSort(localComboBox.getSelectedIndex()+1);
                        modifyEntryAgent.setParam(curEntry,isSelectType);                          
                        modifyEntryAgent.start();
                    }
                }
            }

            @Override
            public void onCancelClicked() {
                NInternalDiag<Entry, JComponent> diag
                        = NInternalDiag.findNInternalDiag(AddOrUpdateEntryPanel.this);
                diag.hideDiag();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        localButtonGroup = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        JPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        innerUrlTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        outerUrlTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        downloadUrlForandroidTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        downloadUrlForpcTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        downloadUrlForiosTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        typeCom = new javax.swing.JComboBox<>();
        localLabel = new javax.swing.JLabel();
        localComboBox = new javax.swing.JComboBox<>();
        upRadioButton = new javax.swing.JRadioButton();
        downRadioButton = new javax.swing.JRadioButton();
        uploadBtu = new javax.swing.JButton();
        picPanel = new javax.swing.JPanel();
        nWaitingPanel2 = new com.nazca.ui.NWaitingPanel();
        manPicPane = new com.nazca.ui.NImagePanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bigBtu = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        desTextPane = new javax.swing.JTextArea();
        oKCancelPanel = new com.nazca.inside.guide.client.ui.guide.OKNActionPane1();

        setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(600, 350));

        JPanel1.setName(""); // NOI18N
        JPanel1.setPreferredSize(new java.awt.Dimension(600, 300));

        jLabel4.setText("内网入口：");

        innerUrlTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        innerUrlTextField.setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel5.setText("外网入口：");

        outerUrlTextField.setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel6.setText("URL下载：");

        jLabel7.setText("Android下载：");

        downloadUrlForandroidTextField.setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel8.setText("IOS下载：");

        downloadUrlForpcTextField.setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel3.setText("描述：");

        downloadUrlForiosTextField.setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel9.setText("名称：");

        typeLabel.setText("类别：");

        typeCom.setMinimumSize(new java.awt.Dimension(4, 22));
        typeCom.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                typeComItemStateChanged(evt);
            }
        });

        localLabel.setText("所在位置：");

        localComboBox.setPreferredSize(new java.awt.Dimension(4, 22));
        localComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localComboBoxActionPerformed(evt);
            }
        });

        localButtonGroup.add(upRadioButton);
        upRadioButton.setText("上");

        localButtonGroup.add(downRadioButton);
        downRadioButton.setText("下");

        uploadBtu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nazca/inside/guide/client/ui/res/update-16.png"))); // NOI18N
        uploadBtu.setAlignmentX(0.5F);
        uploadBtu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        uploadBtu.setMargin(new java.awt.Insets(2, 5, 2, 5));
        uploadBtu.setMinimumSize(new java.awt.Dimension(100, 100));
        uploadBtu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBtuActionPerformed(evt);
            }
        });

        picPanel.setLayout(new java.awt.CardLayout());
        picPanel.add(nWaitingPanel2, "WAIT");

        manPicPane.setWaitingTime(10);
        picPanel.add(manPicPane, "CONTENT");

        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("*");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("*");

        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*");

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");

        bigBtu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nazca/inside/guide/client/ui/res/help-16.png"))); // NOI18N
        bigBtu.setAlignmentX(0.5F);
        bigBtu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        bigBtu.setMargin(new java.awt.Insets(2, 5, 2, 5));
        bigBtu.setMinimumSize(new java.awt.Dimension(100, 100));
        bigBtu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bigBtuActionPerformed(evt);
            }
        });

        desTextPane.setLineWrap(true);
        desTextPane.setTabSize(0);
        jScrollPane3.setViewportView(desTextPane);

        javax.swing.GroupLayout JPanel1Layout = new javax.swing.GroupLayout(JPanel1);
        JPanel1.setLayout(JPanel1Layout);
        JPanel1Layout.setHorizontalGroup(
            JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel1Layout.createSequentialGroup()
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(JPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(JPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel4))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(outerUrlTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(innerUrlTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(JPanel1Layout.createSequentialGroup()
                        .addComponent(picPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uploadBtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bigBtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(localLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanel1Layout.createSequentialGroup()
                                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typeLabel)
                                    .addComponent(jLabel9))))
                        .addGap(0, 0, 0)
                        .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanel1Layout.createSequentialGroup()
                                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typeCom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(localComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(upRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(downRadioButton))
                            .addComponent(nameTextField)))
                    .addComponent(downloadUrlForandroidTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(downloadUrlForiosTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(downloadUrlForpcTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        JPanel1Layout.setVerticalGroup(
            JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanel1Layout.createSequentialGroup()
                        .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(uploadBtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel1)))
                        .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bigBtu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(typeCom, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeLabel)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(localLabel)
                                        .addComponent(jLabel10))
                                    .addComponent(localComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(upRadioButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(downRadioButton, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addComponent(picPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel11))
                    .addComponent(innerUrlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outerUrlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(downloadUrlForpcTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(downloadUrlForandroidTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(downloadUrlForiosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jScrollPane2.setViewportView(JPanel1);

        add(jScrollPane2, java.awt.BorderLayout.CENTER);
        add(oKCancelPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void uploadBtuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadBtuActionPerformed
        //打开操作
        JFileChooser fileChooser;//文件选择器
        FileFilter filter;//文件过滤器       
        String fileName = null;//文件名
        ImageIcon icon = null; //图片
        File selectedFile=null;
        BufferedImage img=null;
        String attachmentDirType = null;
        InputStream is = null;
        fileChooser = new JFileChooser(); //选择器
        filter= new javax.swing.filechooser.FileNameExtensionFilter("图片(JPG/GIF/PNG)", "JPG", "JPEG", "GIF", "PNG");               
        fileChooser.setFileFilter(filter);
        int i = fileChooser.showOpenDialog(JPanel1);
        if (i == javax.swing.JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();//获得文件绝对路径
                img = ImageIO.read(selectedFile);
                imgs=img;
                manPicPane.removeAll();
                manPicPane.setImage(img);
                fileName = selectedFile.getName();
                is = new FileInputStream(selectedFile);
                attachmentDirType = fileChooser.getTypeDescription(selectedFile);
                uploadPictureAgent.setParame(fileName, is, attachmentDirType);
                uploadPictureAgent.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_uploadBtuActionPerformed

    private void bigBtuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bigBtuActionPerformed

        ScaleImagePane scaleImagePane = new ScaleImagePane();
        JDialog dialog = new JDialog();       
        dialog.add(scaleImagePane);
        dialog.setSize(450, 450);
        dialog.setLocationRelativeTo(null);
        dialog.setTitle("查看图片");
        dialog.setIconImage(Jk2cMmgtResourceUtil.readImage("manage-pic.png"));
        dialog.setVisible(true);
        if(imgs!=null){
        scaleImagePane.setViewImg(imgs);    
        }
        else if(imgsModify!=null){
         scaleImagePane.setViewImg(imgsModify);    
        }
        else{
        scaleImagePane.setViewImg(imgsOld);   
        }
    }//GEN-LAST:event_bigBtuActionPerformed

    private void typeComItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_typeComItemStateChanged
        // TODO add your handling code here:
        downloadUrlForiosTextField.setEnabled(true);
        downloadUrlForandroidTextField.setEnabled(true);
        downloadUrlForpcTextField.setEnabled(true);
        if(typeCom.getSelectedItem().equals(EntryTypeEnum.doc)||typeCom.getSelectedItem().equals(EntryTypeEnum.web)){
            downloadUrlForiosTextField.setEnabled(false);
            downloadUrlForandroidTextField.setEnabled(false);
            downloadUrlForpcTextField.setEnabled(false);
        }
        
    }//GEN-LAST:event_typeComItemStateChanged

    private void localComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_localComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel1;
    private javax.swing.JButton bigBtu;
    private javax.swing.JTextArea desTextPane;
    private javax.swing.JRadioButton downRadioButton;
    private javax.swing.JTextField downloadUrlForandroidTextField;
    private javax.swing.JTextField downloadUrlForiosTextField;
    private javax.swing.JTextField downloadUrlForpcTextField;
    private javax.swing.JTextField innerUrlTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.ButtonGroup localButtonGroup;
    private javax.swing.JComboBox<String> localComboBox;
    private javax.swing.JLabel localLabel;
    private com.nazca.ui.NImagePanel manPicPane;
    private com.nazca.ui.NWaitingPanel nWaitingPanel2;
    private javax.swing.JTextField nameTextField;
    private com.nazca.inside.guide.client.ui.guide.OKNActionPane1 oKCancelPanel;
    private javax.swing.JTextField outerUrlTextField;
    private javax.swing.JPanel picPanel;
    private javax.swing.JComboBox<String> typeCom;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JRadioButton upRadioButton;
    private javax.swing.JButton uploadBtu;
    // End of variables declaration//GEN-END:variables

    public Entry showMe(JComponent aThis) {
        NInternalDiag diag = null;
        if (addMode) {
            diag  = new NInternalDiag("添加项目",this);                  
        } else {
            diag = new NInternalDiag("修改项目",this);                     
        }
        return (Entry)diag.showInternalDiag(aThis, NInternalDiag.INIT_SIZE_MODE_PREFERED);
    }
    //位置下拉框
    void addLocal(List<Entry> localEntry,int index) {
        this.localEntry = localEntry;
        SimpleObjectListModel<Entry> localModel = new SimpleObjectListModel<>();
        for (int i = 0; i < localEntry.size(); i++) {
            localModel.add(localEntry.get(i)); 
        }
        LocationComboxRenderer locationComboxRenderer=new LocationComboxRenderer();
        localComboBox.setModel(localModel);
        localComboBox.setRenderer(locationComboxRenderer);
//        localComboBox.setModel(localModel);
        localComboBox.setSelectedIndex(index);
    }
       
    void addData(Entry curEntry) {
        this.curEntry = curEntry;    
        if(curEntry.getUploadPicture()!=null){
        downloadPictureAgent = new DownloadPictureAgent(); 
        downloadPictureAgent.addListener(downLoadPictureAgentListener);
        downloadPictureAgent.setParame(curEntry.getUploadPicture()); 
        downloadPictureAgent.start();        
        }
        nameTextField.setText(curEntry.getName());
        typeCom.setSelectedItem(curEntry.getGuideType());
        if(isSelectType){
         typeCom.setEnabled(false);
        }
        localComboBox.setSelectedItem(curEntry.getName());
        innerUrlTextField.setText(curEntry.getInnerUrl());
        outerUrlTextField.setText(curEntry.getOuterUrl());
        downloadUrlForiosTextField.setText(curEntry.getDownloadUrlForIos());
        downloadUrlForandroidTextField.setText(curEntry.getDownloadUrlForAndroid());
        downloadUrlForpcTextField.setText(curEntry.getDownloadUrlForPc());
        desTextPane.setText(curEntry.getDes());

    }

    void addType(EntryTypeEnum curEntryTypeEnum) {
        addEntryTypeEnum = curEntryTypeEnum;
        typeCom.setSelectedItem(curEntryTypeEnum);
        typeCom.setEnabled(false);
        
    }

  
   
}
