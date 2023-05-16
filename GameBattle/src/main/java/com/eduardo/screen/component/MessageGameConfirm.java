package com.eduardo.screen.component;

import java.awt.Color;
import javax.swing.JFrame;

public class MessageGameConfirm extends javax.swing.JDialog {

    private final JFrame frame;

    public MessageGameConfirm(JFrame frame) {
        super(frame, true);
        this.frame = frame;
        initComponents();
        init();
    }

    public void init() {
        setBackground(new Color(0, 0, 0, 0));
    }

    public void show(String title, String message) {
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public void close() {
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRounded1 = new com.eduardo.componentGeneric.PanelRounded();
        jButtonRoundedCancel = new com.eduardo.componentGeneric.JButtonRounded();
        jButtonRoundedAcept = new com.eduardo.componentGeneric.JButtonRounded();
        jLabelTitle = new javax.swing.JLabel();
        jLabelMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelRounded1.setBackground(new java.awt.Color(255, 255, 255));

        jButtonRoundedCancel.setBackground(new java.awt.Color(255, 87, 87));
        jButtonRoundedCancel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRoundedCancel.setText("cancel");
        jButtonRoundedCancel.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 14)); // NOI18N
        jButtonRoundedCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRoundedCancelActionPerformed(evt);
            }
        });

        jButtonRoundedAcept.setBackground(new java.awt.Color(153, 153, 153));
        jButtonRoundedAcept.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRoundedAcept.setText("acept");
        jButtonRoundedAcept.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 14)); // NOI18N
        jButtonRoundedAcept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRoundedAceptActionPerformed(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 24)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(153, 153, 153));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("title");

        jLabelMessage.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 24)); // NOI18N
        jLabelMessage.setForeground(new java.awt.Color(153, 153, 153));
        jLabelMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMessage.setText("¿Quieres Ingresar al Juego?");

        javax.swing.GroupLayout panelRounded1Layout = new javax.swing.GroupLayout(panelRounded1);
        panelRounded1.setLayout(panelRounded1Layout);
        panelRounded1Layout.setHorizontalGroup(
            panelRounded1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRounded1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRounded1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jLabelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addGap(56, 56, 56))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRounded1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jButtonRoundedCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonRoundedAcept, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        panelRounded1Layout.setVerticalGroup(
            panelRounded1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRounded1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(jLabelMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(panelRounded1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRoundedCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonRoundedAcept, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRounded1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRounded1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRoundedCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRoundedCancelActionPerformed
    }//GEN-LAST:event_jButtonRoundedCancelActionPerformed

    private void jButtonRoundedAceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRoundedAceptActionPerformed
    }//GEN-LAST:event_jButtonRoundedAceptActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.eduardo.componentGeneric.JButtonRounded jButtonRoundedAcept;
    private com.eduardo.componentGeneric.JButtonRounded jButtonRoundedCancel;
    private javax.swing.JLabel jLabelMessage;
    private javax.swing.JLabel jLabelTitle;
    private com.eduardo.componentGeneric.PanelRounded panelRounded1;
    // End of variables declaration//GEN-END:variables
}
