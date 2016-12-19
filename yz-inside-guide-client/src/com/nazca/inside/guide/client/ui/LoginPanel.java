/*
 * LoginPanel.java
 * 
 * Copyright(c) 2007-2010 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-14 20:22:21
 */
package com.nazca.inside.guide.client.ui;

import com.nazca.niis.client.ui.LinkContainer;
import com.nazca.ui.GraphicsTool;
import com.nazca.ui.ImageScalePaintingTool;
import com.nazca.ui.JLinkLabel;
import com.nazca.ui.NComponentStyleTool;
import com.nazca.ui.NLabelMessageTool;
import com.nazca.ui.UIUtilities;
import com.nazca.ui.painter.TriangleBGPainter;
import com.nazca.usm.model.USMSUser;
import com.nazca.util.OpenBrowser;
import com.nazca.inside.guide.client.ClientConfig;
import com.nazca.inside.guide.client.agent.LoginAgent;
import com.nazca.inside.guide.client.ui.layout.LoginPanelLayout;
import com.nazca.inside.guide.client.util.InsideGuideResourceUtil;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.SwingUtilities;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.Painter;
import com.nazca.inside.guide.client.listener.LoginAgentListener;

/**
 *
 * @author Zhang Chunnan
 */
public class LoginPanel extends JXPanel implements LoginAgentListener {

    private TriangleBGPainter tp = new TriangleBGPainter();
    private LoginAgent agent = null;
    private LinkContainer links = null;
    private BufferedImage logincompBg = null;
    private BufferedImage bigLogo = null;
    private Image bgImage = null;

    /**
     * Creates new form LoginPanel
     */
    public LoginPanel() {
        initComponents();
        antialiasedLabel1.setForeground(new Color(0x56789C));
        this.savePwdCkBox.setEnabled(false);
        this.passwordTxfd.setEchoChar('●');
        this.jLabel6.setText(" ");
        this.waitPane.setIndeterminate(true);
        this.waitPane.setVisible(false);
        jXPanel1.setLayout(new LoginPanelLayout());
        this.initLoginComp();
        this.setOpaque(false);
        links = new LinkContainer();
        JLinkLabel help = new JLinkLabel("帮助", InsideGuideResourceUtil.readIcon("help-16.png"));
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    OpenBrowser.openBrowser(ClientConfig.HELP_URL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        JLinkLabel feedback = new JLinkLabel("提交反馈", InsideGuideResourceUtil.readIcon("mail-16.png"));
        feedback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIUtilities.errorDlg(LoginPanel.this, "抱歉，暂时无法在线反馈，请将问题反馈给溜溜球客服中心。");
//                NFeedBackFrame.getInstance().setVisible(true);
            }
        });
        JLinkLabel about = new JLinkLabel("关于", InsideGuideResourceUtil.readIcon("about-16.png"));
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AboutIndideGuide().showMe(LoginPanel.this);
            }
        });

        links.addLink(feedback);
        links.addLink(help);
        links.addLink(about);

        jXPanel1.add(links);
        try {
            bigLogo = InsideGuideResourceUtil.readImage("logo-big.png");
            logincompBg = InsideGuideResourceUtil.readImage("logincomp_bg.png");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        tp.setUseGradiate(true);
        tp.setBlockSize(48);
        tp.setMicroStep(8);
        tp.setBasePoint(new Point(100, 100));
        tp.setStartGradiateColor(new Color(193, 215, 231, 255));
        tp.setEndGradiateColor(new Color(109, 154, 190, 255));
        tp.setGradiateRadius(622);
        bgImage = InsideGuideResourceUtil.readImage("bg.png");
        this.setBackgroundPainter(new Painter() {
            @Override
            public void paint(Graphics2D gd, Object t, int i, int i1) {
                RenderingHints oldHints = GraphicsTool.setQuanlifiedGraphics(gd);
                gd.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
                gd.setRenderingHints(oldHints);
            }
        });
        jXPanel1.setBackgroundPainter(new Painter() {
            @Override
            public void paint(Graphics2D gd, Object t, int i, int i1) {
                gd.drawImage(bigLogo, (getWidth() - bigLogo.getWidth()) / 2, getHeight() * 120 / 600, null);
            }
        });
        this.agent = new LoginAgent();
        this.agent.addListener(this);
        loadUserAndPwd();
        NComponentStyleTool.goodNewsStyle(loginBtn);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints old = g2d.getRenderingHints();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g);
    }
    private ImageScalePaintingTool tool = null;

    private void initLoginComp() {
        this.logincomp.setOpaque(false);
        this.logincomp.setBackgroundPainter(new Painter() {
            public void paint(Graphics2D g, Object object, int width, int height) {
                if (tool == null) {
                    Point[] ps = new Point[4];
                    ps[0] = new Point(38, 44);
                    ps[1] = new Point(451, 44);
                    ps[2] = new Point(451, 193);
                    ps[3] = new Point(38, 193);
                    tool = new ImageScalePaintingTool(logincompBg, ps);
                }
                tool.paint(g, logincomp);
            }
        });
    }

    private void checkAutoLoginBtn() {
        if (this.saveNameCkBox.isSelected()) {
            this.savePwdCkBox.setEnabled(true);
        } else {
            this.savePwdCkBox.setEnabled(false);
            this.savePwdCkBox.setSelected(false);
        }
    }

    void makeLoginBtnDefault() {
        this.getRootPane().setDefaultButton(loginBtn);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        logincomp = new org.jdesktop.swingx.JXPanel();
        antialiasedLabel1 = new com.nazca.ui.AntialiasedLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        userTxfd = new javax.swing.JTextField();
        saveNameCkBox = new javax.swing.JCheckBox();
        savePwdCkBox = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        loginBtn = new javax.swing.JButton();
        resetBtn = new javax.swing.JButton();
        passwordTxfd = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        waitPane = new com.nazca.ui.NProcessingPanel();

        setLayout(new java.awt.BorderLayout());

        jXPanel1.setOpaque(false);

        logincomp.setBackground(new java.awt.Color(153, 153, 153));
        logincomp.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 45, 20, 45));
        logincomp.setName("login"); // NOI18N

        antialiasedLabel1.setForeground(new java.awt.Color(51, 51, 51));
        antialiasedLabel1.setText("平台用户登录");
        antialiasedLabel1.setFont(new java.awt.Font("SimHei", 0, 20)); // NOI18N

        jLabel3.setText("<html>请使用“溜溜球管理系统”的用户名和密码进行登录，有任何问题请点击窗口下方的“帮助”链接查看帮助，或者与管理员联系。</html>");

        jLabel4.setText("用户名：");

        jLabel5.setText("密码：");

        userTxfd.setPreferredSize(new java.awt.Dimension(6, 20));
        userTxfd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userTxfdActionPerformed(evt);
            }
        });

        saveNameCkBox.setText("记住用户名");
        saveNameCkBox.setOpaque(false);
        saveNameCkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveNameCkBoxActionPerformed(evt);
            }
        });

        savePwdCkBox.setText("记住密码");
        savePwdCkBox.setOpaque(false);
        savePwdCkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savePwdCkBoxActionPerformed(evt);
            }
        });

        loginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nazca/inside/guide/client/ui/res/login-16.png"))); // NOI18N
        loginBtn.setText("登  录");
        loginBtn.setOpaque(false);
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        resetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/nazca/inside/guide/client/ui/res/reset-16.png"))); // NOI18N
        resetBtn.setText("重新填写");
        resetBtn.setDefaultCapable(false);
        resetBtn.setOpaque(false);
        resetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetBtnActionPerformed(evt);
            }
        });

        passwordTxfd.setPreferredSize(new java.awt.Dimension(186, 20));
        passwordTxfd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTxfdActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout waitPaneLayout = new javax.swing.GroupLayout(waitPane);
        waitPane.setLayout(waitPaneLayout);
        waitPaneLayout.setHorizontalGroup(
            waitPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        waitPaneLayout.setVerticalGroup(
            waitPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout logincompLayout = new javax.swing.GroupLayout(logincomp);
        logincomp.setLayout(logincompLayout);
        logincompLayout.setHorizontalGroup(
            logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logincompLayout.createSequentialGroup()
                .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logincompLayout.createSequentialGroup()
                        .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(logincompLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(logincompLayout.createSequentialGroup()
                                        .addComponent(saveNameCkBox)
                                        .addGap(18, 18, 18)
                                        .addComponent(savePwdCkBox))
                                    .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(userTxfd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(passwordTxfd, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))))
                            .addGroup(logincompLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(antialiasedLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 77, Short.MAX_VALUE))
                    .addGroup(logincompLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logincompLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(waitPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetBtn)
                                .addGap(18, 18, 18)
                                .addComponent(loginBtn))
                            .addComponent(jSeparator1))))
                .addContainerGap())
        );
        logincompLayout.setVerticalGroup(
            logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logincompLayout.createSequentialGroup()
                .addComponent(antialiasedLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(userTxfd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(passwordTxfd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveNameCkBox)
                    .addComponent(savePwdCkBox))
                .addGap(18, 18, 18)
                .addGroup(logincompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(resetBtn)
                    .addComponent(loginBtn)
                    .addComponent(waitPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(logincomp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(logincomp, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        add(jXPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void saveNameCkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveNameCkBoxActionPerformed
        checkAutoLoginBtn();
    }//GEN-LAST:event_saveNameCkBoxActionPerformed

    private void savePwdCkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savePwdCkBoxActionPerformed
        checkAutoLoginBtn();
    }//GEN-LAST:event_savePwdCkBoxActionPerformed

    private void resetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetBtnActionPerformed
        this.userTxfd.setText("");
        this.passwordTxfd.setText("");
    }//GEN-LAST:event_resetBtnActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        doLogin();
    }//GEN-LAST:event_loginBtnActionPerformed

    private void passwordTxfdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTxfdActionPerformed
        doLogin();
    }//GEN-LAST:event_passwordTxfdActionPerformed

    private void userTxfdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTxfdActionPerformed
        doLogin();
    }//GEN-LAST:event_userTxfdActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.nazca.ui.AntialiasedLabel antialiasedLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private javax.swing.JButton loginBtn;
    private org.jdesktop.swingx.JXPanel logincomp;
    private javax.swing.JPasswordField passwordTxfd;
    private javax.swing.JButton resetBtn;
    private javax.swing.JCheckBox saveNameCkBox;
    private javax.swing.JCheckBox savePwdCkBox;
    private javax.swing.JTextField userTxfd;
    private com.nazca.ui.NProcessingPanel waitPane;
    // End of variables declaration//GEN-END:variables

    private void saveUserAndPwd() {
        String userId = userTxfd.getText().trim();
        String password = new String(passwordTxfd.getPassword());
        if (savePwdCkBox.isSelected()) {
            ClientConfig.saveUserIdAndPassword(userId, password);
        } else if (saveNameCkBox.isSelected()) {
            ClientConfig.saveUserId(userId);
        } else {
            ClientConfig.removeUserIdAndPassword();
        }
    }

    private void loadUserAndPwd() {
        String name = ClientConfig.getUserId();
        String pwd = ClientConfig.getPassword();
        if (!name.equals("")) {
            this.userTxfd.setText(name);
            this.saveNameCkBox.setSelected(true);
        }
        if (!pwd.equals("")) {
            this.passwordTxfd.setText(pwd);
            this.savePwdCkBox.setSelected(true);
        }
        checkAutoLoginBtn();
    }

    private void doLogin() {
        if (this.userTxfd.getText().trim().length() < 1) {
            NLabelMessageTool.errorMessage(jLabel6, "请输入用户名！");
            return;
        }
        if (this.passwordTxfd.getPassword().length < 1) {
            NLabelMessageTool.errorMessage(jLabel6, "请输入密码！");
            return;
        }
        this.userTxfd.setEnabled(false);
        this.passwordTxfd.setEnabled(false);
        this.loginBtn.setEnabled(false);
        this.resetBtn.setEnabled(false);
        this.saveNameCkBox.setEnabled(false);
        this.savePwdCkBox.setEnabled(false);
        this.agent.setLoginName(this.userTxfd.getText());
        this.agent.setPassword(new String(this.passwordTxfd.getPassword()));
        this.agent.start();
    }

    public void onStarted(long seq) {
        this.waitPane.setVisible(true);
        NLabelMessageTool.infoMessage(jLabel6, "登录中...");
    }

    public void onSucceeded(final USMSUser user, long seq) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                NLabelMessageTool.goodNewsMessage(jLabel6, "登录成功！");
                saveUserAndPwd();
                ClientFrame.getInstance().gotoInitPanel();
                waitPane.setVisible(false);
            }
        });
    }

    public void onFailed(String msg, int code, long seq) {
        this.waitPane.setVisible(false);
        NLabelMessageTool.errorMessage(jLabel6, code, msg);
        this.userTxfd.setEnabled(true);
        this.passwordTxfd.setEnabled(true);
        this.saveNameCkBox.setEnabled(true);
        this.savePwdCkBox.setEnabled(true);
        this.checkAutoLoginBtn();
        this.resetBtn.setEnabled(true);
        this.loginBtn.setEnabled(true);
    }
}
