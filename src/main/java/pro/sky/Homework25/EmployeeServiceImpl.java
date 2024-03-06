package pro.sky.Homework25;

import org.springframework.stereotype.Service;
import pro.sky.Homework25.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Homework25.exceptions.EmployeeNotFoundException;
import pro.sky.Homework25.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employees;
    final int SIZE = 10;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public String addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        if (employees.size() >= SIZE) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }
        for (Employee currentEmployee : employees) {
            if (currentEmployee.equals(employee)) {
                throw new EmployeeAlreadyAddedException("Уже есть такой сотрудник");
            }
        }
        employees.add(employee);
        return employee.toString();
    }
    @Override
    public String removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()) {
            Employee nextEmployee = employeeIterator.next();
            if (nextEmployee.equals(employee)) {
                employeeIterator.remove();
                return employee.toString();
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }
    @Override
    public String findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);

        for (Employee currentEmployee : employees) {
            if (currentEmployee.equals(employee)) {
                return currentEmployee.toString();
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }
    @Override
    public String printAll() {
        String printoutAllEmployees = "";
        for (Employee employee : employees) {
            printoutAllEmployees = printoutAllEmployees + employee.toString() + "<br>";
        }
        return printoutAllEmployees;
    }
}
