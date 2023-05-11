package com.eduardo.screen.auth;

import com.eduardo.app.*;
import com.eduardo.client.SocketClient;
import com.eduardo.helper.Protocol;
import com.eduardo.helper.Response;
import com.eduardo.screen.Game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Login extends javax.swing.JPanel implements Screen {

    public static String LOG = Login.class.getName();

    private Main main;

    public Login(Main main) {
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
        jButtonLogin.setEnabled(isOnline);
        if (isOnline) {
            jButtonLogin.setBackground(Color.decode("#ff5757"));
        } else {
            jButtonLogin.setBackground(Color.GRAY);
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

        panelRounded1 = new com.eduardo.component.PanelRounded();
        jTextFieldName = new com.eduardo.component.JTextFieldPlaceHolder();
        jLabelName = new javax.swing.JLabel();
        panelRounded2 = new com.eduardo.component.PanelRounded();
        jPassword = new com.eduardo.component.JPasswordFieldPlaceHolder();
        jLabelPassord = new javax.swing.JLabel();
        jButtonLogin = new com.eduardo.component.JButtonRounded();
        jLabelTitle = new javax.swing.JLabel();
        jLabelLinkRegister = new javax.swing.JLabel();
        jLabelMessage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 245, 245));

        panelRounded1.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldName.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldName.setForeground(new java.awt.Color(153, 153, 153));
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
        jPassword.setForeground(new java.awt.Color(153, 153, 153));
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

        jLabelPassord.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 12)); // NOI18N
        jLabelPassord.setForeground(new java.awt.Color(204, 204, 204));
        jLabelPassord.setText("PASSWORD");

        jButtonLogin.setBackground(new java.awt.Color(255, 87, 87));
        jButtonLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogin.setText("LOGIN");
        jButtonLogin.setFont(new java.awt.Font("JetBrains Mono ExtraBold", 1, 12)); // NOI18N
        jButtonLogin.setPreferredSize(new java.awt.Dimension(123, 48));
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 36)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(204, 204, 204));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("LOGIN");

        jLabelLinkRegister.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 10)); // NOI18N
        jLabelLinkRegister.setForeground(new java.awt.Color(204, 204, 204));
        jLabelLinkRegister.setText("Not have Account ? SingUp Here");
        jLabelLinkRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelLinkRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLinkRegisterMouseClicked(evt);
            }
        });

        jLabelMessage.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 0, 10)); // NOI18N
        jLabelMessage.setForeground(new java.awt.Color(255, 87, 87));
        jLabelMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(268, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                        .addComponent(panelRounded1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelRounded2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelPassord, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelLinkRegister, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))
                .addGap(311, 311, 311))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(39, 39, 39)
                .addComponent(jLabelName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRounded1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelPassord)
                .addGap(9, 9, 9)
                .addComponent(panelRounded2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabelLinkRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelLinkRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLinkRegisterMouseClicked
        Register register = new Register(this.main);
        main.loadScreen(register);
    }//GEN-LAST:event_jLabelLinkRegisterMouseClicked

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        String user = jTextFieldName.getText();
        String password = String.valueOf(jPassword.getPassword());
        SocketClient socketClient = main.getSocketClient();
        String data = Protocol.setFormatLogin(String.valueOf(socketClient.getSessionId()), user, password);
        socketClient.send(data);
        main.loadScreen(new Game(main));
    }//GEN-LAST:event_jButtonLoginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.eduardo.component.JButtonRounded jButtonLogin;
    private javax.swing.JLabel jLabelLinkRegister;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPassord;
    private javax.swing.JLabel jLabelTitle;
    private com.eduardo.component.JPasswordFieldPlaceHolder jPassword;
    private com.eduardo.component.JTextFieldPlaceHolder jTextFieldName;
    private com.eduardo.component.PanelRounded panelRounded1;
    private com.eduardo.component.PanelRounded panelRounded2;
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
        switch (response) {
            case "LOGIN":
                responseLogin(ok, message);
                break;
            default:
                System.out.println("Response No valida !!!");
        }
    }

    public void responseLogin(boolean ok, String message) {
        if (ok) {
            main.loadScreen(new Game(main));
            return;
        }
        jLabelMessage.setText(message);
    }

}
