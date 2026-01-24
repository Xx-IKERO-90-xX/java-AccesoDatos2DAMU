package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.example.connection.Database;
import org.example.controller.CourseController;
import org.example.controller.SubjectController;
import java.util.Scanner;
import org.example.models.Course;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
<<<<<<< HEAD
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String url = "jdbc:postgresql://100.116.29.49:5432/vtinstitute";
        String user = "ikero";
        String passwd = "ikero9090";
=======
    static SubjectController subjects = new SubjectController();
>>>>>>> c2db7b43f645d60700cd4000e8ab341c3cc86a51

    // Funcion that allows users to add Subjects.
    public static void addSubject() throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.print("Subject Name");
        String sbName = sc.nextLine();

        System.out.print("Year");
        int sbYear = sc.nextInt();

        System.out.print("Course");
        String cName = sc.nextLine();

        Course crse = CourseController.getCourseByName(sbName);

        if (crse != null) {
            subjects.addSubject(sbName, sbYear, crse.getCode());
        } else {
            System.out.println("The name of the entered course does not match any stored course.");
        }
    }
    public static void main(String[] args) throws SQLException {
        Database db = new Database();

        if (db.testConnection()) {
            System.out.println("Connection Successfull!!");
            addSubject();
        } else {
            System.err.println("Connection Failure!!");
        }
    }
}