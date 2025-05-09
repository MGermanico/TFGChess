/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exec.server;

import com.db.dbUtils.PasswordUtils;
import static com.db.dbUtils.PasswordUtils.hashPassword;
import static com.db.dbUtils.PasswordUtils.verifyPassword;
import com.db.pojo.Player;
import com.db.impl.PlayerImpl;

/**
 *
 * @author migue
 */
public class login {
    public static void main(String[] args) {
        PlayerImpl playerImpl = new PlayerImpl();
        
        playerImpl.registerPlayer("pepe", "1234");
        
        Player player = playerImpl.getPlayerByUsername("pepe");
        System.out.println("player: " + player);
        if (PasswordUtils.verifyPassword("1234", player.getPasswordHash())) {
            System.out.println("BIEN");
        }else{
            System.out.println("MAL");
        }
    }
}
