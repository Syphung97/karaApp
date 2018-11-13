/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;
import model.Account;
import model.Invitation;
import model.User;

/**
 *
 * @author Sy Phung
 */
public class ServerControl {

    public static int serverPortRegister = 9999;
    public static int serverPortInvitation = 9998;
    public static int serverPortSong = 9997;
    public static int serverPortLogin = 10000;
    public static int serverPortSignUp = 11111;
    public static int serverPortRoom = 11112;
    public static ArrayList<Pair<Account, ArrayList<Socket>>> list = new ArrayList<>();
    public static ArrayList<handleLogin> listWorker = new ArrayList<>();
    public static ArrayList<handleSignUp> listSignUp = new ArrayList<>();
    public static ArrayList<HandleSong> listHanSong = new ArrayList<>();
    public static ArrayList<HandleRoom> listHanRoom = new ArrayList<>();

    public static void remove(handleLogin w) {
        listWorker.remove(w);
    }

    public static void removeSU(handleSignUp h) {
        listSignUp.remove(h);
    }

    public static class AddCustomer implements Runnable {

        @Override
        public void run() {
            try {
                ServerSocket serverRegister = new ServerSocket(serverPortRegister);
                ServerSocket svRec = new ServerSocket(9900);
                while (true) {
                    try {
                        Socket clientRegister = serverRegister.accept();
                        Socket re = svRec.accept();
                        ObjectInputStream in = new ObjectInputStream(clientRegister.getInputStream());
                        Account customer = (Account) in.readObject();                       
                        ArrayList<Socket> listSockets = new ArrayList<>();
                        listSockets.add(clientRegister);
                        
                        listSockets.add(re);

                        list.add(new Pair(customer, listSockets));
                        System.out.println(list.size());
                        
                       

                    } catch (IOException | ClassNotFoundException e) {
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    public class CheckInvitation extends Thread{

        @Override
        public void run() {
            try {
                ServerSocket ss = new ServerSocket(9998);
                
                while(true){
                    Socket s = ss.accept();
                    // Đọc lời mời
                    ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                    Invitation i = (Invitation) ois.readObject();
                    
                    for (Pair<Account, ArrayList<Socket>> pair : list) {
                        if(i.getReceive().equals(pair.getKey().getUsername())){
                            Socket client = (Socket)((ArrayList)pair.getValue()).get(1);
                            // Gửi lời mời thông qua socket của thằng nhận
                            DataOutputStream dos = new DataOutputStream(client.getOutputStream());
                            dos.writeUTF("Invite from "+i.getSend().getUsername());
                        }
                    }
//                    DataInputStream dis = new DataInputStream(s.getInputStream());
//                    System.out.println(dis.readInt());
                    System.out.println(i.getReceive());
                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    dos.writeUTF(i.getSend().getUsername());
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public static class CheckConnection implements Runnable {

        @Override
        public void run() {

            while (true) {
                int index = -1;
                try {
                    for (int i = 0; i < list.size(); i++) {
                        index = i;
                        //check connection
                        DataOutputStream out = new DataOutputStream(list.get(i).getValue().get(0).getOutputStream());
                        out.writeBoolean(true);
//                        System.out.println("checking connection");

                    }

                } catch (IOException e) {
                    list.remove(index);
                    System.out.println("REMOVED CLIENT FROM LIST");
                }

                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    class ForLogin implements Runnable {

        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(serverPortLogin);
                while (true) {
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

    class ForSignUp implements Runnable {

        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(serverPortSignUp);
                while (true) {
                    Socket s = serverSocket.accept();
                    handleSignUp signUp = new handleSignUp(s);
                    listSignUp.add(signUp);
                    signUp.start();

                }
            } catch (IOException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    class ForSong implements Runnable {

        @Override
        public void run() {

            try {
                ServerSocket serverSocket = new ServerSocket(serverPortSong);
                while (true) {
                    Socket s = serverSocket.accept();
                    HandleSong hs = new HandleSong(s);
                    listHanSong.add(hs);
                    hs.start();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    class ForRoom implements Runnable{

        @Override
        public void run() {
            try {
                ServerSocket server = new ServerSocket(serverPortRoom);
                while(true){
                    try {
                        Socket s = server.accept();
                        HandleRoom hr = new HandleRoom(s);
                        listHanRoom.add(hr);
                        hr.start();
                    } catch (Exception e) {
                    }
            }
            } catch (Exception e) {
            }
        }
        
    }

    public ServerControl() {
        createServer();
    }

    void createServer() {
        new Thread(new ForLogin()).start();
        new Thread(new ForSignUp()).start();
        new Thread(new ForSong()).start();
        new Thread(new ForRoom()).start();
        new CheckInvitation().start();
    }

    public static void main(String[] args) throws InterruptedException {
        new ServerControl();
        Thread addCustomer = new Thread(new AddCustomer());
        addCustomer.start();
        Thread.sleep(100);
        Thread checkConnection = new Thread(new CheckConnection());
        checkConnection.start();
        
        
        
    }
}
