package pl.put.poznan.hospitalmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.hospitalmanagement.exceptions.EmployeeNotFoundException;
import pl.put.poznan.hospitalmanagement.model.Employee;
import pl.put.poznan.hospitalmanagement.service.EmployeeService;

@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = ":{id}")
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "user not found")
    public Employee getEmployeeById(@PathVariable Long id) {
        log.info("Getting all employees");
        return employeeService.getEmployeeById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }


    /**
     * To create employee entity the path is "/employee" with POST method
     *
     * @RequestBody :
     * {
     * "firstName": "",
     * "lastName": "",
     * "pesel": "",
     * "phoneNr": "",
     * "position": ""
     * }
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public void saveEmployee(@RequestBody Employee employee) {
        try {
            log.info("Added emplyee: " + employee.toString());
            employeeService.saveEmployee(employee);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getStackTrace().toString()
            );
        }
    }
}
