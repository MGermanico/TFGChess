/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.impl;

import com.db.DatabaseManager;
import com.db.dbUtils.HibernateUtils;
import com.db.dbUtils.PasswordUtils;
import com.db.pojo.Player;
import com.db.pojo.PlayerFriend;
import com.db.pojo.keys.PlayerFriendKey;
import com.db.service.PlayerFriendService;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author migue
 */


public class PlayerFriendImpl implements PlayerFriendService {

    @Override
    public List<Player> getFriendsByUsername(String username) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        
        Query<PlayerFriend> query = session.createQuery("FROM PlayerFriend WHERE id.username = :username", PlayerFriend.class);
        query.setParameter("username", username);
//        
        List<PlayerFriend> middleFriendList = query.getResultList();
//        
        session.close();
        
        List<Player> friends = new LinkedList<>();
        
        Player tmpFriend;
        for (PlayerFriend playerFriend : middleFriendList) {
            tmpFriend = DatabaseManager.getPlayerByUsername(playerFriend.getId().getFriendUsername());
            if(tmpFriend != null)
                friends.add(tmpFriend);
        }
        
        return friends;
    }

    @Override
    public boolean addFriendByUsernames(String username, String friendUsername) {
        if (!DatabaseManager.isUsernameTaken(username) || !DatabaseManager.isUsernameTaken(friendUsername)) 
            return false;
        
        
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println(username + " está añadiendo amigo: " + friendUsername);
            PlayerFriend playerFriend = new PlayerFriend(new PlayerFriendKey(username, friendUsername));
            session.persist(playerFriend);

            transaction.commit();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeFriendByUsernames(String username, String friendUsername) {
        if (!DatabaseManager.isUsernameTaken(username) || !DatabaseManager.isUsernameTaken(friendUsername)) 
            return false;
        
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println(username + " está borrando de amigo: " + friendUsername);
            PlayerFriend playerFriend = new PlayerFriend(new PlayerFriendKey(username, friendUsername));
            session.remove(playerFriend);

            transaction.commit();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
