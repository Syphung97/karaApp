/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import model.Account;
import model.Song;
import share.ConnectDB;

/**
 *
 * @author Hau Nguyen
 */
public class SongDAO extends ConnectDB {

    public SongDAO() {
        super();
    }

    public ArrayList<Song> searchSong(String name) {
        ArrayList<Song> listSong = new ArrayList<>();

        try {

            String sql = "SELECT * FROM song WHERE Name LIKE '%"+name+"%'";
            PreparedStatement ps = conn.prepareStatement(sql);
            
//            ps.setString(1, "%" + name + "%");
            
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                Song s = new Song(rs.getInt("ID"), rs.getString("Name"), rs.getString("Author"), rs.getString("Singer"));
                listSong.add(s);
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSong;
    }

    public boolean addSong(Song s) {

        boolean result = false;
        try {
            String sql = "INSERT INTO song(Name, Author, Singer) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, s.getName());
            ps.setString(2, s.getAuthor());
            ps.setString(3, s.getSinger());

            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

    public boolean updateSong(Song s) {
        boolean result = false;
        try {
            String sql = "UPDATE song SET Name = ?, Author = ?, Singer = ? WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, s.getName());
            ps.setString(2, s.getAuthor());
            ps.setString(3, s.getSinger());
            ps.setInt(4, s.getID());

            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public boolean deleteSong(int i) {
        boolean result = false;
        try {
            String sql = "DELETE FROM song WHERE ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, i);

            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

    public ArrayList<Song> getListSong() {
        ArrayList<Song> listSong = new ArrayList<>();

        try {

            String sql = "SELECT * FROM song";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                Song song = new Song(rs.getInt(1), rs.getString("Name"), rs.getString("Author"), rs.getString("Singer"));
                listSong.add(song);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SongDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listSong;
    }

//    public static void main(String[] args) {
//        SongDAO sd = new SongDAO();
//        if(sd.deleteSong(7)) {
//            System.out.println("Ok");
//        }
//
//    }
}
