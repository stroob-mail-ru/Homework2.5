package pro.sky.Homework25;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Homework25.exceptions.EmployeeAlreadyAddedException;
import pro.sky.Homework25.exceptions.EmployeeNotFoundException;
import pro.sky.Homework25.exceptions.EmployeeStorageIsFullException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String add(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            return e.getMessage();
        }
    }
    @GetMapping("/remove")
    public String remove(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.removeEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }
    @GetMapping("/find")
    public String find(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }
    @GetMapping("/findAll")
    public String findAll() {
        return employeeService.printAll();
    }
}
