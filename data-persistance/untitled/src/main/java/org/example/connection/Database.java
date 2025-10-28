package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    // We create PosgreSQL connection.
    public Connection openConnection(String url, String user, String passwd) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getClass().getName()+": "+e.getMessage());
        }
        return conn;
    }

    // Fuction that tests the connecion.
    public boolean testConnection(String url, String user, String passwd) {
        Connection conn = openConnection(url, user, passwd);
        return conn != null;
    }
}
