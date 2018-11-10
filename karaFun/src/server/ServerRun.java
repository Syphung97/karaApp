/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Sy Phung
 */
public class ServerRun {
    public static void main(String[] args) throws AlreadyBoundException, RemoteException {
        ServerControl control = new ServerControl();
    }
}
