import controller.EmployeeController;
import controller.PersonController;
import model.Employee;
import model.Person;
import model.Salary;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonController personController = new PersonController();
        personController.listPersons();
        personController.updatePersonMaxAge();
        personController.deleteEveryPersonExcept();
        EmployeeController employeeController = new EmployeeController();
        List<Employee> employees = employeeController.listEmployees();
        employeeController.employeesWithTopPayoutCurrency();
        employeeController.getMaxPaidPossitions();
    }
}
