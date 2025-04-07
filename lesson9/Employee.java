package lesson9;

import java.io.PrintStream;

public class Employee {
    private String fio;
    private String jobTitle;
    private String mail;
    private String phone;
    private int salary;
    private int age;

    public Employee(String fio, String jobTitle, String mail, String phone, int salary, int age) {
        this.fio = fio;
        this.jobTitle = jobTitle;
        this.mail = mail;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void printEmployeeInformation(PrintStream printStream, Employee employee) {
        if (employee.getAge() > 40) {
            printStream.println(employee.toString());
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fio='" + fio + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}