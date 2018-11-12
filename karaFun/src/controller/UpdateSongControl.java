/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Song;
import view.AdminHomeFrm;
import view.UpdateSongFrm;

/**
 *
 * @author Hau Nguyen
 */
public class UpdateSongControl {

    UpdateSongFrm view;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    static final String host = "localhost";
    static final int port = 9997;

    public UpdateSongControl(UpdateSongFrm view) {
        this.view = view;
        view.setVisible(true);
        view.addCancelBtnListener(new Cancel());
        view.addUpdateBtnListener(new Update());
    }

    class Cancel implements ActionListener {

        public Cancel() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
        }
    }

    class Update implements ActionListener {

        public Update() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket client = new Socket(host, port);
                oos = new ObjectOutputStream(client.getOutputStream());
                ois = new ObjectInputStream(client.getInputStream());

                oos.writeObject(new Integer(3));

                Song s = view.getSong();
                if (s == null) {
                    view.showMessage("Fill all the infomations !!!");
                } else {
                    oos.writeObject(s);
                    Boolean noti = (Boolean) ois.readObject();
                    if (noti) {
                        view.showMessage("Update Successfully !!!");
                        view.dispose();
                        AdminHomeFrm view = new AdminHomeFrm();
                        AdminControl ac = new AdminControl(view);
                        view.setVisible(true);
                    } else {
                        view.showMessage("Update Failed !!!");
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(UpdateSongControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UpdateSongControl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
