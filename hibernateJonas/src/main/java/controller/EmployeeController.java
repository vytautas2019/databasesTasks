package controller;

import controller.service.EmployeeService;
import controller.service.PersonService;
import model.Employee;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class EmployeeController {
    List<Employee> people = new ArrayList<>();

    public List<Employee> listEmployees() {
        EmployeeService employeeService = new EmployeeService();
        people = employeeService.listEmployees();
        return people;
    }

    public void employeesWithTopPayoutCurrency(){
        EmployeeService employeeService = new EmployeeService();
        System.out.println("Max uždirbančio žmogaus valiuta: " + employeeService.printEmpWithMaxPayCurrency(people).getPaymentCurrency());
    }

    public void getMaxPaidPossitions() {
        EmployeeService employeeService = new EmployeeService();
        employeeService.getMaxPaidPossitions(people);
    }
}
