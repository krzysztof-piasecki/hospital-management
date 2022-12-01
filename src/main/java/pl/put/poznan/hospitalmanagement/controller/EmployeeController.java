package pl.put.poznan.hospitalmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.hospitalmanagement.model.Employee;
import pl.put.poznan.hospitalmanagement.service.EmployeeService;

@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        log.info("Retrieve employee by id:" + id);
        return employeeService.getEmployeeById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
    @GetMapping(path = "all")
    public Iterable<Employee> getAllEmployees() {
        log.info("Getting all employees");
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public void saveEmployee(@RequestBody Employee employee) {
        try {
            log.info("Added emplyee: " + employee.toString());
            employeeService.saveEmployee(employee, passwordEncoder);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getStackTrace().toString()
            );
        }
    }
}
