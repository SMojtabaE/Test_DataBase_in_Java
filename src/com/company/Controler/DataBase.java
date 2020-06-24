package com.company.Controler;

import com.company.Model.Users;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {

    private static Connection connection;
    private static Statement statement;

    private DataBase(){}

    public static void makeconection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test"
                    ,"username_of_database","password_of_database");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeconnection(){
        if (connection != null){
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static int creatuser(Users user) throws SQLException {
        makeconection();
        statement.execute(String.format("insert into users (username,name,lastname)" +
                        "values ( '%s', '%s', '%s')", user.getUsername(),user.getName(),user.getLastname()),
                        Statement.RETURN_GENERATED_KEYS);
        ResultSet re = statement.getGeneratedKeys();
        re.next();
        int id = re.getInt(1);
        closeconnection();
        return id;
    }
    public static void deletuser(Users user) throws SQLException {
        makeconection();
        statement.execute(String.format("delete from users where id = %d",user.getId()));
        closeconnection();
    }
    public static ArrayList<Users> getAllusers() throws SQLException {
        makeconection();
        ResultSet re = statement.executeQuery("select * from users");
        ArrayList<Users> users = new ArrayList<>();
        while (re.next()){
            users.add(new Users(re.getInt(1),re.getString(2),re.getString(3),
                    re.getString(4)));
        }
        closeconnection();
        return users;
    }
    public static void updateuser(Users user) throws SQLException {
        makeconection();
        statement.execute(String.format("update users set username = '%s', name = '%s', lastname = '%s' where id = %d"
                ,user.getUsername(),user.getName(),user.getLastname(),user.getId()));
        closeconnection();
    }
}
