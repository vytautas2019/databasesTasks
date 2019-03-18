package controller.service;

import lombok.NonNull;
import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService {

    Session session;


    private void openTrans() {
        Configuration cfg = new Configuration()
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(TimeSheet.class)
                .addAnnotatedClass(Currency.class);
        SessionFactory sf = cfg.buildSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
    }

    public List<Employee> listEmployees() {
        openTrans();
        List<Employee> people = new ArrayList<>();
        people = session.createQuery("from employee").list();//session.createCriteria(Person.class).list();
        printToConsole(people);
        session.disconnect();
        return people;

    }

    private void printToConsole(List<Employee> people) {
        people.forEach(person -> {
            System.out.println(person.getName() + "uždirba : "
                    + getSalary(person));
        });
    }

    private double getSalary(Employee employee) {
        return Math.round(employee.getTimeSheetList().getMoneyPerHour() * employee.getPaymentCurrency().getRate() * employee.getMoneyPerHour());
    }


    public Employee printEmpWithMaxPayCurrency(List<Employee> people) {
        Employee employee = null;
        try {
            employee = people.stream()
                    .max((o1, o2) -> new Double(getSalary(o1)).intValue() - new Double(getSalary(o2)).intValue())
                    .orElseThrow(() -> new Exception(""));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void getMaxPaidPossitions(List<Employee> people) {
        Map<String, Double> collect = people.stream()
                .filter(employee -> !employee.isOnHolidays())
                .collect(Collectors.groupingBy(
                        employee -> employee.getPosition(),
                        Collectors.summingDouble(value -> getSalary(value))
                ));
        collect.forEach((s, aDouble) -> {
            System.out.println(s + ": " + aDouble);
        });
    }
}
