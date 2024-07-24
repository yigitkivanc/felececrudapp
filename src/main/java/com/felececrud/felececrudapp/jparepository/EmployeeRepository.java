package com.felececrud.felececrudapp.jparepository;

import com.felececrud.felececrudapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
}
