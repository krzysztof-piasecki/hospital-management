package pl.put.poznan.hospitalmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.hospitalmanagement.model.Shift;
import pl.put.poznan.hospitalmanagement.service.EmployeeService;
import pl.put.poznan.hospitalmanagement.service.ShiftService;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("shift")
@Slf4j
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @GetMapping("/all")
    public Iterable<Shift> getAllShifts(){
        log.info("Get all the shifts");
        return shiftService.getAllShifts();
    }
}
