/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general.principalframe;

import com.gui.lobby.CreateGame;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public final class SetUpCreateGameCommand extends SetUpCommand{

    public SetUpCreateGameCommand() {
        super(new Dimension(550, 338));
    }

    @Override
    protected JPanel action(PrincipalFrame frame) {
        return new CreateGame(frame);
    }
    
}
