package com.felececrud.felececrudapp.jparepository;

import com.felececrud.felececrudapp.entity.Employee;
import com.felececrud.felececrudapp.projections.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    List<Employee> findByManager(Employee manager);

    @Query("SELECT e.id as id, CONCAT(e.firstName, ' ', e.lastName) as fullName, e.otherInformation as otherInformation, e.personalInformation as personalInformation FROM Employee e")
    List<EmployeeProjection> findAllEmployeeProjections();

    @Query("SELECT e.id as id, CONCAT(e.firstName, ' ', e.lastName) as fullName, e.otherInformation as otherInformation, e.personalInformation as personalInformation FROM Employee e WHERE e.id = :id")
    EmployeeProjection findEmployeeProjectionById(Long id);
}
