package org.panagaming.handler;

import org.panagaming.models.Employee;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SAXHandler extends DefaultHandler {

    private List<Employee> employees = new ArrayList<>();
    private Employee employee;

    private boolean bName = false;
    private boolean bSurname = false;
    private boolean bAge = false;
    private boolean bSalary = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts)
            throws SAXException {
        // Evento al abrir una etiqueta <...>

        if ("employee".equals(qName)) {
            employee = new Employee();

            String code = atts.getValue("employee_code");
            employee.setEmployeeCode(code);
        }
        switch(qName) {
            case "name" -> bName = true;
            case "surname" -> bSurname = true;
            case "age" -> bAge = true;
            case "salary" -> bSalary = true;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String content = new String(ch, start, length).trim();

        if (bName) {
            employee.setName(content);
            bName = false;
        }

        if (bSurname) {
            employee.setSurname(content);
            bSurname = false;
        }

        if (bAge) {
            employee.setAge(Integer.parseInt(content));
            bAge = false;
        }

        if (bSalary) {
            employee.setSalary(Double.parseDouble(content));
            bSalary = false;
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        // Evento al cerrar una etiqueta </...>
        if ("employee".equals(qName)) {
            employees.add(employee);
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
