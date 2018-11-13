/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;
import model.Account;

/**
 *
 * @author Sy Phung
 */
public class User extends Person implements Serializable{

    private boolean isVip;

    public User(boolean isVip, String name, String add, Date dob, Account acc) {
        super(name, add, dob, acc);
        this.isVip = isVip;
    }

    

    public User(String name) {
        super(name);
    }
    
    public User() {
    }
    

    public boolean isIsVip() {
        return isVip;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

}
