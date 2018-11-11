/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import server.DAO.AccountDAO;

/**
 *
 * @author Sy Phung
 */
public class handleSignUp extends Thread{
    Socket s;

    public handleSignUp(Socket socket) {
        this.s = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            Account a = (Account) ois.readObject();
            AccountDAO aO = new AccountDAO();
            boolean b = aO.insert(a);
            if (b) {
                dos.writeBoolean(true);
            }else{
                dos.writeBoolean(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(handleSignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(handleSignUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(handleSignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServerControl.removeSU(this);
    }
    
    
    
}
