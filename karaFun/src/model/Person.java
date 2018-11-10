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
public class Person {
    protected final int ID;
    protected String name, add;
    protected Date dob;
    protected Account acc;

    public Person(int ID, String name, String add, Date dob, Account acc) {
        this.ID = ID;
        this.name = name;
        this.add = add;
        this.dob = dob;
        this.acc = acc;
    }

    public int getID() {
        return ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    
    
    
}
