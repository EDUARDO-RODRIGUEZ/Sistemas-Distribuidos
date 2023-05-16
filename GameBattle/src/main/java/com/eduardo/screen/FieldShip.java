package com.eduardo.screen;

import com.eduardo.app.Main;
import com.eduardo.app.Screen;
import com.eduardo.client.SocketClient;
import com.eduardo.helper.ErrorFormatException;
import com.eduardo.helper.Protocol;
import com.eduardo.helper.TableroValue;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FieldShip extends javax.swing.JPanel implements Screen {

    public static String LOG = FieldShip.class.getName();

    private Main main;
    private int ships;
    private String btnText;

    public FieldShip(Main main, String btnText) {
        initComponents();
        this.main = main;
        this.btnText = btnText;
        setOpaque(false);
        init();
        getRequest();
    }

    public void init() {
        jButtonRoundedPlay.setText(btnText);
    }

    public void getRequest() {
        getSizeMatrix();
        getOnline();
    }

    public void getSizeMatrix() {
        SocketClient socketClient = main.getSocketClient();
        socketClient.send(Protocol.setFormatSizeMatrix(String.valueOf(socketClient.getSessionId())));
    }

    public void getOnline() {
        SocketClient socketClient = main.getSocketClient();
        socketClient.send(Protocol.setFormatCountOnline(String.valueOf(socketClient.getSessionId())));
    }

    public void handleClickCell(MouseEvent e) {
        JLabel cell = (JLabel) e.getSource();
        int index = Integer.parseInt(cell.getName());
        int count = (int) Math.sqrt(panelRoundedGrid.getComponents().length);
        if (ships == 0) {
            System.out.println("Limit Ships");
            return;
        }
        ships--;
        jLabelShip1Count.setText(String.valueOf(this.ships));
        cell.setIcon(new ImageIcon(getClass().getResource("/com/eduardo/image/shipleveltwo32.png")));
        int fila = index / count;
        int columna = index % count;
        SocketClient socketClient = main.getSocketClient();
        socketClient.send(Protocol.setFormatTableroSet(String.valueOf(socketClient.getSessionId()), fila, columna, TableroValue.ELEMENT));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        panelRoundedField = new com.eduardo.componentGeneric.PanelRounded();
        jLabelTitleField = new javax.swing.JLabel();
        panelRoundedGrid = new com.eduardo.componentGeneric.PanelRounded();
        panelRoundedShips = new com.eduardo.componentGeneric.PanelRounded();
        jLabelTitleShips = new javax.swing.JLabel();
        panelRoundedShip1 = new com.eduardo.componentGeneric.PanelRounded();
        jLabelShip1Icon = new javax.swing.JLabel();
        jLabelShip1Count = new javax.swing.JLabel();
        jButtonRoundedPlay = new com.eduardo.componentGeneric.JButtonRounded();
        jLabelConfirm = new javax.swing.JLabel();
        jLabelCountOnline = new javax.swing.JLabel();

        setBackground(new java.awt.Color(245, 245, 245));
        setPreferredSize(new java.awt.Dimension(800, 540));

        jLabelTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/battlefield.png"))); // NOI18N

        panelRoundedField.setBackground(new java.awt.Color(204, 204, 204));

        jLabelTitleField.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 14)); // NOI18N
        jLabelTitleField.setForeground(new java.awt.Color(255, 87, 87));
        jLabelTitleField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitleField.setText("FIELD");

        panelRoundedGrid.setBackground(new java.awt.Color(204, 204, 204));
        panelRoundedGrid.setForeground(new java.awt.Color(204, 204, 204));
        panelRoundedGrid.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout panelRoundedFieldLayout = new javax.swing.GroupLayout(panelRoundedField);
        panelRoundedField.setLayout(panelRoundedFieldLayout);
        panelRoundedFieldLayout.setHorizontalGroup(
            panelRoundedFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedFieldLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRoundedFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRoundedFieldLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(panelRoundedGrid, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRoundedFieldLayout.createSequentialGroup()
                        .addComponent(jLabelTitleField, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelRoundedFieldLayout.setVerticalGroup(
            panelRoundedFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedFieldLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabelTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRoundedGrid, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panelRoundedShips.setBackground(new java.awt.Color(204, 204, 204));

        jLabelTitleShips.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 14)); // NOI18N
        jLabelTitleShips.setForeground(new java.awt.Color(255, 87, 87));
        jLabelTitleShips.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitleShips.setText("SHIPS");

        panelRoundedShip1.setBackground(new java.awt.Color(204, 204, 204));
        panelRoundedShip1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        panelRoundedShip1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelRoundedShip1MousePressed(evt);
            }
        });
        panelRoundedShip1.setLayout(new java.awt.FlowLayout());

        jLabelShip1Icon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelShip1Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/shipleveltwo64.png"))); // NOI18N
        jLabelShip1Icon.setPreferredSize(new java.awt.Dimension(120, 64));
        panelRoundedShip1.add(jLabelShip1Icon);

        jLabelShip1Count.setBackground(new java.awt.Color(204, 204, 204));
        jLabelShip1Count.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 12)); // NOI18N
        jLabelShip1Count.setForeground(new java.awt.Color(255, 87, 87));
        jLabelShip1Count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelShip1Count.setText("0");
        jLabelShip1Count.setPreferredSize(new java.awt.Dimension(120, 64));
        panelRoundedShip1.add(jLabelShip1Count);

        panelRoundedShip1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panelRoundedShipsLayout = new javax.swing.GroupLayout(panelRoundedShips);
        panelRoundedShips.setLayout(panelRoundedShipsLayout);
        panelRoundedShipsLayout.setHorizontalGroup(
            panelRoundedShipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedShipsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRoundedShipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTitleShips, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRoundedShip1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRoundedShipsLayout.setVerticalGroup(
            panelRoundedShipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRoundedShipsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabelTitleShips, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRoundedShip1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonRoundedPlay.setBackground(new java.awt.Color(255, 87, 87));
        jButtonRoundedPlay.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRoundedPlay.setFont(new java.awt.Font("JetBrains Mono NL ExtraBold", 1, 14)); // NOI18N
        jButtonRoundedPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRoundedPlayActionPerformed(evt);
            }
        });

        jLabelConfirm.setForeground(new java.awt.Color(204, 204, 204));
        jLabelConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/check.png"))); // NOI18N
        jLabelConfirm.setText("1");

        jLabelCountOnline.setForeground(new java.awt.Color(204, 204, 204));
        jLabelCountOnline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/eduardo/image/onlineplaying.png"))); // NOI18N
        jLabelCountOnline.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabelTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelConfirm, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelCountOnline, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonRoundedPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelRoundedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(panelRoundedShips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTitle)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelCountOnline)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelConfirm)
                        .addGap(19, 19, 19)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRoundedField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRoundedShips, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jButtonRoundedPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void panelRoundedShip1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRoundedShip1MousePressed
        Border border = panelRoundedShip1.getBorder();
        if (border instanceof EmptyBorder) {
            panelRoundedShip1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        } else {
            panelRoundedShip1.setBorder(BorderFactory.createEmptyBorder());
        }
    }//GEN-LAST:event_panelRoundedShip1MousePressed

    private void jButtonRoundedPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRoundedPlayActionPerformed
        if (jButtonRoundedPlay.getText().equalsIgnoreCase("play")) {
            GridLayout board = (GridLayout) panelRoundedGrid.getLayout();
            SocketClient socketClient = main.getSocketClient();
            socketClient.send(Protocol.setFormatInitPlay(String.valueOf(socketClient.getSessionId())));
            main.loadScreen(new Game(main, board.getRows(), board.getColumns()));
        } else {
            int confirm = Integer.parseInt(jLabelConfirm.getText());
            jLabelConfirm.setText(String.valueOf((confirm + 1)));
            jButtonRoundedPlay.setBackground(Color.GRAY);
            jButtonRoundedPlay.setEnabled(false);
            SocketClient socketClient = main.getSocketClient();
            socketClient.send(Protocol.setFormatConfirmPlay(String.valueOf(socketClient.getSessionId())));
        }
    }//GEN-LAST:event_jButtonRoundedPlayActionPerformed

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g = (Graphics2D) grphcs;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.eduardo.componentGeneric.JButtonRounded jButtonRoundedPlay;
    private javax.swing.JLabel jLabelConfirm;
    private javax.swing.JLabel jLabelCountOnline;
    private javax.swing.JLabel jLabelShip1Count;
    private javax.swing.JLabel jLabelShip1Icon;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelTitleField;
    private javax.swing.JLabel jLabelTitleShips;
    private com.eduardo.componentGeneric.PanelRounded panelRoundedField;
    private com.eduardo.componentGeneric.PanelRounded panelRoundedGrid;
    private com.eduardo.componentGeneric.PanelRounded panelRoundedShip1;
    private com.eduardo.componentGeneric.PanelRounded panelRoundedShips;
    // End of variables declaration//GEN-END:variables

    public void loadMatrix(int row, int col) {
        GridLayout gridLayout = (GridLayout) panelRoundedGrid.getLayout();
        gridLayout.setRows(row);
        gridLayout.setColumns(col);
        for (int i = 0; i < (row * col); i++) {
            JLabel jLabel = new JLabel();
            jLabel.setName(String.valueOf(i));
            jLabel.setBorder(new LineBorder(Color.GRAY, 1, true));
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            jLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    handleClickCell(e);
                }
            });
            panelRoundedGrid.add(jLabel);
        }
        this.ships = (row * col) / 2;
        jLabelShip1Count.setText(String.valueOf(this.ships));
    }

    public void handlerSizeMatrix(String data) {
        try {
            Map<String, String> map = Protocol.formatSizeMatrix(data);
            loadMatrix(Integer.parseInt(map.get("MATRIXFILA")), Integer.parseInt(map.get("MATRIXCOLUMNA")));
        } catch (ErrorFormatException ex) {
            System.out.println(ex);
        }
    }

    public void handlerCountOnline(String data) {
        try {
            Map<String, String> map = Protocol.formatCountOnline(data);
            int count = Integer.parseInt(map.get("COUNT"));
            jLabelCountOnline.setText(String.valueOf(count));
        } catch (ErrorFormatException ex) {
            System.out.println(ex);
        }
    }

    public void handlerNewUser() {
        int countOnline = Integer.parseInt(jLabelCountOnline.getText());
        jLabelCountOnline.setText(String.valueOf(countOnline + 1));
    }

    public void handlerConfirmPlay() {
        int confirm = Integer.parseInt(jLabelConfirm.getText());
        jLabelConfirm.setText(String.valueOf(confirm + 1));
    }

    public void handlerInitPlay() {
        GridLayout board = (GridLayout) panelRoundedGrid.getLayout();
        main.loadScreen(new Game(main, board.getRows(), board.getColumns()));
    }

    @Override
    public void OnData(String data) {
        switch (Protocol.getFormat(data)) {
            case "SIZE_MATRIX":
                handlerSizeMatrix(data);
                break;
            case "COUNT_ONLINE":
                handlerCountOnline(data);
                break;
            case "NEW_USER":
                handlerNewUser();
                break;
            case "CONFIRM_PLAY":
                handlerConfirmPlay();
                break;
            case "INIT_PLAY":
                handlerInitPlay();
                break;
            default:
                System.out.println(LOG + ": form data incorrect!!!");
                break;
        }
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
