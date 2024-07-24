package com.felececrud.felececrudapp.service.employeeService;

import com.felececrud.felececrudapp.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeById(Long id);
    void deleteEmployee(Long id);

    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updatedEmployeeDTO);

    EmployeeDTO assignManagerToEmployee(Long employeeId, Long managerId);


}
