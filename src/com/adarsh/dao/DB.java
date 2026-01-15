package com.adarsh.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    
    // Private constructor prevents outside classes from doing 'new DB()'
    private DB(){} 

    public static Connection createConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/bookingtb";
        String user = "root";
        String pass = "adarsh0103";
        
        Connection connection = DriverManager.getConnection(url, user, pass);
        
        if(connection != null){
            System.out.println("Connection Created");
        }
        return connection;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // CORRECTED: Call the static method directly. 
        // No need for 'DB db = new DB();'
        DB.createConnection();
    }
}