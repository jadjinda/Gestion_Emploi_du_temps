package com.example.interfacegraphique;
import java.sql.*;

public class ConnexionDB {
    static String user = "root";
    static String password = "1234Jojo";
    static String url ="jdbc:mysql://localhost/EmploiDuTemps";

    public static Connection getConnect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return connection;
    }
}
