package com.felececrud.felececrudapp.controller;

import com.felececrud.felececrudapp.dto.EmployeeDTO;
import com.felececrud.felececrudapp.entity.Employee;
import com.felececrud.felececrudapp.entity.PersonalInformation;
import com.felececrud.felececrudapp.filterRequest.EmployeeFilterRequest;
import com.felececrud.felececrudapp.jparepository.EmployeeRepository;
import com.felececrud.felececrudapp.jparepository.OtherInformationRepository;
import com.felececrud.felececrudapp.jparepository.PersonalInformationRepository;
import com.felececrud.felececrudapp.mapper.EntityMapper;
import com.felececrud.felececrudapp.service.employeeService.EmployeeService;
import com.felececrud.felececrudapp.validation.DuplicateFieldException;
import com.felececrud.felececrudapp.validation.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonalInformationRepository personalInformationRepository;

    @Autowired
    OtherInformationRepository otherInformationRepository;
    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/createEmployee")
    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        validateUniqueFields(employeeDTO);
        Employee employee = entityMapper.toEmployee(employeeDTO);
        employee = employeeRepository.save(employee);
        return entityMapper.toEmployeeDTO(employee);
    }

    @GetMapping("/listEmployees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/updateEmployee/{id}")
    public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @GetMapping("/listEmployees/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @PostMapping("/{employeeId}/assignManager/{managerId}")
    public EmployeeDTO assignManagerToEmployee(@PathVariable Long employeeId, @PathVariable Long managerId) {
        return employeeService.assignManagerToEmployee(employeeId, managerId);
    }

    @PostMapping("/filter")
    public List<EmployeeDTO> filterEmployees(@Valid @RequestBody EmployeeFilterRequest filterRequest) {
        return employeeService.filterEmployees(filterRequest);
    }
    private void validateUniqueFields(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsByPhoneNumber(employeeDTO.getPhoneNumber())) {
            throw new DuplicateFieldException("Phone number already exists");
        }
        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new DuplicateFieldException("Email address already exists");
        }
        if (personalInformationRepository.existsByNationalId(employeeDTO.getPersonalInformation().getNationalId())){
            throw new DuplicateFieldException("National ID already exists");
        }
        if (otherInformationRepository.existsByIban(employeeDTO.getOtherInformation().getIban())){
            throw new DuplicateFieldException("IBAN already exist");
        }
    }



}
