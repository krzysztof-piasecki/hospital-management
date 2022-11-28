package pl.put.poznan.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.poznan.hospitalmanagement.model.Shift;
import pl.put.poznan.hospitalmanagement.repository.ShiftRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public void saveShift(Shift shift) {
        shiftRepository.save(shift);
    }

    public void saveShiftList(List<Shift> shift) {
        shiftRepository.saveAll(shift);
    }

    public void deleteShiftById(long id) {
        shiftRepository.deleteById(id);
    }

    public Iterable<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Optional<Shift> getShiftById(long id) {
        return shiftRepository.findById(id);
    }

}
