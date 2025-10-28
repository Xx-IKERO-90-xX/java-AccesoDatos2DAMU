package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.example.connection.Database;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String url = "jdbc:postgresql://localhost:5432/VTInstitute";
        String user = "ikero";
        String passwd = "ikero9090";

        Database db = new Database();

        if (db.testConnection(url, user, passwd)) {
            System.out.println("Connection Successfull!!");
        } else {
            System.err.println("Connection Failure!!");
        }


    }
}