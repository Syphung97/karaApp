/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import model.User;

/**
 *
 * @author Sy Phung
 */
public class ServerControl {

    public static int serverPortRegister = 9999;
    public static int serverPortInvitation = 9998;
    public static int serverPortLogin = 10000;
    public static ArrayList<Pair<User,ArrayList<Socket>>> list = new ArrayList<>();
    public static ArrayList<handleLogin> listWorker = new ArrayList<>();
    
    public static void remove(handleLogin w){
            listWorker.remove(w);
        }
    class ForLogin implements Runnable{
        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(serverPortLogin);
                while(true){
                    Socket s = serverSocket.accept();
                    handleLogin w = new handleLogin(s);
                    listWorker.add(w);
                    w.start();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    public ServerControl() {
        createServer();
    }

    void createServer() { 
        new Thread(new ForLogin()).start();
    }

    public static void main(String[] args) {
        new ServerControl();
    }
}
