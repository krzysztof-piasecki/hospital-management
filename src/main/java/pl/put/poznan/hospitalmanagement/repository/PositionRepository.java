package pl.put.poznan.hospitalmanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.put.poznan.hospitalmanagement.model.Position;

import java.util.List;

public interface PositionRepository extends CrudRepository<Position, Long> {
}
