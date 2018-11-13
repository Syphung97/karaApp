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
import javax.swing.JButton;
import model.Room;
import model.User;
import server.ServerControl;
import view.ListRoomFrm;
import view.RoomFrm;

/**
 *
 * @author Tran Tuan Anh
 */
public class ListRoomControl {
    ListRoomFrm view;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    
    static final String host = "localhost";
    
    public ListRoomControl(ListRoomFrm view){
        
        this.view = view;
        
        //get list room
        getListRoom();
        

    }
    
    public void getListRoom(){
        try {
            Socket client = new Socket(host, ServerControl.serverPortRoom);
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
            
            oos.writeObject(new Integer(0));
            
            try {
                ArrayList<Room> listRoom = new ArrayList<>();
                Integer size = (Integer)ois.readObject();
                for(int i=0; i<size; i++){
                    listRoom.add((Room)ois.readObject());
                }
                this.view.listRoom = listRoom;
                this.view.addJoinBtn();
                for (JButton joinBtn : this.view.listJoinBtn) {
                    joinBtn.addActionListener(new ButtonListener());
                }
            } catch (IOException | ClassNotFoundException e) {
            }
        } catch (IOException e) {
        }
    }
    
    public class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btnClicked = (JButton) e.getSource();
            for (int i = 0; i < view.listJoinBtn.size(); i++) {
                if (btnClicked.equals(view.listJoinBtn.get(i))) {
                    Room room = view.listRoom.get(i);
                    
                    
                    try {
                        Socket client = new Socket(host, ServerControl.serverPortRoom);
                        oos = new ObjectOutputStream(client.getOutputStream());
                        ois = new ObjectInputStream(client.getInputStream());
                        
                        //SAVE USER THROUGH EACH FRM......
                                    
                        oos.writeObject(new Integer(1));    //key
                        oos.writeObject(new Integer(1));    //user.getID() <------
                        oos.writeObject(new Integer(room.getID())); //roomID
                        
                        new RoomControl(room);
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                }
            }
        }
        
    }
    
    public static void main(String[] args) {

        ListRoomFrm view = new ListRoomFrm();
        ListRoomControl control = new ListRoomControl(view);
        view.setVisible(true);

    }
}
