/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gui.general.menu;

import com.connutils.Action;
import com.connutils.Request;
import com.connutils.chatlog.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
class ChatLogAction implements Action {

    private Menu menu;

    public ChatLogAction(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute(Request request) {
        if (!(!request.contains("with") || !request.contains("chatlog"))) {
            String otherUsername = request.get("with");
            if (this.menu.chat != null && this.menu.chat.getOtherUsername().equals(otherUsername)) {
                try {
                    this.menu.chat.updateMessages(new ObjectMapper().readValue(request.get("chatlog"), new TypeReference<LinkedList<Message>>() {
                    }));
                } catch (JsonProcessingException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.menu.printRendererBorder(otherUsername);
            }
        }
    }

    @Override
    public String getType() {
        return "chatlog";
    }
}
