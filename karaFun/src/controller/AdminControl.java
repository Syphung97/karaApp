/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import client.ClientControl;
import client.LoginFrm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Song;
import view.AddSongFrm;
import view.AdminHomeFrm;
import view.UpdateSongFrm;

/**
 *
 * @author Hau Nguyen
 */
public class AdminControl {

    AdminHomeFrm view;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    static final String host = "localhost";
    static final int port = 9997;

    // KEY
    // Show list song: 1
    // Add Song: 2
    // Update Song: 3
    // Delete Song: 4
    // Search Song: 5
    public AdminControl(AdminHomeFrm view) {
        this.view = view;
        for (JButton updateBtn : this.view.listUpdateBtn) {
            updateBtn.addActionListener(new ButtonListener());
        }
        for (JButton deleBtn : this.view.listDeleteBtn) {
            deleBtn.addActionListener(new ButtonListener());
        }
        this.view.addLogoutBtnListener(new Logout());
        this.view.addSearchBtnListener(new Search());
        this.view.addAddSongBtnListener(new Add());
    }

    class Add implements ActionListener {

        public Add() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AddSongFrm addView = new AddSongFrm();
            AddSongControl addCtr = new AddSongControl(addView);

        }

    }

    class Search implements ActionListener {

        public Search() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket client = new Socket(host, port);
                oos = new ObjectOutputStream(client.getOutputStream());
                ois = new ObjectInputStream(client.getInputStream());

                if (view.getSearchName() == null) {
                    view.showMessage("Please, enter keyword !!!");
                    return;
                } else {
                    System.out.println("Search");
                    oos.writeObject(new Integer(5));
                    oos.writeObject(view.getSearchName());

                    Integer loop = (Integer) ois.readObject();

//                    System.out.println(loop);
                    ArrayList<Song> listS = new ArrayList<>();
                    for (int i = 0; i < loop; i++) {
                        Song s = (Song) ois.readObject();
                        listS.add(s);
                    }
                    view.listSong = listS;
                    view.updateTable();

                    oos.close();
                    client.close();

                }

            } catch (IOException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    class Logout implements ActionListener {

        public Logout() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println("log out");
                LoginFrm frm = new LoginFrm();
                ClientControl c = new ClientControl("localhost", 10000, frm);
                frm.setVisible(true);
                view.dispose();
            } catch (IOException ex) {
                Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class ButtonListener implements ActionListener {

        public ButtonListener() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton btnClicked = (JButton) e.getSource();
            for (int i = 0; i < view.listUpdateBtn.size(); i++) {
                if (btnClicked.equals(view.listUpdateBtn.get(i))) {
                    Song s = view.listSong.get(i);

                    UpdateSongFrm updateView = new UpdateSongFrm(s);
                    UpdateSongControl updateCtr = new UpdateSongControl(updateView);

                }
            }

            for (int i = 0; i < view.listDeleteBtn.size(); i++) {
                if (btnClicked.equals(view.listDeleteBtn.get(i))) {
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int del = JOptionPane.showConfirmDialog(view, "Do you want to delete this song??", "Warning", dialogButton);

                    if (del == JOptionPane.YES_OPTION) {
                        int id = view.listSong.get(i).getID();

                        try {
                            Socket client = new Socket(host, port);
                            oos = new ObjectOutputStream(client.getOutputStream());
                            ois = new ObjectInputStream(client.getInputStream());

                            oos.writeObject(new Integer(4));

                            oos.writeObject(new Integer(id));

                            Boolean noti = (Boolean) ois.readObject();
                            if (noti) {
                                view.showMessage("Deleted Successfully !!!");
                            } else {
                                view.showMessage("Delete Failed !!!");
                            }
//                        oos.close();
//                        client.close();

                        } catch (IOException ex) {
                            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(AdminControl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        view.listSong.remove(i);
                        view.updateTable();
                    }

                }
            }
        }
    }

    public static void main(String[] args) {

        AdminHomeFrm view = new AdminHomeFrm();
        AdminControl ac = new AdminControl(view);
        view.setVisible(true);

    }

}
