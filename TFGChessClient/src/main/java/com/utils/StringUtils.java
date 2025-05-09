/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utils;

import java.awt.FontMetrics;

/**
 *
 * @author migue
 */
public class StringUtils {
    public static String maximumLongSize(String text, int[] nLines, int maxWidth, FontMetrics metrics){
        int textLength = text.length();
        StringBuilder fullStrBuilder = new StringBuilder();
        StringBuilder strBuilder = new StringBuilder();
        
        int adder = 0;
        for (int i = 0; i < textLength; i ++) {
            strBuilder.append(text.charAt(i));
            int actualWidth = metrics.stringWidth(strBuilder.toString());
            if (actualWidth <= maxWidth + adder) {
                
            }else{
                adder = strBuilder.length();
                fullStrBuilder.append("<br>").append(strBuilder.toString());
                nLines[0]++;
                strBuilder = new StringBuilder();
                System.out.println("OUT:    " + actualWidth + "  ,  " + (maxWidth + adder) + " -> " + fullStrBuilder.toString());
            }
        }
        fullStrBuilder.append("<br>").append(strBuilder.toString());
        nLines[0]++;
        System.out.println(" -> " + fullStrBuilder.toString());
        return fullStrBuilder.toString();
    }
    
    public static String addChar(String text, char c, int position){
        return text.substring(position) + c + text.substring(0, position);
    }
}
