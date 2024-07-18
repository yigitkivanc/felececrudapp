package com.felececrud.felececrudapp.dao;

import com.felececrud.felececrudapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
