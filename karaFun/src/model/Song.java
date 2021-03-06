/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Hau Nguyen
 */
public class Song implements Serializable {
    static final long serialVersionUID = 2L;
    private int ID;
    private String name, author, singer;

    public Song(String name, String author, String singer) {
        this.ID = 0;
        this.name = name;
        this.author = author;
        this.singer = singer;
    }

    public Song(int ID, String name, String author, String singer) {
        this.ID = ID;
        this.name = name;
        this.author = author;
        this.singer = singer;
    }

    public int getID() {
        return ID;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
    
    
}
