package org.example.controller;
import org.example.connection.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class SubjectController {

    // Function that adds a new subject
    public void addSubject (String name, int year, int cName) throws SQLException {
       Database db = new Database();

        try (Connection conn = db.openConnection(); Statement stmt = conn.createStatement()) {
            String sql = String.format("INSERT INTO subjects (name, year) VALUES ('%s', %s, %s)", name, year);
            int rowAffected = stmt.executeUpdate(sql);
            System.out.println("Inserted Rows: " + rowAffected);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getClass().getName() + ": " + e.getMessage());
        }
    }
}
