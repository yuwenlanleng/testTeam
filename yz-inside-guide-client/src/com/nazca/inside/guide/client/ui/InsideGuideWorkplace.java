package com.nazca.inside.guide.client.ui;

/*
 * StaffWorkplace.java
 *
 * Created on 2013
 */
import com.nazca.niis.client.ui.LinkContainer;
import com.nazca.niis.client.ui.TabContainer;
import com.nazca.niis.client.ui.layout.WorkplaceLayout;
import com.nazca.niis.client.ui.layout.WorkplaceTitlePanelLayout;
import com.nazca.ui.JLinkLabel;
import com.nazca.ui.NSlideMenuContainedPanel;
import com.nazca.ui.UIUtilities;
import com.nazca.usm.client.service.async.agent.ClientClockAgent;
import com.nazca.usm.model.USMSUser;
import com.nazca.util.OpenBrowser;
import com.nazca.inside.guide.client.ChgUserInfoPanel;
import com.nazca.inside.guide.client.ClientConfig;
import com.nazca.inside.guide.client.ClientContext;
import com.nazca.inside.guide.client.PasswordCheckPanel;
import com.nazca.inside.guide.client.agent.TimeServiceAgent;
import com.nazca.inside.guide.client.db.BasicCodesSyncer;
import com.nazca.inside.guide.client.db.DBServiceControler;
import com.nazca.inside.guide.client.ui.guide.YzInsidePanel;
import com.nazca.inside.guide.client.ui.laf.TitlePanelUI;
import com.nazca.inside.guide.client.ui.sysmodel.SystemMgmtPanel;
import com.nazca.inside.guide.client.util.InsideGuideResourceUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXPanel;

/**
 *
 * @author Zhang Chunnan
 */
public class InsideGuideWorkplace extends JXPanel {

    private TabContainer tabContainer = null;
    private LinkContainer linkContainer = null;
    private JLinkLabel about = null;
    private JLinkLabel help = null;
    private JLinkLabel logout = null;
    private JLinkLabel feedback = null;
    private JLinkLabel pwd = null;
    private JLinkLabel config = null;
    public static final int TITLE_HEIGHT = 66;
    private JPanel helpPanel;
    public static final int HELP_PANEL_WIDTH = 200;
    private JLabel userinfo = new JLabel();

    /**
     * Creates new form Workplace
     */
    public InsideGuideWorkplace() {
        initComponents();
        this.setLayout(new BorderLayout());
        this.add(this.titlePanel, WorkplaceLayout.NORTH);
        this.add(this.mainContainer, WorkplaceLayout.CENTER);
        //initHelpPanel();
        this.userinfo.setIcon(InsideGuideResourceUtil.readIcon("user-16.png"));
        this.tabContainer = new TabContainer();
        this.linkContainer = new LinkContainer();
        this.titlePanel.setPreferredSize(new Dimension(200, TITLE_HEIGHT));
        this.titlePanel.setLayout(new WorkplaceTitlePanelLayout());
        this.titlePanel.setUI(new TitlePanelUI());
        this.titlePanel.add(tabContainer.getTabController());
        this.titlePanel.add(linkContainer);
        this.titlePanel.add(userinfo);
        this.mainContainer.add(tabContainer, "asdf");
        initLinks();

        USMSUser user = ClientContext.getUser();
        userinfo.setForeground(Color.DARK_GRAY);
        if (user != null) {
            userinfo.setText(user.getName() + "，您好！");
        }
        initTabs();
    }

    public void initHelpPanel() {
        helpPanel = new JPanel();
        helpPanel.setPreferredSize(new Dimension(200, 2000));
        this.add(helpPanel, WorkplaceLayout.EAST);
        helpPanel.setVisible(false);
    }

    private void initLinks() {
        JLinkLabel lock = new JLinkLabel("锁定");
        lock.setIcon(InsideGuideResourceUtil.readIcon("lock-16.png"));
        lock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PasswordCheckPanel pane = new PasswordCheckPanel();
                if (pane.showMeForLock(getRootPane())) {
                    Panel panel = new Panel();
                    panel.setLayout(new BorderLayout());
                    NSlideMenuContainedPanel p = new NSlideMenuContainedPanel();
                    panel.add(p, BorderLayout.CENTER);
                    validate();
                    repaint();
                }
            }
        });
        this.linkContainer.addLink(lock);
        final JLinkLabel setPwd = new JLinkLabel("修改密码");
        setPwd.setIcon(InsideGuideResourceUtil.readIcon("user-info-16.png"));
        setPwd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ChgUserInfoPanel().showMe(setPwd);
            }
        });
        this.linkContainer.addLink(setPwd);
        logout = new JLinkLabel("注销");
        logout.setIcon(InsideGuideResourceUtil.readIcon("logout-16.png"));
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (UIUtilities.okCancelDlg(InsideGuideWorkplace.this, "询问", "确定要注销吗？")) {
                    TimeServiceAgent.getInstance().stopSyncTime();
                    ClientClockAgent.getInstance().stopSyncTime();
                    BasicCodesSyncer.getInstance().removeAllListeners();
                    BasicCodesSyncer.getInstance().stop();
                    DBServiceControler.stopDBServer();
                    ClientFrame.getInstance().gotoLogin();
                }
            }
        });
        this.linkContainer.addLink(logout);

        feedback = new JLinkLabel("反馈");
        feedback.setIcon(InsideGuideResourceUtil.readIcon("mail-16.png"));
        feedback.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                UIUtilities.errorDlg(InsideGuideWorkplace.this, "抱歉，暂时无法在线反馈，请将问题反馈给溜溜球客服中心。");
//                fbFrame.setSender(ClientContext.getUser().getLoginName() +"(" + ClientContext.getUser().getName() + ")");
//                fbFrame.setSize(750, 550);
//                fbFrame.setVisible(true);
//                fbFrame.toFront();
            }
        });
        this.linkContainer.addLink(feedback);
        // add help link
        help = new JLinkLabel();
        help.setText("帮助");
        help.setIcon(InsideGuideResourceUtil.readIcon("help-16.png"));
        help.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                try {
                    OpenBrowser.openBrowser(ClientConfig.HELP_URL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        this.linkContainer.addLink(help);
        about = new JLinkLabel();
        about.setText("关于");
        about.setIcon(InsideGuideResourceUtil.readIcon("about-16.png"));
        this.linkContainer.addLink(about);
        about.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                new AboutIndideGuide().showMe(InsideGuideWorkplace.this);
            }
        });
        this.linkContainer.add(about);
    }

    private void initTabs() {
        SystemMgmtPanel sysMgmtMainPane = new SystemMgmtPanel();
        tabContainer.addTabPanel(sysMgmtMainPane, false);

        YzInsidePanel yzInsidePanel = new YzInsidePanel();
        tabContainer.addTabPanel(yzInsidePanel, false);
        
//        MyDemoPanel myDemoPanel=new MyDemoPanel();
//        tabContainer.addTabPanel(myDemoPanel,false);

    }

    protected void destoryAll() {
        linkContainer.removeAll();
        tabContainer.removeAllTabPanels();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new org.jdesktop.swingx.JXPanel();
        mainContainer = new org.jdesktop.swingx.JXPanel();

        titlePanel.setOpaque(false);
        titlePanel.setLayout(new javax.swing.BoxLayout(titlePanel,
                javax.swing.BoxLayout.X_AXIS));

        mainContainer.setOpaque(false);
        mainContainer.setLayout(new java.awt.CardLayout());

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected org.jdesktop.swingx.JXPanel mainContainer;
    protected org.jdesktop.swingx.JXPanel titlePanel;
    // End of variables declaration//GEN-END:variables

    public TabContainer getTabContainer() {
        return tabContainer;
    }

    public LinkContainer getLinkContainer() {
        return linkContainer;
    }
}
