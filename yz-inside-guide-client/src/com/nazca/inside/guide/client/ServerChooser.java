/*
 * ServerChooser.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-20 15:55:27
 */
package com.nazca.inside.guide.client;

import com.nazca.inside.guide.client.ui.ClientFrame;
import com.nazca.usm.client.ServiceConfig;
import com.nazca.util.PropertyTool;
import com.nazca.inside.guide.common.consts.ProjectConst;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author chenjianan
 */
public class ServerChooser extends JFrame {

    private static final String SERVER_CHOOSER_PROPERTIES_FILE = "chooser.cfg";

    public ServerChooser() {
        super("选择服务器");
        this.initComponent();
        int h = 600;
        int w = 400;
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screenSize.width - w) / 2, (screenSize.height - h) / 2, w, h);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private JButton jb = new JButton("走你");
    private JComboBox client = new JComboBox();
    private JComboBox upd = new JComboBox();
    private JComboBox insideGuideServ = new JComboBox();
    private JComboBox usmServ = new JComboBox();
    private JComboBox mediaLibServer = new JComboBox();
    private JComboBox mediaImgUrl = new JComboBox();
    private JComboBox mediaDocUrl = new JComboBox();

    private void saveComboboxOptions(String name, JComboBox jcb) {
        try {
            Properties p = PropertyTool.loadProperty(new File(
                    ProjectConst.PLATFORM_CONFIG_DIR_PATH),
                    ProjectConst.CLIENT_PRJ_ID,
                    SERVER_CHOOSER_PROPERTIES_FILE);
            int n = jcb.getItemCount();
            String si = jcb.getSelectedItem().toString();
            int m = jcb.getSelectedIndex();
            String ss = si + ";";
            for (int i = 0; i < n; i++) {
                if (jcb.getItemAt(i).toString().equals(si)) {
                    continue;
                }
                ss += jcb.getItemAt(i).toString() + ";";
            }
            p.put(name, ss);
            PropertyTool.saveProperty(p, new File(
                    ProjectConst.PLATFORM_CONFIG_DIR_PATH), ProjectConst.CLIENT_PRJ_ID,
                    SERVER_CHOOSER_PROPERTIES_FILE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadComboboxOptions(String name, JComboBox jcb) {
        try {
            Properties ps = PropertyTool.loadProperty(new File(
                    ProjectConst.PLATFORM_CONFIG_DIR_PATH),
                    ProjectConst.CLIENT_PRJ_ID,
                    SERVER_CHOOSER_PROPERTIES_FILE);
            String ss[] = decodeServerAddr(ps.getProperty(name, ""));
            for (String s : ss) {
                jcb.addItem(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                PropertyTool.saveProperty(new Properties(),
                        new File(ProjectConst.PLATFORM_CONFIG_DIR_PATH), ProjectConst.CLIENT_PRJ_ID,
                        SERVER_CHOOSER_PROPERTIES_FILE);
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
        }
    }

    private String[] decodeServerAddr(String serverAddr) {
        return serverAddr.split("\\;");
    }

    private void initComponent() {
        this.getContentPane().setLayout(new GridLayout(20, 2));
        this.add(new JLabel("客户端更新服务器："));
        this.add(client);
        this.add(new JLabel("更新器更新服务器："));
        this.add(upd);
        this.add(new JLabel("溜溜球系统服务器："));
        this.add(insideGuideServ);
        this.add(new JLabel("登录服务器："));
        this.add(usmServ);

        this.add(new JLabel("medialib服务器："));
        this.add(mediaLibServer);
        this.add(new JLabel("media img地址："));
        this.add(mediaImgUrl);
        this.add(new JLabel("media doc地址："));
        this.add(mediaDocUrl);

        this.client.setEditable(true);
        this.client.addItem("http://localhost/svn/client");
        this.upd.setEditable(true);
        this.upd.addItem("http://localhost/svn/update");
        this.usmServ.setEditable(true);
        this.usmServ.addItem("http://172.16.100.83:8090/usms-web/rpc");
        this.insideGuideServ.setEditable(true);
        this.insideGuideServ.addItem("http://vpn.yingzhitech.com:8192/yz-inside-guide-server/rpc");
        this.insideGuideServ.addItem("http://localhost:8080/yz-inside-guide-server/rpc");
        this.insideGuideServ.addItem("http://172.16.100.115:8888/yz-inside-guide-server/rpc");

        this.mediaLibServer.addItem("http://172.16.100.127:8080/media-lib-server/rpc");
        this.mediaLibServer.setEditable(true);
        this.mediaImgUrl.addItem("http://172.16.100.127:8080/images/");
        this.mediaImgUrl.setEditable(true);
        this.mediaDocUrl.addItem("http://172.16.100.127:8080/files/");
        this.mediaDocUrl.setEditable(true);

        this.add(jb, BorderLayout.SOUTH);
        loadComboboxOptions("client", client);
        loadComboboxOptions("upd", upd);
        loadComboboxOptions("usmServ", usmServ);
        loadComboboxOptions("indideGuideServ", insideGuideServ);
        loadComboboxOptions("mediaLibServer", mediaLibServer);
        loadComboboxOptions("mediaImg", mediaImgUrl);
        loadComboboxOptions("mediaDoc", mediaDocUrl);

        jb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                ClientConfig.INSTALL_SVN_CLIENT = client.getSelectedItem().toString();
                ClientConfig.INSTALL_SVN_UPDATE = upd.getSelectedItem().toString();
                ClientConfig.HTTPRPC_USM_SERVER = usmServ.getSelectedItem().toString();
                ClientConfig.HTTPRPC_INSIDE_GUIDE_SERVER = insideGuideServ.getSelectedItem().toString();
                String mediaLibURL = mediaLibServer.getSelectedItem().toString();
                String mediaImgURL = mediaImgUrl.getSelectedItem().toString();
                String mediaDocURL = mediaDocUrl.getSelectedItem().toString();

                try {
                    ServiceConfig.setUsmsServerURL(new URL(ClientConfig.HTTPRPC_USM_SERVER));

                    ClientContext.setMediaLibURL(new URL(mediaLibURL));
                    ClientContext.setMediaImgURL(mediaImgURL);
                    ClientContext.setMediaDocURL(mediaDocURL);

                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
                saveComboboxOptions("client", client);
                saveComboboxOptions("upd", upd);
                saveComboboxOptions("usmServ", usmServ);
                saveComboboxOptions("indideGuideServ", insideGuideServ);

                saveComboboxOptions("mediaLibServer", mediaLibServer);
                saveComboboxOptions("mediaImg", mediaImgUrl);
                saveComboboxOptions("mediaDoc", mediaDocUrl);

                ClientFrame.getInstance().setVisible(true);
                setVisible(false);
            }
        });
    }
}
