/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import model.Account;
import model.User;
import server.DAO.AccountDAO;

/**
 *
 * @author Sy Phung
 */
public class handleLogin extends Thread{
    Socket socket;

    public handleLogin(Socket s) throws IOException {
        this.socket = s;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            Account a = (Account) ois.readObject();

            
            Account a1 = new AccountDAO().getAccount(a.getUsername());
            
            if(a1 != null && a.getPassword().equals(a1.getPassword())){
               oos.writeObject(a1); 
                User s = new User(false,"","",null, a1);
                ArrayList<Socket> al = new ArrayList<>();
                Pair p = new Pair(s, al);
                ServerControl.list.add(p);
            }        
            else{
                oos.writeObject(null);
            }
        } catch (IOException ex) {
            Logger.getLogger(handleLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(handleLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(handleLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServerControl.remove(this);
    }
    
    
    
}
