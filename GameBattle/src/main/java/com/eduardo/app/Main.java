package com.eduardo.app;

import com.eduardo.screen.Home;
import com.eduardo.client.SocketClient;
import com.eduardo.event.OnGameClose;
import com.eduardo.event.OnGameConnection;
import com.eduardo.event.OnGameData;
import com.eduardo.event.OnGameResponse;
import com.eduardo.listener.ListenerGame;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Main extends javax.swing.JFrame implements ListenerGame {

    private SocketClient socketClient;
    private Screen screen;
    private boolean online;

    public Main() {
        socketClient = new SocketClient();
        this.online = false;
        this.screen = new Home(this);
        initComponents();
    }

    public void connectServerGame() {
        socketClient.addListener(this);
        socketClient.connect();
    }

    private void initConfig() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

    public void loadScreen() {
        loadScreen(this.screen);
    }

    public void loadScreen(Screen screen) {
        setScreen(screen);
        JPanel panel = (JPanel) screen;
        panel.setSize(800, 540);
        jPanelContent.removeAll();
        jPanelContent.add(panel);
        jPanelContent.revalidate();
        jPanelContent.repaint();
    }

    @Override
    public void listenerGameConnection(OnGameConnection e) {
        setOnline(true);
        screen.OnConnect();
    }

    @Override
    public void listenerGameClose(OnGameClose e) {
        setOnline(false);
        screen.OnClose();
    }

    @Override
    public void listenerGameData(OnGameData e) {
        screen.OnData(e.getData());
    }

    @Override
    public void listenerGameResponse(OnGameResponse e) {
        screen.OnResponse(e.getOK(), e.getResponse(), e.getMessage());
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public boolean getOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        String path = (online) ? "/com/eduardo/image/online.png" : "/com/eduardo/image/offline.png";
        jLabelConnection.setIcon(new ImageIcon(getClass().getResource(path)));
        this.online = online;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBackground = new com.eduardo.componentGeneric.PanelRounded();
        jPanelContent = new javax.swing.JPanel();
        panelRoundedHeader = new com.eduardo.componentGeneric.PanelRounded();
        jLabelConnection = new javax.swing.JLabel();
        jButtonClose = new com.eduardo.componentGeneric.JButtonRounded();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanelBackground.setBackground(new java.awt.Color(245, 245, 245));
        jPanelBackground.setPreferredSize(new java.awt.Dimension(800, 570));

        jPanelContent.setBackground(new java.awt.Color(245, 245, 245));
        jPanelContent.setAlignmentX(0.0F);
        jPanelContent.setAlignmentY(0.0F);
        jPanelContent.setPreferredSize(new java.awt.Dimension(800, 540));

        javax.swing.GroupLayout jPanelContentLayout = new javax.swing.GroupLayout(jPanelContent);
        jPanelContent.setLayout(jPanelContentLayout);
        jPanelContentLayout.setHorizontalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanelContentLayout.setVerticalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        panelRoundedHeader.setBackground(new java.awt.Color(245, 245, 245));
        panelRoundedHeader.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelRoundedHeader.setPreferredSize(new java.awt.Dimension(800, 30));

        jLabelConnection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/offline.png"))); // NOI18N

        jButtonClose.setBackground(new java.awt.Color(245, 245, 245));
        jButtonClose.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/close.png"))); // NOI18N
        jButtonClose.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jButtonClose.setMaximumSize(new java.awt.Dimension(24, 24));
        jButtonClose.setMinimumSize(new java.awt.Dimension(24, 24));
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRoundedHeaderLayout = new javax.swing.GroupLayout(panelRoundedHeader);
        panelRoundedHeader.setLayout(panelRoundedHeaderLayout);
        panelRoundedHeaderLayout.setHorizontalGroup(
            panelRoundedHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelConnection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 736, Short.MAX_VALUE)
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRoundedHeaderLayout.setVerticalGroup(
            panelRoundedHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedHeaderLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelRoundedHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelConnection, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanelBackgroundLayout = new javax.swing.GroupLayout(jPanelBackground);
        jPanelBackground.setLayout(jPanelBackgroundLayout);
        jPanelBackgroundLayout.setHorizontalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addComponent(panelRoundedHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanelBackgroundLayout.setVerticalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                .addComponent(panelRoundedHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBackground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        dispose();
        System.exit(0);
    }//GEN-LAST:event_jButtonCloseActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Main main = new Main();
                main.initConfig();
                main.loadScreen();
                main.moveFrame();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.eduardo.componentGeneric.JButtonRounded jButtonClose;
    private javax.swing.JLabel jLabelConnection;
    private com.eduardo.componentGeneric.PanelRounded jPanelBackground;
    private javax.swing.JPanel jPanelContent;
    private com.eduardo.componentGeneric.PanelRounded panelRoundedHeader;
    // End of variables declaration//GEN-END:variables

    private int x, y;

    public void moveFrame() {
        jPanelBackground.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        jPanelBackground.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        });
    }

}
