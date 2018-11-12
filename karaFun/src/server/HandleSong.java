/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controller.SongDAO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Song;

/**
 *
 * @author Hau Nguyen
 */
public class HandleSong extends Thread {

    Socket socket;
    SongDAO songDAO;
    // KEY
    // Show list song: 1
    // Add Song: 2
    // Update Song: 3
    // Delete Song: 4
    // Search Song: 5
    
    public HandleSong(Socket socket) {
        this.socket = socket;
        songDAO = new SongDAO();
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            Integer key = (Integer) ois.readObject();
            
            if(key == 2) {
                System.out.println("Add");
                Song s = (Song) ois.readObject();
                if(songDAO.addSong(s)) {
                    oos.writeObject(new Boolean(true));
                } else {
                    oos.writeObject(new Boolean(false));
                }
            }
            
            if(key == 3) {
                System.out.println("Update");
                Song s = (Song) ois.readObject();
                if(songDAO.updateSong(s)) {
                    oos.writeObject(new Boolean(true));
                } else {
                    oos.writeObject(new Boolean(false));
                }
            }
            
            if(key == 4) {
                System.out.println("Delete");
                Integer songID = (Integer) ois.readObject();
                if(songDAO.deleteSong(songID)) {
                    oos.writeObject(new Boolean(true));
                } else {
                    oos.writeObject(new Boolean(false));
                }
            }
            
            if(key == 5) {
                System.out.println("Search");
                String search = (String) ois.readObject();
                ArrayList<Song> searchSong = songDAO.searchSong(search);
                oos.writeObject(new Integer(searchSong.size()));
                for (Song song : searchSong) {
                    oos.writeObject(song);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(HandleSong.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HandleSong.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServerControl.listHanSong.remove(this);
    }


}
