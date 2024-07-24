package com.felececrud.felececrudapp.service.employeeService;

import com.felececrud.felececrudapp.dto.EmployeeDTO;
import com.felececrud.felececrudapp.dto.OtherInformationDTO;
import com.felececrud.felececrudapp.dto.PersonalInformationDTO;
import com.felececrud.felececrudapp.dto.ProjectDTO;
import com.felececrud.felececrudapp.entity.Employee;
import com.felececrud.felececrudapp.entity.OtherInformation;
import com.felececrud.felececrudapp.entity.PersonalInformation;
import com.felececrud.felececrudapp.entity.Project;
import com.felececrud.felececrudapp.enums.*;
import com.felececrud.felececrudapp.jparepository.EmployeeRepository;
import com.felececrud.felececrudapp.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = entityMapper.toEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return entityMapper.toEmployeeDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(entityMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        return employeeRepository.findById(Math.toIntExact(id))
                .map(entityMapper::toEmployeeDTO)
                .orElse(null);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(Math.toIntExact(id));
    }

    @Override
    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO updatedEmployeeDTO) {
        Employee existingEmployee = employeeRepository.findById(Math.toIntExact(employeeId))
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));

        existingEmployee.setFirstName(updatedEmployeeDTO.getFirstName());
        existingEmployee.setLastName(updatedEmployeeDTO.getLastName());

        if (updatedEmployeeDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(Math.toIntExact(updatedEmployeeDTO.getManagerId()))
                    .orElseThrow(() -> new IllegalArgumentException("Manager not found"));
            existingEmployee.setManager(manager);
        } else {
            existingEmployee.setManager(null);
        }

        existingEmployee.setLevel(Level.valueOf(updatedEmployeeDTO.getLevel()));
        existingEmployee.setPhoneNumber(updatedEmployeeDTO.getPhoneNumber());
        existingEmployee.setEmail(updatedEmployeeDTO.getEmail());
        existingEmployee.setBirthDate(updatedEmployeeDTO.getBirthDate());
        existingEmployee.setWorkType(WorkType.valueOf(updatedEmployeeDTO.getWorkType()));
        existingEmployee.setContractType(ContractType.valueOf(updatedEmployeeDTO.getContractType()));
        existingEmployee.setTeam(Team.valueOf(updatedEmployeeDTO.getTeam()));
        existingEmployee.setStartDate(updatedEmployeeDTO.getStartDate());
        existingEmployee.setEndDate(updatedEmployeeDTO.getEndDate());


        if (existingEmployee.getPersonalInformation() == null) {
            existingEmployee.setPersonalInformation(new PersonalInformation());
        }
        PersonalInformationDTO personalInfoDTO = updatedEmployeeDTO.getPersonalInformation();
        existingEmployee.getPersonalInformation().setBirthDate(personalInfoDTO.getBirthDate());
        existingEmployee.getPersonalInformation().setNationalId(personalInfoDTO.getNationalId());
        existingEmployee.getPersonalInformation().setMilitaryStatus(MilitaryStatus.valueOf(personalInfoDTO.getMilitaryStatus()));
        existingEmployee.getPersonalInformation().setGender(Gender.valueOf(personalInfoDTO.getGender()));
        existingEmployee.getPersonalInformation().setMaritalStatus(MaritalStatus.valueOf(personalInfoDTO.getMaritalStatus()));


        if (existingEmployee.getOtherInformation() == null) {
            existingEmployee.setOtherInformation(new OtherInformation());
        }
        OtherInformationDTO otherInfoDTO = updatedEmployeeDTO.getOtherInformation();
        existingEmployee.getOtherInformation().setAddress(otherInfoDTO.getAddress());
        existingEmployee.getOtherInformation().setBankName(otherInfoDTO.getBankName());
        existingEmployee.getOtherInformation().setIban(otherInfoDTO.getIban());
        existingEmployee.getOtherInformation().setEmergencyContactName(otherInfoDTO.getEmergencyContactName());
        existingEmployee.getOtherInformation().setEmergencyContactPhoneNumber(otherInfoDTO.getEmergencyContactPhoneNumber());


        List<ProjectDTO> projects = updatedEmployeeDTO.getProjects();
        if (projects != null) {
            List<Project> updatedProjects = projects.stream()
                    .map(entityMapper::toProject)
                    .collect(Collectors.toList());
            existingEmployee.setProjects(updatedProjects);
        }


        Employee savedEmployee = employeeRepository.save(existingEmployee);


        return entityMapper.toEmployeeDTO(savedEmployee);
    }

    @Override
    public EmployeeDTO assignManagerToEmployee(Long employeeId, Long managerId) {
        Employee employee = employeeRepository.findById(Math.toIntExact(employeeId))
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        Employee manager = employeeRepository.findById(Math.toIntExact(managerId))
                .orElseThrow(() -> new IllegalArgumentException("Manager not found"));

        employee.setManager(manager);
        Employee updatedEmployee = employeeRepository.save(employee);
        return entityMapper.toEmployeeDTO(updatedEmployee);
    }


}

