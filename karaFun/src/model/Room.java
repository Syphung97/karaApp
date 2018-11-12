/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Hau Nguyen
 */
public class Room implements Serializable{
    private final int ID;
    private ArrayList<Song> listSong;
    private ArrayList<User> listUser;
    private int capacity;

    public Room(int ID, ArrayList<Song> listSong, ArrayList<User> listUser) {
        this.ID = ID;
        this.listSong = listSong;
        this.listUser = listUser;
    }

    public Room(int ID, int capacity) {
        this.ID = ID;
        this.capacity = capacity;
    }
    

    public int getID() {
        return ID;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
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
