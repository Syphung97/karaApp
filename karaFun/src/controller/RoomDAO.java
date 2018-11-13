/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Room;
import model.User;
import share.ConnectDB;

/**
 *
 * @author Tran Tuan Anh
 */
public class RoomDAO extends ConnectDB{
     public RoomDAO() {
         super();
     }
     
     public ArrayList<User> listUsersInRoom(int roomID) {
        ArrayList<User> listUsers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user WHERE roomID = "+roomID+"";
            
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, roomID);

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                User user = new User(rs.getString("Name"));
                listUsers.add(user);
            }

            rs.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listUsers;
    }


    public void joinRoom(int idUser, int idRoom) {
        try {
            String sql = "UPDATE user SET RoomID = ? WHERE AccountID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, idRoom);
            ps.setInt(2, idUser);

            ps.executeUpdate();
      
            ps.close();
        } catch (SQLException ex) {
        }
    }

    public ArrayList<Room> getAllRoom() {
        ArrayList<Room> listRoom = new ArrayList<>();
        try {

            String sql = "SELECT * FROM room";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                Room room = new Room(rs.getInt("ID"), rs.getInt("Capacity"));
                listRoom.add(room);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
        }
        System.out.println(listRoom.size());
        return listRoom;
}

    }

    
