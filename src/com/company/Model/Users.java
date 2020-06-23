package com.company.Model;

import com.company.Controler.DataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class Users {

    private String name,lastname,username;
    private int id = -1;

    public Users(int id,String username,String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.id = id;
    }

    public Users(String name, String lastname, String username) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
    }

    public static ArrayList<Users> getAllusers(){
        try {
            return DataBase.getAllusers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(){
        if (this.id == -1 ){
            try {
              id =  DataBase.creatuser(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                DataBase.updateuser(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
