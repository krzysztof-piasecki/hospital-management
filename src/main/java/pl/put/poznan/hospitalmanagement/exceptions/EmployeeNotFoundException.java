package pl.put.poznan.hospitalmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static pl.put.poznan.hospitalmanagement.controller.utils.ControllerUtils.EMPLOYEE_NOT_FOUND;



public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException() {
        super(EMPLOYEE_NOT_FOUND);
    }
}

