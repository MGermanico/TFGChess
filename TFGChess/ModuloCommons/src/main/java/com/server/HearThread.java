/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.server;

import com.utils.ServerCode;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import com.connutils.Action;
import com.connutils.Request;
import com.connutils.RequestBuilder;

/**
 *
 * @author migue
 */
class HearThread extends Thread{

    private ClientConn con;

    public HearThread(ClientConn con) {
        this.con = con;
        
    }

    @Override
    public void run() {
        String json;
        Request request;
        BufferedReader in;
        try {
            in = con.getReader();
            while (in != null && !con.isClosed()) {
                try {
                    json = in.readLine();
                    request = Request.fromJSON(json);
                    con.gotRequest(request);
                } catch(java.lang.IllegalArgumentException ex){
                    con.exit(ServerCode.KO);
                } catch (IOException ex) {
                    con.exit(ServerCode.KO);
                }
            }
        } catch (IOException ex) {
            con.exit(ServerCode.KO);
        }
        
    }
}
