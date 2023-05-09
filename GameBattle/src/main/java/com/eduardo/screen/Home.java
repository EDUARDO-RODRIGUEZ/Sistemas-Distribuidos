package com.eduardo.screen;

import com.eduardo.app.Main;
import com.eduardo.app.Screen;
import com.eduardo.screen.auth.Login;
import com.eduardo.component.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Home extends javax.swing.JPanel implements Screen {

    private Main main;

    public Home(Main main) {
        initComponents();
        this.main = main;
        setOpaque(false);
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

        jLabelTitle = new javax.swing.JLabel();
        jButtonPlay = new com.eduardo.component.JButtonRounded();

        setBackground(new java.awt.Color(245, 245, 245));

        jLabelTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/battlegame.png"))); // NOI18N

        jButtonPlay.setBackground(new java.awt.Color(255, 87, 87));
        jButtonPlay.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPlay.setText("PLAY");
        jButtonPlay.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 24)); // NOI18N
        jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(257, 257, 257))
            .addGroup(layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabelTitle)
                .addGap(18, 18, 18)
                .addComponent(jButtonPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
        main.connectServerGame();
        Login login = new Login(this.main);
        main.loadScreen(login);
    }//GEN-LAST:event_jButtonPlayActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.eduardo.component.JButtonRounded jButtonPlay;
    private javax.swing.JLabel jLabelTitle;
    // End of variables declaration//GEN-END:variables

    @Override
    public void OnData(String data) {
    }

    @Override
    public void OnClose() {
    }

    @Override
    public void OnConnect() {
    }

    @Override
    public void OnResponse(boolean ok, String response, String message) {
    }
}
