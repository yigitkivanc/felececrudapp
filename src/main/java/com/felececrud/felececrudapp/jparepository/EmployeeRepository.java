package com.felececrud.felececrudapp.jparepository;

import com.felececrud.felececrudapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);


}
