package com.eduardo.screen.auth;

import com.eduardo.app.*;
import com.eduardo.client.SocketClient;
import com.eduardo.helper.Protocol;
import com.eduardo.screen.FieldShip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Register extends javax.swing.JPanel implements Screen {

    private Main main;

    public Register(Main main) {
        this.main = main;
        setOpaque(false);
        initComponents();
        config();
    }

    public void config() {
        updateStateBtnLogin();
    }

    private void updateStateBtnLogin() {
        boolean isOnline = main.getOnline();
        jButtonRegister.setEnabled(isOnline);
        if (isOnline) {
            jButtonRegister.setBackground(Color.decode("#ff5757"));
        } else {
            jButtonRegister.setBackground(Color.GRAY);
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g = (Graphics2D) grphcs;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRounded1 = new com.eduardo.componentGeneric.PanelRounded();
        jTextFieldName = new com.eduardo.componentGeneric.JTextFieldPlaceHolder();
        jLabelName = new javax.swing.JLabel();
        panelRounded2 = new com.eduardo.componentGeneric.PanelRounded();
        jPassword = new com.eduardo.componentGeneric.JPasswordFieldPlaceHolder();
        jLabelPassword = new javax.swing.JLabel();
        jButtonRegister = new com.eduardo.componentGeneric.JButtonRounded();
        jLabelTitle = new javax.swing.JLabel();
        jLabelLinkLogin = new javax.swing.JLabel();
        jLabelMessage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(800, 540));

        panelRounded1.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldName.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldName.setForeground(new java.awt.Color(128, 128, 128));
        jTextFieldName.setPlaceholder("name...");

        javax.swing.GroupLayout panelRounded1Layout = new javax.swing.GroupLayout(panelRounded1);
        panelRounded1.setLayout(panelRounded1Layout);
        panelRounded1Layout.setHorizontalGroup(
            panelRounded1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRounded1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRounded1Layout.setVerticalGroup(
            panelRounded1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRounded1Layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelName.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 12)); // NOI18N
        jLabelName.setForeground(new java.awt.Color(204, 204, 204));
        jLabelName.setText("NAME");

        panelRounded2.setBackground(new java.awt.Color(204, 204, 204));

        jPassword.setBackground(new java.awt.Color(204, 204, 204));
        jPassword.setForeground(new java.awt.Color(128, 128, 128));
        jPassword.setText("password...");
        jPassword.setPlaceholder("password...");

        javax.swing.GroupLayout panelRounded2Layout = new javax.swing.GroupLayout(panelRounded2);
        panelRounded2.setLayout(panelRounded2Layout);
        panelRounded2Layout.setHorizontalGroup(
            panelRounded2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRounded2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelRounded2Layout.setVerticalGroup(
            panelRounded2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRounded2Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelPassword.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 12)); // NOI18N
        jLabelPassword.setForeground(new java.awt.Color(204, 204, 204));
        jLabelPassword.setText("PASSWORD");

        jButtonRegister.setBackground(new java.awt.Color(255, 87, 87));
        jButtonRegister.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRegister.setText("REGISTER");
        jButtonRegister.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 12)); // NOI18N
        jButtonRegister.setPreferredSize(new java.awt.Dimension(123, 48));
        jButtonRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegisterActionPerformed(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 36)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("REGISTER");

        jLabelLinkLogin.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 10)); // NOI18N
        jLabelLinkLogin.setForeground(new java.awt.Color(204, 204, 204));
        jLabelLinkLogin.setText("Already have an account ? login here");
        jLabelLinkLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelLinkLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLinkLoginMouseClicked(evt);
            }
        });

        jLabelMessage.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 10)); // NOI18N
        jLabelMessage.setForeground(new java.awt.Color(255, 87, 87));
        jLabelMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(268, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                        .addComponent(panelRounded1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelRounded2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelLinkLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))
                .addGap(311, 311, 311))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRounded1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelPassword)
                .addGap(9, 9, 9)
                .addComponent(panelRounded2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabelLinkLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jLabelMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelLinkLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLinkLoginMouseClicked
        Login login = new Login(this.main);
        main.loadScreen(login);
    }//GEN-LAST:event_jLabelLinkLoginMouseClicked

    private void jButtonRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegisterActionPerformed
        String name = jTextFieldName.getText();
        String password = String.valueOf(jPassword.getPassword());
        SocketClient socketClient = main.getSocketClient();
        socketClient.send(Protocol.setFormatRegister(String.valueOf(socketClient.getSessionId()), name, password));
    }//GEN-LAST:event_jButtonRegisterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.eduardo.componentGeneric.JButtonRounded jButtonRegister;
    private javax.swing.JLabel jLabelLinkLogin;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelTitle;
    private com.eduardo.componentGeneric.JPasswordFieldPlaceHolder jPassword;
    private com.eduardo.componentGeneric.JTextFieldPlaceHolder jTextFieldName;
    private com.eduardo.componentGeneric.PanelRounded panelRounded1;
    private com.eduardo.componentGeneric.PanelRounded panelRounded2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void OnData(String data) {
    }

    @Override
    public void OnConnect() {
        updateStateBtnLogin();
    }

    @Override
    public void OnClose() {
        updateStateBtnLogin();
    }

    @Override
    public void OnResponse(boolean ok, String response, String message) {
        System.out.println(ok);
        System.out.println(response);
        System.out.println(message);
        switch (response) {
            case "REGISTER":
                responseRegister(ok, message);
                break;
            default:
                System.out.println("Response No valida !!!");
        }
    }

    public void responseRegister(boolean ok, String message) {
        if (ok) {
            if (message.equalsIgnoreCase("0")) {
                main.loadScreen(new FieldShip(main, "play"));
                return;
            }
            main.loadScreen(new FieldShip(main, "confirm"));
        }
        jLabelMessage.setText(message);
    }
}
