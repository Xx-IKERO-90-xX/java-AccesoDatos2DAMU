package org.panagaming.models;

public class Employee {
    private String employeeCode;
    private String category;
    private String name;
    private String surname;
    private int age;
    private double salary;

    // Constructor vacío
    public Employee() {}

    // Constructor con todos los atributos
    public Employee(String employeeCode, String category, String name, String surname, int age, double salary) {
        this.employeeCode = employeeCode;
        this.category = category;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.salary = salary;
    }

    // Getters y Setters
    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Método toString para mostrar información fácilmente
    @Override
    public String toString() {
        return "Employee{" +
                "employeeCode='" + employeeCode + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
