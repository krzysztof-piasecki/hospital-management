package pl.put.poznan.hospitalmanagement.controller.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.hospitalmanagement.model.Employee;
import pl.put.poznan.hospitalmanagement.model.Shift;
import pl.put.poznan.hospitalmanagement.service.EmployeeService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@Component
public class CustomShiftDeserializer extends StdDeserializer<Shift> {

    @Autowired
    EmployeeService employeeService;

    public CustomShiftDeserializer(Class<?> vc) {
        super(vc);

    }

    public CustomShiftDeserializer(){
        this(null);
    }

    @Override
    public Shift deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node  = codec.readTree(parser);

        JsonNode employeeNode = node.get("employee");
        Long employeeId = employeeNode.asLong();
        log.info("Retrieve employee with id: " + employeeId);

        Employee employee = employeeService.getEmployeeById(employeeId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no such employee with given id"));

        JsonNode startDateNode = node.get("startDateShift");
        LocalDate startLocalDate = LocalDate.parse(startDateNode.asText());

        JsonNode endDateNode = node.get("endDateShift");
        LocalDate endLocalDate = LocalDate.parse(endDateNode.asText());

        JsonNode startTimeNode = node.get("startTimeShift");
        LocalTime startTime = LocalTime.parse(startTimeNode.asText());

        JsonNode endTimeNode = node.get("endTimeShift");
        LocalTime endTime = LocalTime.parse(endTimeNode.asText());

        Shift shift = new Shift();
        shift.setEndDateShift(endLocalDate);
        shift.setStartDateShift(startLocalDate);
        shift.setEmployee(employee);
        shift.setEndTimeShift(endTime);
        shift.setStartTimeShift(startTime);
        return shift;
    }
}
