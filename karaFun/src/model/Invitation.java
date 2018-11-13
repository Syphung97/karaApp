/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Sy Phung
 */
public class Invitation implements Serializable{
    Account send;
    String receive;

    public Invitation(Account send, String receive) {
        this.send = send;
        this.receive = receive;
    }
   
    public Account getSend() {
        return send;
    }

    public String getReceive() {
        return receive;
    }

    
    
}
