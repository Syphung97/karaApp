/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import model.User;
import share.IRMI;

/**
 *
 * @author Sy Phung
 */
public class ServerControl extends UnicastRemoteObject implements IRMI{
    private int port=5555;
    private Registry registry;
    
    public ServerControl() throws RemoteException, AlreadyBoundException {
        createServer();
    }
    void createServer() throws RemoteException, AlreadyBoundException{
        registry=LocateRegistry.createRegistry(port);
        registry.bind("Server", this);
    }    

}
