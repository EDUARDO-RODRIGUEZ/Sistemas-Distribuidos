package com.eduardo.screen;

import com.eduardo.app.Main;
import com.eduardo.app.Screen;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Game extends javax.swing.JPanel implements Screen {

    public static String LOG = Game.class.getName();

    private Main main;
    private int row, col;

    public Game(Main main, int row, int col) {
        initComponents();
        this.main = main;
        this.row = row;
        this.col = col;
        setOpaque(false);
        init();
    }

    public void init() {
        initBoards();
    }

    public void initBoards() {
        GridLayout myBoard = (GridLayout) panelRoundedTablero.getLayout();
        GridLayout enemyBoard = (GridLayout) panelRoundedTableroEnemy.getLayout();
        myBoard.setRows(this.row);
        myBoard.setColumns(this.col);
        enemyBoard.setRows(this.row);
        enemyBoard.setColumns(this.col);
        for (int i = 0; i < (row * col); i++) {
            JLabel jLabel1 = new JLabel();
            jLabel1.setName(panelRoundedTablero.getName() + "-" + i);
            jLabel1.setBorder(new LineBorder(Color.GRAY, 1, true));
            jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelRoundedTablero.add(jLabel1);

            JLabel jLabel2 = new JLabel();
            jLabel2.setName(panelRoundedTableroEnemy.getName() + "-" + i);
            jLabel2.setBorder(new LineBorder(Color.GRAY, 1, true));
            jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
            panelRoundedTableroEnemy.add(jLabel2);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        panelRoundedField = new com.eduardo.componentGeneric.PanelRounded();
        jLabelTitleField = new javax.swing.JLabel();
        panelRoundedTablero = new com.eduardo.componentGeneric.PanelRounded();
        panelRoundedField1 = new com.eduardo.componentGeneric.PanelRounded();
        jLabelTitleField1 = new javax.swing.JLabel();
        panelRoundedTableroEnemy = new com.eduardo.componentGeneric.PanelRounded();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(800, 540));

        jLabelTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/battlefield.png"))); // NOI18N

        panelRoundedField.setBackground(new java.awt.Color(204, 204, 204));
        panelRoundedField.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelRoundedField.setPreferredSize(new java.awt.Dimension(235, 334));

        jLabelTitleField.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 14)); // NOI18N
        jLabelTitleField.setForeground(new java.awt.Color(255, 87, 87));
        jLabelTitleField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitleField.setText("MY FIELD");

        panelRoundedTablero.setBackground(new java.awt.Color(204, 204, 204));
        panelRoundedTablero.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout panelRoundedFieldLayout = new javax.swing.GroupLayout(panelRoundedField);
        panelRoundedField.setLayout(panelRoundedFieldLayout);
        panelRoundedFieldLayout.setHorizontalGroup(
            panelRoundedFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedFieldLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRoundedFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRoundedTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(jLabelTitleField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRoundedFieldLayout.setVerticalGroup(
            panelRoundedFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedFieldLayout.createSequentialGroup()
                .addComponent(jLabelTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRoundedTablero, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelRoundedField1.setBackground(new java.awt.Color(204, 204, 204));
        panelRoundedField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelRoundedField1.setPreferredSize(new java.awt.Dimension(235, 334));

        jLabelTitleField1.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 14)); // NOI18N
        jLabelTitleField1.setForeground(new java.awt.Color(255, 87, 87));
        jLabelTitleField1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitleField1.setText("ENEMY FIELD");

        panelRoundedTableroEnemy.setBackground(new java.awt.Color(204, 204, 204));
        panelRoundedTableroEnemy.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout panelRoundedField1Layout = new javax.swing.GroupLayout(panelRoundedField1);
        panelRoundedField1.setLayout(panelRoundedField1Layout);
        panelRoundedField1Layout.setHorizontalGroup(
            panelRoundedField1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedField1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRoundedField1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRoundedTableroEnemy, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(jLabelTitleField1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRoundedField1Layout.setVerticalGroup(
            panelRoundedField1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedField1Layout.createSequentialGroup()
                .addComponent(jLabelTitleField1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelRoundedTableroEnemy, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(204, 204, 204));
        jLabel1.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/battle.png"))); // NOI18N
        jLabel1.setText("Enemy Battle  :");
        jLabel1.setPreferredSize(new java.awt.Dimension(155, 48));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("JetBrains Mono Medium", 1, 10)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/poison.png"))); // NOI18N
        jLabel2.setText("Enemy Destroy :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabelTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelRoundedField, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelRoundedField1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRoundedField, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelRoundedField1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g = (Graphics2D) grphcs;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTitleField;
    private javax.swing.JLabel jLabelTitleField1;
    private com.eduardo.componentGeneric.PanelRounded panelRoundedField;
    private com.eduardo.componentGeneric.PanelRounded panelRoundedField1;
    private com.eduardo.componentGeneric.PanelRounded panelRoundedTablero;
    private com.eduardo.componentGeneric.PanelRounded panelRoundedTableroEnemy;
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
