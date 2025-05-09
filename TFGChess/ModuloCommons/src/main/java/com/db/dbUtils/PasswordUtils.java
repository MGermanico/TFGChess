/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.dbUtils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author migue
 */

public class PasswordUtils {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public static void main(String[] args) {
        String c = "1234";
        String hashes = hashPassword(c);
        System.out.println("contraseña normal: " + c);
        System.out.println("contraseña hash  : " + hashes);
        System.out.println("::               : " + verifyPassword(c, "$2a$10$WDyNiGJbYMk2sWXRVCVFX..X8r/EZWscn9wChZt8pGTcM7wgZoVQK"));
    }

    public static String hashPassword(String password) {
        return encoder.encode(password);
    }

    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}