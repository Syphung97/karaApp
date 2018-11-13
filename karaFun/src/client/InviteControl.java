/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import view.InviteFrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Invitation;
import model.User;

/**
 *
 * @author Sy Phung
 */
public class InviteControl extends Thread {

    InviteFrm view;
    Account account;

    public InviteControl(InviteFrm view, Account account) {
        this.view = view;
        this.account = account;

    }

    class addBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nameReceiver = view.getName();

            if (nameReceiver != null) {
                try {
                    Socket s = new Socket("localhost", 9998);
//                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    ObjectOutputStream oo = new ObjectOutputStream(s.getOutputStream());
                    oo.writeObject(new Invitation(account, nameReceiver));
                    // Mỗi khi click thì gửi lời mời
                    DataInputStream diss = new DataInputStream(s.getInputStream());
                    String ss = diss.readUTF();
                    System.out.println(ss);
                } catch (IOException ex) {
                    Logger.getLogger(InviteControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @Override
    public void run() {
        view.addActionListener(new addBtnListener());
        view.setVisible(true);

        try {
            // Gửi account vào cổng đăng kí
            Socket socket = new Socket("localhost", 9999);
            Socket rec = new Socket("localhost", 9900);

//            System.out.println("a");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(account);
            while (true) {
                // Đọc lời mời 
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataInputStream recInv = new DataInputStream(rec.getInputStream());
                System.out.println(recInv.readUTF());
            }

        } catch (IOException ex) {
            Logger.getLogger(InviteControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
