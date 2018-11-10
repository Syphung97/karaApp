/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Hau Nguyen
 */
public class Room {
    private ArrayList<Song> listSong;
    private ArrayList<User> listUser;

    public Room(ArrayList<Song> listSong, ArrayList<User> listUser) {
        this.listSong = listSong;
        this.listUser = listUser;
    }

    public ArrayList<Song> getListSong() {
        return listSong;
    }

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public void setListSong(ArrayList<Song> listSong) {
        this.listSong = listSong;
    }

    public void setListUser(ArrayList<User> listUser) {
        this.listUser = listUser;
    }
    
    
    
}
