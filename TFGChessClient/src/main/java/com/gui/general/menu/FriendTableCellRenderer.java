/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general.menu;

import com.gui.general.menu.Menu;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author migue
 */
public class FriendTableCellRenderer extends DefaultTableCellRenderer {

    private List<Color> rowColors;
    private Set<String> printedBordersUsernames;
    
    public FriendTableCellRenderer(JTable friendTable, List<Friend> friends) {
        printedBordersUsernames = new HashSet<>();
        
        Collections.sort(friends);
        DefaultTableModel model = (DefaultTableModel)friendTable.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) 
            model.removeRow(0);
        
        rowColors = new ArrayList<>();
        for (Friend friend : friends) {
            String[] fila = new String[]{friend.username};
            model.addRow(fila);
            rowColors.add(friend.getColor());
        }
    }
    
    public void printBorder(String username){
        printedBordersUsernames.add(username);
    }
    public void removePrintedBorder(String username){
        System.out.println("borrando alerta de mensaje de: " + username);
        printedBordersUsernames.remove(username);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            // dejamos el color por defecto de selección
            return c;
        }
        
        if (row < rowColors.size()) {
            if (printedBordersUsernames.contains(value.toString())) {
                c.setBackground(rowColors.get(row).brighter());
                c.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2));
            }else{
                c.setBackground(rowColors.get(row));
            }
        } else {
            c.setBackground(Color.WHITE); // color por defecto
        }

        return c;
    }
}
