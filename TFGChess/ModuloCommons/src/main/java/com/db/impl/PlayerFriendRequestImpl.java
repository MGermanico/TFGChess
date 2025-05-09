/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.db.impl;

import com.db.DatabaseManager;
import com.db.dbUtils.HibernateUtils;
import com.db.pojo.Player;
import com.db.pojo.PlayerFriend;
import com.db.pojo.PlayerFriendRequest;
import com.db.pojo.keys.PlayerFriendKey;
import com.db.service.PlayerFriendRequestService;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author migue
 */


public class PlayerFriendRequestImpl implements PlayerFriendRequestService {

    @Override
    public List<Player> getFriendRequestsByUsername(String username) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        
        Query<PlayerFriendRequest> query = session.createQuery("FROM PlayerFriendRequest WHERE id.username = :username", PlayerFriendRequest.class);
        query.setParameter("username", username);
//        
        List<PlayerFriendRequest> middleFriendList = query.getResultList();
//        
        session.close();
        
        List<Player> friends = new LinkedList<>();
        
        Player tmpFriend;
        for (PlayerFriendRequest playerFriend : middleFriendList) {
            tmpFriend = DatabaseManager.getPlayerByUsername(playerFriend.getId().getFriendUsername());
            if(tmpFriend != null)
                friends.add(tmpFriend);
        }
        
        return friends;
    }
    
    @Override
    public List<Player> getFriendRequestsReceivedByUsername(String username) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        
        Query<PlayerFriendRequest> query = session.createQuery("FROM PlayerFriendRequest WHERE id.friendUsername = :username", PlayerFriendRequest.class);
        query.setParameter("username", username);
//        
        List<PlayerFriendRequest> middleFriendList = query.getResultList();
//        
        session.close();
        
        List<Player> friends = new LinkedList<>();
        
        Player tmpFriend;
        for (PlayerFriendRequest playerFriend : middleFriendList) {
            tmpFriend = DatabaseManager.getPlayerByUsername(playerFriend.getId().getUsername());
            if(tmpFriend != null)
                friends.add(tmpFriend);
        }
        
        return friends;
    }

    @Override
    public synchronized boolean addFriendRequestByUsernames(String username, String friendUsername) {
        if (username.equals(friendUsername) || !DatabaseManager.isUsernameTaken(username) || !DatabaseManager.isUsernameTaken(friendUsername)) 
            return false;
        
        
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            PlayerFriendRequest playerFriend = new PlayerFriendRequest(new PlayerFriendKey(username, friendUsername));
            session.persist(playerFriend);

            transaction.commit();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeFriendRequestByUsernames(String username, String friendUsername) {
        if (!DatabaseManager.isUsernameTaken(username) || !DatabaseManager.isUsernameTaken(friendUsername)) 
            return false;
        
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            PlayerFriendRequest playerFriend = new PlayerFriendRequest(new PlayerFriendKey(username, friendUsername));
            session.remove(playerFriend);

            transaction.commit();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
