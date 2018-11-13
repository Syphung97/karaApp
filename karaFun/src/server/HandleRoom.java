/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controller.RoomDAO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Room;
import model.User;

/**
 *
 * @author Tran Tuan Anh
 */
public class HandleRoom extends Thread{
    Socket socket;
    RoomDAO roomDAO;

    public HandleRoom(Socket socket) {
        this.socket = socket;
        this.roomDAO = new RoomDAO();
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            Integer key = (Integer) ois.readObject();
            
            //0 : get list room
            //1 : join room
            //2 : get list user in room
            
            if(key == 0) {
                System.out.println("Get list room");
                try {
                    ArrayList<Room> list = roomDAO.getAllRoom();
                    oos.writeObject(new Integer(list.size()));
                    for(Room room : list){
                        oos.writeObject(room);
                        System.out.println("roomID " + room.getID());
                    }
                } catch (IOException e) {
                    
                }
            }
            
            if(key == 1) {
                System.out.println("Join room");
                try {
                    Integer idUser = (Integer) ois.readObject();
                    Integer idRoom = (Integer) ois.readObject();
                    roomDAO.joinRoom(idUser, idRoom);
                } catch (IOException | ClassNotFoundException e) {
                }
            }
            
            if(key == 2) {
                System.out.println("Get list user in room");
                try {
                    Integer roomID = (Integer) ois.readObject();
                    ArrayList<User> list = roomDAO.listUsersInRoom(roomID);
                    oos.writeObject(new Integer(list.size()));
                    for(User u : list){
                        oos.writeObject(u);
                        System.out.println("user name "+u.getName());
                    }
                    
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                }
//            
//            if(key == 5) {
//                System.out.println("Search");
//                String search = (String) ois.readObject();
//                ArrayList<Song> searchSong = songDAO.searchSong(search);
//                oos.writeObject(new Integer(searchSong.size()));
//                for (Song song : searchSong) {
//                    oos.writeObject(song);
//                }
//            }
            
        } catch (IOException ex) {
            Logger.getLogger(HandleSong.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HandleSong.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServerControl.listHanRoom.remove(this);
    }
    
    
}
