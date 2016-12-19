/*
 * ClientFrame.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-14 12:11:24
 */
package com.nazca.inside.guide.client.ui;

import com.nazca.niis.client.ui.FrameLocker;
import com.nazca.ui.InternalDiagTool;
import com.nazca.ui.UIUtilities;
import com.nazca.inside.guide.client.util.InsideGuideResourceUtil;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Zhang Chunnan
 */
public class ClientFrame extends JFrame {
    private static ClientFrame cf = null;
    private InsideGuideWorkplace oaWP = null;
    private LoginPanel login = null;
    private FrameLocker locker = null;
    private InitPanel initPane = null;

    private ClientFrame() {
        super("英智内部入口管理");
        Image logo = InsideGuideResourceUtil.readImage("logo-32.png");
        this.setIconImage(logo);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(UIUtilities.okCancelDlg(ClientFrame.this, "询问", "确定要退出“英智内部入口管理”吗？")){
                    System.exit(0);
                }
            }
        });
        login = new LoginPanel();
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(login, BorderLayout.CENTER);
        resetLocation();
    }

    public void gotoLogin() {
        login = new LoginPanel();
        this.getContentPane().removeAll();
        this.getContentPane().add(login, BorderLayout.CENTER);
        this.validate();
        this.repaint();
        login.makeLoginBtnDefault();
        oaWP.destoryAll();
    }

    public void gotoInitPanel(){
        initPane = new InitPanel();
        this.getContentPane().removeAll();
        this.getContentPane().add(initPane, BorderLayout.CENTER);
        this.validate();
        this.repaint();
        initPane.onInit();
    }
    
    public void gotoWorkPlace() {
        this.oaWP = new InsideGuideWorkplace();
        this.getContentPane().removeAll();
        this.getContentPane().add(oaWP, BorderLayout.CENTER);
        this.validate();
        this.repaint();
        initPane.onDestroy();
    }

    public static ClientFrame getInstance() {
        if (cf == null) {
            cf = new ClientFrame();
        }
        return cf;
    }

    public void resetLocation() {
        int h = 720;
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int w = h * 16 / 10;
        if (w > screenSize.width) {
            w = screenSize.width;
        }
        setSize(w, h);
        setLocationRelativeTo(null);
    }

    public void lockFrame() {
        locker = new FrameLocker();
        InternalDiagTool.showInternalDiag(this, locker, "平台锁定", null);
    }

    public void unLockFrame() {
        if (locker != null) {
            InternalDiagTool.hideInternalDiag(locker);
            locker = null;
        }
    }
}
