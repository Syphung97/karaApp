/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Sy Phung
 */
public class ClientControl {
 

    LoginFrm loginFrm;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public ClientControl(String address, int port, LoginFrm view) throws IOException {

        

        
        this.loginFrm = view;
        loginFrm.addBtnLoginListener(new Login());
    }
    
    class Login implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Account acc = loginFrm.getAccount();
            try {

                Socket socket = new Socket("localhost", 10000);

                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                oos.writeObject(acc);
                Account a2 = (Account) ois.readObject();
                if(a2 == null){
                    loginFrm.showMessage("Account not valid! Login fail");
                }else{

                    loginFrm.showMessage("Login successful");

                }
            } catch (IOException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public static void main(String[] args) throws IOException {
        LoginFrm frm = new LoginFrm();
        ClientControl c = new ClientControl("localhost", 10000, frm);
        frm.setVisible(true);
    }
}
