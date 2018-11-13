
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.User;

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
        loginFrm.addBtnSignupListener(new SignUp());
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
                    ois.close();
                    oos.close();
                    socket.close();
                }else{

                    loginFrm.showMessage("Login successful");
                    
                    ois.close();
                    oos.close();
                    socket.close();
                    loginFrm.setVisible(false);
                   // sau khi login  
                    new InviteControl(new InviteFrm(), a2).start();
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
    }
    class SignUp implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpFrm frm = new SignUpFrm();
                
                frm.addBtnSubmitListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Socket s = new Socket("localhost",11111);
                            try {
                                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                                DataInputStream dis = new DataInputStream(s.getInputStream());
                                Account a = frm.getAccount();
                                if(a!=null){
                                    oos.writeObject(a);
                                    Boolean result = dis.readBoolean();
                                    if(result == true){
                                        frm.showMessage("Sign up success");
                                    }else{
                                        frm.showMessage("Sign up fail");
                                    }
                                    oos.close();
                                    dis.close();
                                    s.close();
                                }else{
                                    oos.close();
                                    dis.close();
                                    s.close();
                                }
                            } catch (IOException ex) {
                                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                frm.setVisible(true);
            }
            
        }
    public static void main(String[] args) throws IOException {
        LoginFrm frm = new LoginFrm();
        ClientControl c = new ClientControl("localhost", 10000, frm);
        frm.setVisible(true);
    }
}

