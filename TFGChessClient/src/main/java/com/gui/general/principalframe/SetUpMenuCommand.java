/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general.principalframe;

import com.gui.general.menu.Menu;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public final class SetUpMenuCommand extends SetUpCommand{

    public SetUpMenuCommand() {
        super(new Dimension(1070, 700));
    }

    @Override
    protected JPanel action(PrincipalFrame frame) {
        return new Menu(frame);
    }
    
}
