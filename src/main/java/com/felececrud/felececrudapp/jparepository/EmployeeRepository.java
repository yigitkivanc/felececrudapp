package com.felececrud.felececrudapp.jparepository;

import com.felececrud.felececrudapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);

    List<Employee> findByManager(Employee manager);


}
