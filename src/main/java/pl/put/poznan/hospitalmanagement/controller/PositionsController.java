package pl.put.poznan.hospitalmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.put.poznan.hospitalmanagement.model.Position;
import pl.put.poznan.hospitalmanagement.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("position")
@Slf4j
public class PositionsController {

    @Autowired
    private PositionService positionService;

    @GetMapping(path = "all")
    public Iterable<Position> getAllPositions() {
        log.info("Getting all positions");
        return positionService.getAllPositions();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.OK, reason = "OK")
    public void savePosition(@RequestBody Position position) {
        try {
            log.info("Saving the position");
            positionService.savePosition(position);

        } catch (Exception e) {
            log.error("Couldn't save the position");
            log.error(e.getStackTrace().toString());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getStackTrace().toString()
            );
        }
    }
}
