package com.felececrud.felececrudapp.mapper;

import com.felececrud.felececrudapp.dto.*;
import com.felececrud.felececrudapp.entity.Employee;
import com.felececrud.felececrudapp.entity.OtherInformation;
import com.felececrud.felececrudapp.entity.PersonalInformation;
import com.felececrud.felececrudapp.entity.Project;
import com.felececrud.felececrudapp.enums.*;
import com.felececrud.felececrudapp.jparepository.EmployeeRepository;
import com.felececrud.felececrudapp.projections.EmployeeProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityMapper {
    @Autowired
    private EmployeeRepository employeeRepository;
    public Employee toEmployee(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setManager(dto.getManagerId() != null ? new Employee(dto.getManagerId()) : null); // Assuming Employee has a constructor with id
        employee.setLevel(Level.valueOf(dto.getLevel()));
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setEmail(dto.getEmail());
        employee.setBirthDate(dto.getBirthDate());
        employee.setWorkType(WorkType.valueOf(dto.getWorkType()));
        employee.setContractType(ContractType.valueOf(dto.getContractType()));
        employee.setTeam(Team.valueOf(dto.getTeam()));
        employee.setStartDate(dto.getStartDate());
        employee.setEndDate(dto.getEndDate());
        employee.setPersonalInformation(toPersonalInformation(dto.getPersonalInformation()));
        employee.setOtherInformation(toOtherInformation(dto.getOtherInformation()));
        if(!CollectionUtils.isEmpty(dto.getProjects())){
            employee.setProjects(dto.getProjects().stream().map(this::toProject).collect(Collectors.toList()));
        }
        return employee;
    }


    public EmployeeDTO toEmployeeDTO(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setManagerId(employee.getManager() != null ? employee.getManager().getId() : null);
        dto.setLevel(employee.getLevel().name());
        dto.setPhoneNumber(employee.getPhoneNumber());
        dto.setEmail(employee.getEmail());
        dto.setBirthDate(employee.getBirthDate());
        dto.setWorkType(employee.getWorkType().name());
        dto.setContractType(employee.getContractType().name());
        dto.setTeam(employee.getTeam().name());
        dto.setStartDate(employee.getStartDate());
        dto.setEndDate(employee.getEndDate());
        dto.setPersonalInformation(toPersonalInformationDTO(employee.getPersonalInformation()));
        dto.setOtherInformation(toOtherInformationDTO(employee.getOtherInformation()));
        if(!CollectionUtils.isEmpty(employee.getProjects())){
            dto.setProjects(employee.getProjects().stream().map(this::toProjectDTO).collect(Collectors.toList()));

        }
        return dto;
    }

    public PersonalInformationDTO toPersonalInformationDTO(PersonalInformation personalInformation) {
        if (personalInformation == null) {
            return null;
        }

        PersonalInformationDTO dto = new PersonalInformationDTO();
        dto.setId(personalInformation.getId());
        dto.setBirthDate(personalInformation.getBirthDate());
        dto.setNationalId(personalInformation.getNationalId());
        dto.setMilitaryStatus(personalInformation.getMilitaryStatus().name());
        dto.setGender(personalInformation.getGender().name());
        dto.setMaritalStatus(personalInformation.getMaritalStatus().name());

        return dto;
    }

    public PersonalInformation toPersonalInformation(PersonalInformationDTO dto) {
        if (dto == null) {
            return null;
        }

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setId(dto.getId());
        personalInformation.setBirthDate(dto.getBirthDate());
        personalInformation.setNationalId(dto.getNationalId());
        personalInformation.setMilitaryStatus(MilitaryStatus.valueOf(dto.getMilitaryStatus()));
        personalInformation.setGender(Gender.valueOf(dto.getGender()));
        personalInformation.setMaritalStatus(MaritalStatus.valueOf(dto.getMaritalStatus()));

        return personalInformation;
    }

    public OtherInformationDTO toOtherInformationDTO(OtherInformation otherInformation) {
        if (otherInformation == null) {
            return null;
        }

        OtherInformationDTO dto = new OtherInformationDTO();
        dto.setId(otherInformation.getId());
        dto.setAddress(otherInformation.getAddress());
        dto.setBankName(otherInformation.getBankName());
        dto.setIban(otherInformation.getIban());
        dto.setEmergencyContactName(otherInformation.getEmergencyContactName());
        dto.setEmergencyContactPhoneNumber(otherInformation.getEmergencyContactPhoneNumber());

        return dto;
    }

    public OtherInformation toOtherInformation(OtherInformationDTO dto) {
        if (dto == null) {
            return null;
        }

        OtherInformation otherInformation = new OtherInformation();
        otherInformation.setId(dto.getId());
        otherInformation.setAddress(dto.getAddress());
        otherInformation.setBankName(dto.getBankName());
        otherInformation.setIban(dto.getIban());
        otherInformation.setEmergencyContactName(dto.getEmergencyContactName());
        otherInformation.setEmergencyContactPhoneNumber(dto.getEmergencyContactPhoneNumber());

        return otherInformation;
    }

    public ProjectDTO toProjectDTO(Project project) {
        if (project == null) {
            return null;
        }

        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setProjectName(project.getProjectName());
        dto.setProjectType(project.getProjectType().name());
        dto.setDepartment(project.getDepartment());
        dto.setVpnUsername(project.getVpnUsername());
        dto.setVpnPassword(project.getVpnPassword());
        dto.setEnvironmentDetails(project.getEnvironmentDetails());
        if (project.getEmployees() != null) {
            List<Long> employeeIds = project.getEmployees().stream()
                    .map(Employee::getId)
                    .collect(Collectors.toList());
            dto.setEmployeeIds(employeeIds);
        }

        if (project.getManager() != null) {
            dto.setManagerId(project.getManager().getId());
        }

        return dto;
    }

    public Project toProject(ProjectDTO dto) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setProjectName(dto.getProjectName());
        project.setProjectType(ProjectType.valueOf(dto.getProjectType()));
        project.setDepartment(dto.getDepartment());
        project.setVpnUsername(dto.getVpnUsername());
        project.setVpnPassword(dto.getVpnPassword());
        project.setEnvironmentDetails(dto.getEnvironmentDetails());

        if (dto.getEmployeeIds() != null) {
            List<Employee> employees = dto.getEmployeeIds().stream()
                    .map(id -> employeeRepository.findById(Math.toIntExact(id))
                            .orElseThrow(() -> new IllegalArgumentException("Employee not found")))
                    .collect(Collectors.toList());
            project.setEmployees(employees);
        }

        if (dto.getManagerId() != null) {
            Employee manager = employeeRepository.findById(Math.toIntExact(dto.getManagerId()))
                    .orElseThrow(() -> new IllegalArgumentException("Manager not found"));
            project.setManager(manager);
        }

        return project;
    }

    public EmployeeProjectionDTO projectionToEmployeeProjectionDTO(EmployeeProjection projection) {
        EmployeeProjectionDTO dto = new EmployeeProjectionDTO();
        dto.setId(projection.getId());
        dto.setFullName(projection.getFullName());
        dto.setOtherInformation(projection.getOtherInformation());
        dto.setPersonalInformation(projection.getPersonalInformation());
        return dto;
    }
}