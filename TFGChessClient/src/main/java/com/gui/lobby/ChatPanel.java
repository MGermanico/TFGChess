/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.gui.lobby;

import com.utils.StringUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author migue
 */
public class ChatPanel extends javax.swing.JPanel {

    /**
     * Creates new form Text
     */
    public ChatPanel() {
        initComponents();
        this.setSize(270, 1600);
    }
    
    public void addText(String title, String text, Color color) {
        JPanel textPanel = new JPanel();      
        int[] nLines = new int[]{1};
        
        int extraSize = 0;
        String titleText = "";
        if (title != null && !title.isBlank()) {
            titleText = "<span style=\"font-weight: bold; font-size: large;\">"+ title +"</span><br>";
            extraSize += 57;
        }
        
        JLabel textLabel = new JLabel();
        FontMetrics metrics = getFontMetrics(textLabel.getFont());
        textLabel.setText("<html>" + titleText + StringUtils.maximumLongSize(text, nLines, 170, metrics) + "</html>");
        textLabel.setHorizontalAlignment(SwingConstants.LEFT);
        textPanel.setBackground(color);
        Dimension labelDimension = new Dimension(230, 17*nLines[0] + extraSize - 20);
        Dimension panelDimension = new Dimension(250, 17*nLines[0] + extraSize);
        textLabel.setSize(labelDimension);
        textLabel.setPreferredSize(labelDimension);
        textLabel.setMaximumSize(labelDimension);
        textLabel.setMinimumSize(labelDimension);
        
        textPanel.setSize(panelDimension);
        textPanel.setPreferredSize(panelDimension);
        textPanel.setMaximumSize(panelDimension);
        textPanel.setMinimumSize(panelDimension);
        textPanel.add(textLabel);
        textPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.add(textPanel, 0);
        
        Dimension spaceDimension = new Dimension(250, 10);
        JPanel space = new JPanel();
        space.setBackground(Color.PINK);
        space.setSize(spaceDimension);
        space.setPreferredSize(spaceDimension);
        space.setMaximumSize(spaceDimension);
        space.setMinimumSize(spaceDimension);
        this.add(space, 0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 0, 0));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.Y_AXIS));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
