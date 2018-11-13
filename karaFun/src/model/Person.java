/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Hau Nguyen
 */
public class Person implements Serializable{
    protected String name;
;
    protected Account acc;

    public Person(String name, Account acc) {
        this.name = name;
        this.acc = acc;
    }

 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    
    
    
}
