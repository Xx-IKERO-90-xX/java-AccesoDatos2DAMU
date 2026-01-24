package org.panagaming;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.panagaming.runner.EmployeeRunner;
import org.panagaming.models.Employee;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String XMLPath = "Employees.xml";

    public static void main(String[] args) {
        var xsdFile = new File("Employees.xsd");
        EmployeeRunner employeeRunner = new EmployeeRunner();

        List<Employee> employees = new ArrayList<>();

        try {
            Path xmlPath = Paths.get(XMLPath);
            Reader reader = Files.newBufferedReader(xmlPath);

            String schemaLang = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            Schema schema = factory.newSchema(xsdFile);

            Validator validator = schema.newValidator();

            var source = new SAXSource(new InputSource(reader));
            validator.validate(source);

            System.out.println("The document was validated OK");

            employees = employeeRunner.parseEmployee();
            for (Employee e : employees) {
                System.out.println(e);
            }

        } catch (Exception ex) {
            System.err.println("ERROR: XML NOT VALID.");
            ex.printStackTrace();
            employees = new ArrayList<>();
        }

        System.out.println("\nEmployees list size: " + employees.size());
    }
}