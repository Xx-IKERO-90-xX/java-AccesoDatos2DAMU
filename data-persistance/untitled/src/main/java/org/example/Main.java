package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.example.connection.Database;
import org.example.controller.SubjectController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static SubjectController subjects = new SubjectController();

    // Funcion that tests addSubject function.
    public static void testAddSubject() throws SQLException {
        String name = "MARKUP LANGUAJES";
        int year = 1;

        subjects.addSubject(name, year);
    }
    public static void main(String[] args) throws SQLException {
        Database db = new Database();

        if (db.testConnection()) {
            System.out.println("Connection Successfull!!");
            testAddSubject();
        } else {
            System.err.println("Connection Failure!!");
        }
    }
}