/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.lobby;

import com.connutils.Request;
import com.gui.principalframe.PrincipalFrame;

/**
 *
 * @author migue
 */
public class LobbyBuilder {
    Lobby creatingLobby;

    public LobbyBuilder(PrincipalFrame principalFrame, Request createRequest) {
        creatingLobby = new Lobby(principalFrame, createRequest);
    }
    
    public Lobby build(){
        return creatingLobby;
    }
    
    public static LobbyBuilder createLobby(PrincipalFrame principalFrame, Request createRequest){
        return new LobbyBuilder(principalFrame, createRequest);
    }
}
