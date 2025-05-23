/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.principalframe;

import static com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.back;
import com.gui.login.Login;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public abstract class SetUpCommand {

    protected Dimension frameSize;
    public SetUpCommand(Dimension frameSize) {
        this.frameSize = frameSize;
    }
    
    public void execute(PrincipalFrame frame){
        //Dimension frameSize = new Dimension(1000, 700);
        frame.back.removeAll();
        
        frame.back.add(action(frame));
        
        frame.setSize(frameSize);
        frame.setLocationRelativeTo(null);
        frame.back.validate();
        frame.back.repaint();
    }
    protected abstract JPanel action(PrincipalFrame frame);
}
