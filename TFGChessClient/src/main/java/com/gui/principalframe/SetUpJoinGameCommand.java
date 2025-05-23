/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.principalframe;

import com.gui.joingame.JoinGame;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public final class SetUpJoinGameCommand extends SetUpCommand{

    public SetUpJoinGameCommand() {
        super(new Dimension(550, 338));
    }

    @Override
    protected JPanel action(PrincipalFrame frame) {
        return new JoinGame(frame);
    }
    
}
