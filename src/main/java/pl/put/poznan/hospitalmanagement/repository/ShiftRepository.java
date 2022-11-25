package pl.put.poznan.hospitalmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.poznan.hospitalmanagement.model.Shift;

public interface ShiftRepository extends CrudRepository<Shift, Long> {
}
