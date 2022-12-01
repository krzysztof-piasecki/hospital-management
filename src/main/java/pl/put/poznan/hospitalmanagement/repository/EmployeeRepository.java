package pl.put.poznan.hospitalmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import pl.put.poznan.hospitalmanagement.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
}
