/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Hau Nguyen
 */
public class Admin extends Person {
    private String position;

    public Admin(String position, String name, String add, Date dob, Account acc) {
        super(name, add, dob, acc);
        this.position = position;
    }
    

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    
    
}
