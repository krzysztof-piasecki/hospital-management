package pl.put.poznan.hospitalmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.put.poznan.hospitalmanagement.model.Employee;
public interface EmployeeRepository extends CrudRepository <Employee, Long> {
}
