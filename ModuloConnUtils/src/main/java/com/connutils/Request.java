/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */

public class Request {
    private String header;
    private Map<String, String> body;
    
    public Request() {
    }
    
    public Request(String header, Map<String, String> body) {
        this.header = header;
        if (body != null) {
            this.body = body;
        }else{
            this.body = new HashMap<>();
        }
    }
    public Request(String header) {
        this(header, null);
    }
    
    public static Request fromJSON(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Request request = objectMapper.readValue(json, Request.class);
        return request;
    }
    
    public void put(String key, String value){
        body.put(key, value);
    }
    
    public String get(String key){
        if(!body.containsKey(key)) return null;
        return body.get(key);
    }
    
    public boolean contains(String key){
        return body.containsKey(key);
    }
    
    public String getOrDefault(String key, String defValue){
        String value = get(key);
        if(value == null) return defValue;
        return value;
    }
    
    public String toJSON(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getHeader() {
        return header;
    }

    public Map<String, String> getBody() {
        return body;
    }
    
    @Override
    public String toString() {
        return "cabecera : " + header + 
             "\nbody     : " + body;
    }
}
