/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import model.Account;

/**
 *
 * @author Sy Phung
 */
public class User extends Person {
    private boolean isVip;

    public User(boolean isVip, int ID, String name, String add, Date dob, Account acc) {
        super(ID, name, add, dob, acc);
        this.isVip = isVip;
    }

   
    public boolean isIsVip() {
        return isVip;
    }

    public void setIsVip(boolean isVip) {
        this.isVip = isVip;
    }

}
