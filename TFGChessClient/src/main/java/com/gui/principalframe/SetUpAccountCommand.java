/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.principalframe;

import com.gui.account.Account;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author migue
 */
public class SetUpAccountCommand extends SetUpCommand{
    public SetUpAccountCommand() {
        super(new Dimension(550, 338));
    }

    @Override
    protected JPanel action(PrincipalFrame frame) {
        return new Account(frame);
    }
}
