package pl.put.poznan.hospitalmanagement.controller;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.hospitalmanagement.controller.deserializer.CustomShiftDeserializer;
import pl.put.poznan.hospitalmanagement.model.Shift;
import pl.put.poznan.hospitalmanagement.service.EmployeeService;
import pl.put.poznan.hospitalmanagement.service.ShiftService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("shift")
@Slf4j
public class ShiftController {

    @Autowired
    private ShiftService shiftService;
    @Autowired
    private CustomShiftDeserializer customShiftDeserializer;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
    public Iterable<Shift> getAllShifts() {
        log.info("Get all the shifts");
        return shiftService.getAllShifts();
    }

    @PostMapping
    public void saveShift(@RequestBody String json, HttpServletResponse response) throws JsonProcessingException {
        try {
            SimpleModule module =
                    new SimpleModule("CustomShiftDeserializer", new Version(1, 0, 0, null, null, null));
            module.addDeserializer(Shift.class, customShiftDeserializer);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module);
            Shift shift = mapper.readValue(json, Shift.class);
            shiftService.saveShift(shift);
        } catch (NullPointerException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @PostMapping("list")
    public void saveListShift(@RequestBody String string, HttpServletResponse response) throws JsonProcessingException, JSONException {
        JSONArray jsonArray = new JSONArray(string);
        List<Shift> shiftList = new ArrayList<>();
        for (Object object : jsonArray) {
            SimpleModule module =
                    new SimpleModule("CustomShiftDeserializer", new Version(1, 0, 0, null, null, null));
            module.addDeserializer(Shift.class, customShiftDeserializer);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(module);
            Shift shift = mapper.readValue(object.toString(), Shift.class);
            shiftList.add(shift);
        }
        shiftService.saveShiftList(shiftList);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
