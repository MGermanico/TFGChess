/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.impl;

import com.db.service.PlayerService;
import com.db.dbUtils.HibernateUtils;
import com.db.dbUtils.PasswordUtils;
import com.db.pojo.Player;
import com.db.pojo.PlayerFriend;
import com.db.pojo.keys.PlayerFriendKey;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author migue
 */


public class PlayerImpl implements PlayerService {
    
    @Override
    public Player getPlayerByUsername(String username) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        
        Query<Player> query = session.createQuery("FROM Player WHERE username = :username", Player.class);
        query.setParameter("username", username);
        
        Player player = query.uniqueResult();
        
        session.close();
        
        return player;
    }
    
    @Override
    public boolean registerPlayer(String username, String password) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("registrando: " + password + " -> " + PasswordUtils.hashPassword(password));
            Player player = new Player(username, PasswordUtils.hashPassword(password));
            session.persist(player);

            transaction.commit();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isUsernameTaken(String username) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Player player = session.createQuery("FROM Player WHERE username = :username", Player.class)
                    .setParameter("username", username)
                    .uniqueResult();
            return player != null;
        }
    }
}
