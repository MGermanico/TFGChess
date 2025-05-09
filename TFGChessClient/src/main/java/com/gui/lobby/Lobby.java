/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.gui.lobby;

import com.chess.general.ChessManager;
import com.chess.general.Chessboard;
import com.chess.general.Position;
import com.chess.pieces.DisplayableCell;
import com.chess.pieces.Piece;
import com.connutils.Request;
import com.gui.general.Requestable;
import com.conn.Client;
import com.connutils.Action;
import com.connutils.RequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gui.general.PrincipalFrame;
import com.utils.ChessUtils;
import com.utils.GUIUtils;
import com.utils.MathUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author migue
 */
public class Lobby extends javax.swing.JPanel implements Requestable{

    /**
     * Creates new form Game
     */
    
    private PrincipalFrame principalFrame;
    private String otherUsername;
    private ChessManager chessManager = null;
    private boolean imWhite = false;
    private int cellSize = 45;
    private ChatPanel logPanel;
    private ChatPanel chatPanel;
    private Position lastPositionClicked = null;
    
    private List<Action> actions = new ArrayList<Action>(){{
        add(new PlayerJoinedAction());
        add(new JoinedAction());
        add(new UpdateBoardAction());
    }};
    
    public Lobby(PrincipalFrame principalFrame, Request createRequest) {
//        principalFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
//        System.out.println(principalFrame.getSize());
        principalFrame.setSize((int) (cellSize * 22.776), (int) (cellSize * 12.423));
        this.otherUsername = "J2 (no-name)";
        this.principalFrame = principalFrame;
        initComponents();
        GUIUtils.configureDefaultMessageOnTextFields(List.of(this.commentTextField));
        this.boardPanel.setBorder(BorderFactory.createLineBorder(principalFrame.getThemeColor(), 4));
        
        gotRequest(createRequest);
        
        this.firstPlayerLabel.setText(Client.getUsername());

        logPanel = new ChatPanel();
        this.leftPanel.add(logPanel);
        
        chatPanel = new ChatPanel();
        this.rightPanel.add(chatPanel);
    }
    
    private void updateBoard(){
        this.boardPanel.setLayout(new GridLayout(10, 10,0,0));
        
        int numberOfComponents = this.boardPanel.getComponentCount();
        for (int i = 0; i < numberOfComponents; i++) 
            this.boardPanel.remove(0);
        
        if (chessManager != null) {
            Chessboard board = chessManager.getChessboard();
            for (int i = -1; i < 9; i++) {
                for (int j = -1; j < 9; j++) {
                    int row = i;
                    int col = j;
                    int letter = j;
                    if (imWhite) {
                        col = 7-j;
                        row = 7-i;
                    }else{
                        letter = 7 - j;
                    }
                    if(
                            (i == -1 && j == -1)
                            || (i == -1 && j == 8)
                            || (i == 8 && j == -1)
                            || (i == 8 && j == 8)
                            ){
                        JPanel corner = new JPanel(new GridBagLayout());
                        corner.setBackground(principalFrame.getThemeColor());
                        corner.setPreferredSize(new Dimension(cellSize, cellSize));
                        this.boardPanel.add(corner);
                    }else if(i == -1 || i == 8){
                        int borderSize = 9;
                        JPanel sidePanel = GUIUtils.createPanelWithImageIcon(cellSize, cellSize, "src/main/resources/icons/iconBorder/" + (letter + 1) + "L.png", cellSize/2, cellSize/2);
                        sidePanel.setBorder(new javax.swing.border.MatteBorder(borderSize, 0, borderSize, 0, Color.GRAY));
                        sidePanel.setBackground(new Color(200, 200, 200));
                        this.boardPanel.add(sidePanel);
                    } else if (j == -1 || j == 8) {
                        int borderSize = 9;
                        JPanel sidePanel = GUIUtils.createPanelWithImageIcon(cellSize, cellSize, "src/main/resources/icons/iconBorder/" + (row + 1) + "N.png", cellSize/2, cellSize/2);
                        sidePanel.setBorder(new javax.swing.border.MatteBorder(0, borderSize, 0, borderSize, Color.GRAY));
                        sidePanel.setBackground(new Color(200, 200, 200));
                        this.boardPanel.add(sidePanel);
                    }else{
                        updateCell(board, row, col);
                    }
                }
            }
            this.validate();
        }
    }
    
    private void updateCell(Chessboard board, int row, int col) {
        DisplayableCell cell = board.getBoard()[col][row];
        JButton cellButton = new JButton(ChessUtils.getCellIcon(cell, (int) (cellSize*1.25), (int)(cellSize*1.125)));
        cellButton.setPreferredSize(new Dimension(cellSize, cellSize));

        if (MathUtils.isPair(row) && !MathUtils.isPair(col) 
         || MathUtils.isPair(col) && !MathUtils.isPair(row)) {
            cellButton.setBackground(Color.WHITE);
        } else {
            cellButton.setBackground(principalFrame.getThemeColor());
        }

        cellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cellAction(row, col);
            }
        });
        
        cellButton.setBorder(BorderFactory.createEmptyBorder());
        this.boardPanel.add(cellButton);
    }
    
    private void cellAction(int row, int col) {
        if (lastPositionClicked == null) {
            lastPositionClicked = new Position(row, col);
        }else{
            System.out.println(lastPositionClicked + " -> " + new Position(row, col));
            sendMove(lastPositionClicked, new Position(row, col));
            lastPositionClicked = new Position(row, col);
        }
    }
    
    private void sendMove(Position from, Position to){
        try {
            Client.sendRequest(
                    RequestBuilder.createRequest("move")
                            .put("from", from.toJSON())
                            .put("to", to.toJSON())
                            .build()
            );
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rightPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        secondPlayerLabel = new javax.swing.JLabel();
        boardPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        firstPlayerLabel = new javax.swing.JLabel();
        commentTextField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setMinimumSize(new java.awt.Dimension(50, 50));

        rightPanel.setBackground(new java.awt.Color(0, 0, 0));
        rightPanel.setMinimumSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        leftPanel.setBackground(new java.awt.Color(0, 0, 0));
        leftPanel.setMinimumSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        secondPlayerLabel.setBackground(new java.awt.Color(255, 255, 255));
        secondPlayerLabel.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        secondPlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        secondPlayerLabel.setText("J2");
        secondPlayerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        secondPlayerLabel.setOpaque(true);

        boardPanel.setBackground(new java.awt.Color(255, 153, 153));
        boardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Esperando Rival...");

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, boardPanelLayout.createSequentialGroup()
                .addContainerGap(277, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(249, 249, 249))
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, boardPanelLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        firstPlayerLabel.setBackground(new java.awt.Color(255, 255, 255));
        firstPlayerLabel.setFont(new java.awt.Font("Yu Gothic", 1, 18)); // NOI18N
        firstPlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstPlayerLabel.setText("J1");
        firstPlayerLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        firstPlayerLabel.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(secondPlayerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(boardPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstPlayerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(secondPlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(firstPlayerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        commentTextField.setText("Escribe un mensaje...");
        commentTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                commentTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(commentTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(commentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void commentTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_commentTextFieldActionPerformed
        this.chatPanel.addText(Client.getUsername(), this.commentTextField.getText(), Color.BLUE);
        this.commentTextField.setText("");
        this.chatPanel.validate();
    }//GEN-LAST:event_commentTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    private javax.swing.JTextField commentTextField;
    private javax.swing.JLabel firstPlayerLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel secondPlayerLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void gotRequest(Request request) {
        for (Action action : actions) {
            if(action.getType().equals(request.getHeader()))
                action.execute(request);
        }
    }
    
    private class PlayerJoinedAction implements Action{
        @Override
        public void execute(Request request) {
            otherUsername = request.getOrDefault("username", "J2 (no-name)");
            secondPlayerLabel.setText(otherUsername);
        }
        @Override
        public String getType() {
            return "playerjoined";
        }
    }
    private class JoinedAction implements Action{
        @Override
        public void execute(Request request) {
            otherUsername = request.getOrDefault("ownerusername", "J2 (no-name)");
            secondPlayerLabel.setText(otherUsername);
        }
        @Override
        public String getType() {
            return "joined";
        }
    }
    private class UpdateBoardAction implements Action{
        @Override
        public void execute(Request request) {
            try {
                chessManager = ChessManager.fromJSON(request.get("board"));
                imWhite = request.get("white").equals("true");
                Color themeColor = principalFrame.getThemeColor();
                if (imWhite) {
                    secondPlayerLabel.setBackground(themeColor);
                    secondPlayerLabel.setForeground(Color.LIGHT_GRAY);
                    secondPlayerLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));
                    
                    firstPlayerLabel.setBackground(Color.LIGHT_GRAY);
                    firstPlayerLabel.setForeground(themeColor);
                    firstPlayerLabel.setBorder(BorderFactory.createLineBorder(themeColor, 4));
                }else{
                    secondPlayerLabel.setBackground(Color.LIGHT_GRAY);
                    secondPlayerLabel.setForeground(themeColor);
                    secondPlayerLabel.setBorder(BorderFactory.createLineBorder(themeColor, 4));
                    
                    firstPlayerLabel.setBackground(themeColor);
                    firstPlayerLabel.setForeground(Color.LIGHT_GRAY);
                    firstPlayerLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4));
                }
                updateBoard();
                principalFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(Lobby.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        @Override
        public String getType() {
            return "updatedBoard";
        }
    }
}
