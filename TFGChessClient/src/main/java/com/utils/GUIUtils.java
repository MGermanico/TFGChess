/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    public static JButton addDot(JButton cellButton, Color color, boolean eating){
        BufferedImage background;
        
        if (eating) {
            background = iconToBufferedImage(cellButton.getIcon());
        }else{
            background = new BufferedImage(cellButton.getWidth(), cellButton.getHeight(), BufferedImage.TYPE_INT_ARGB);
        }
        
        int width = background.getWidth();
        int height = background.getHeight();

        BufferedImage mixed = new BufferedImage(
            width, height, BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D mixedGraphics = mixed.createGraphics();
        BufferedImage dot = new BufferedImage(
            width, height, BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D dotGraphics = dot.createGraphics();
        dotGraphics.setColor(color);
        if (eating) {
            dotGraphics.setStroke(new BasicStroke(4));
            dotGraphics.drawOval((int) (width/6), (int) (height/6), (int) (background.getWidth()/1.5), (int) (height/1.5));
        }else{
            dotGraphics.fillOval((int) (width/2.5), (int) (height/2.5), background.getWidth()/5, height/5);
        }
        
        dotGraphics.dispose();
        
        
        mixedGraphics.drawImage(background, 0, 0, null); 
        mixedGraphics.drawImage(dot, 0, 0, null); 
        mixedGraphics.dispose();
        
        
        cellButton.setIcon(new ImageIcon(mixed));
       
//        button.setBackground(cellButton.getBackground());
//        button.setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
        
        return cellButton;
    }
    
    private static BufferedImage iconToBufferedImage(Icon icon) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        return image;
    }
    
    public static String biggerTextHTML(String text){
        return "<html><p style=\"font-weight: bold; font-size: 30px;\">" + text + "</p></html>";
    }
}
