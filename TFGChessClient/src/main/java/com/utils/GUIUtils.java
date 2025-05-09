/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author migue
 */
public class GUIUtils {
    public static ImageIcon resizeImageIcon(ImageIcon img, int width, int height){
        return new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
    public static JPanel createPanelWithImageIcon(int panelWith, int panelHeight, String imagePath, int imageWidth, int imageHeight){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(new JLabel(resizeImageIcon(new ImageIcon(imagePath), imageWidth, imageHeight)));
        panel.setPreferredSize(new Dimension(panelWith, panelHeight));
        return panel;
    }
    public static void configureDefaultMessageOnTextFields(List<JTextField> textFields){
        for(JTextField field : textFields){
            field.addFocusListener(new FocusListener() {
                String defaultText = field.getText();
                @Override
                public void focusGained(FocusEvent e) {
                    if (field.getText().equals(defaultText)) {
                        field.setText("");
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (field.getText().isBlank()) {
                        field.setText(defaultText);
                    }
                }
            });
        }
    }
}
