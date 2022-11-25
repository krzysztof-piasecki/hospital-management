package pl.put.poznan.hospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.poznan.hospitalmanagement.model.Position;
import pl.put.poznan.hospitalmanagement.repository.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public void savePosition(Position position) {
        positionRepository.save(position);
    }

    public void deletePositionById(long id) {
        positionRepository.deleteById(id);
    }

    public Iterable<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Optional<Position> getPositionById(long id) {
        return positionRepository.findById(id);
    }

}
