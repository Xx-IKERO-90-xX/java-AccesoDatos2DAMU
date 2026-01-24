package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    // We create PosgreSQL connection.
    public Connection openConnection() {
        Connection conn = null;

        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "ikero";
        String passwd = "ikero9090";

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
    public boolean testConnection() {
        Connection conn = openConnection();
        return conn != null;
    }
}
