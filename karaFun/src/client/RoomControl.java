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
import java.util.ArrayList;
import model.Room;
import model.User;
import server.ServerControl;
import view.ListOnlineFrm;
import view.RoomFrm;

/**
 *
 * @author Tran Tuan Anh
 */
public class RoomControl {
    RoomFrm view;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    
    static final String host = "localhost";
    
    public RoomControl(Room room){
        
        this.view = new RoomFrm(room);
        
        this.view.addInviteBtnListener(new InviteButton());
        
        //get list user
        getListUser();
        
        
        
    }
    
    public void getListUser(){
        try {
            Socket client = new Socket(host, ServerControl.serverPortRoom);
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
            
            oos.writeObject(new Integer(2)); //key get list user
            oos.writeObject(new Integer(this.view.room.getID()));
            try {
                ArrayList<User> listUser = new ArrayList<>();
                Integer size = (Integer)ois.readObject();
                for(int i=0; i<size; i++){
                    listUser.add((User)ois.readObject());
                }
                this.view.listUsers = listUser;
                
                view.setVisible(true);
                
            } catch (IOException | ClassNotFoundException e) {
            }
        } catch (IOException e) {
        }
    }
    
    class InviteButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<User> listUsers = new ArrayList<>();
            (new ListOnlineFrm()).setVisible(true);
        }
        
    }
    
    
}
