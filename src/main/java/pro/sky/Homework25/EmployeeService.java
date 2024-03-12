package pro.sky.Homework25;

public interface EmployeeService {
    String addEmployee(String firstName, String lastName);

    String removeEmployee(String firstName, String lastName);

    String findEmployee(String firstName, String lastName);

    String printAll();
}
