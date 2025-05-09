/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connutils;


/**
 *
 * @author migue
 */
public interface Action {
    public abstract void execute(Request request);

    public abstract String getType();
}
