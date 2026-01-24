package org.example.controller;

import org.example.connection.Database;
import org.example.models.Course;

import java.sql.*;

public class CourseController {
    public static Course getCourseByName(String name) {
        Database db = new Database();
        String sql = "SELECT code, name FROM courses WHERE name = ?";

        try (Connection conn = db.openConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Course(rs.getInt("code"), rs.getString("name"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
