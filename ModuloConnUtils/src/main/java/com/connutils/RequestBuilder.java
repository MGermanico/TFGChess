/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connutils;


/**
 *
 * @author migue
 */
public class RequestBuilder {
    private Request buildingRequest;

    public RequestBuilder(String header) {
        buildingRequest = new Request(header);
    }
    
    public RequestBuilder put(String name, String value){
        buildingRequest.put(name, value);
        return this;
    }
    
    public Request build(){
        return buildingRequest;
    }
    
    public static RequestBuilder createRequest(String header){
        return new RequestBuilder(header);
    }
}
