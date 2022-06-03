package vn.techmaster.demotest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.demotest.model.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,String>,CustomRepo<Employee> {
    List<Employee> findEmployerByEmailAddressAndLastName();
    List<Employee> findEmployerByFirstNameOrLastName();
    List<Employee> findByLastNameAndOrderByFirstNameEmployees();
    List<Employee> findByFirstNameIgnoreCase();
}
