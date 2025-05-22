/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general.principalframe;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author migue
 */
class SetUpFactory {

    private static Map<PrincipalFrame.SETUP, SetUpCommand> commands = new HashMap<>(){{
        put(PrincipalFrame.SETUP.SETUP_LOGIN, new SetUpLoginCommand());
        put(PrincipalFrame.SETUP.SETUP_REGISTER, new SetUpRegister());
        put(PrincipalFrame.SETUP.SETUP_MENU, new SetUpMenuCommand());
        put(PrincipalFrame.SETUP.SETUP_CREATE_GAME, new SetUpCreateGameCommand());
        put(PrincipalFrame.SETUP.SETUP_JOIN_GAME, new SetUpJoinGameCommand());
    }};
    
    static Optional<SetUpCommand> getSetUpCommand(PrincipalFrame.SETUP type) {
        return Optional.ofNullable(commands.get(type));
    }
    
}
