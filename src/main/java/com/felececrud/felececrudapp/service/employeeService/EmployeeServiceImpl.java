package com.felececrud.felececrudapp.service.employeeService;

import com.felececrud.felececrudapp.dto.*;
import com.felececrud.felececrudapp.entity.Employee;
import com.felececrud.felececrudapp.entity.OtherInformation;
import com.felececrud.felececrudapp.entity.PersonalInformation;
import com.felececrud.felececrudapp.entity.Project;
import com.felececrud.felececrudapp.enums.*;
import com.felececrud.felececrudapp.filterRequest.EmployeeFilterRequest;
import com.felececrud.felececrudapp.jparepository.EmployeeRepository;
import com.felececrud.felececrudapp.jparepository.OtherInformationRepository;
import com.felececrud.felececrudapp.jparepository.PersonalInformationRepository;
import com.felececrud.felececrudapp.jparepository.ProjectRepository;
import com.felececrud.felececrudapp.mapper.EntityMapper;
import com.felececrud.felececrudapp.projections.EmployeeProjection;
import com.felececrud.felececrudapp.specifications.EmployeeSpecification;
import com.felececrud.felececrudapp.validation.DuplicateFieldException;
import com.felececrud.felececrudapp.validation.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PersonalInformationRepository personalInformationRepository;

    @Autowired
    private OtherInformationRepository otherInformationRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        validateUniqueFieldsForCreate(employeeDTO);
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

        validateUniqueFieldsForUpdate(updatedEmployeeDTO, existingEmployee);
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

    @Override
    public List<EmployeeDTO> filterEmployees(EmployeeFilterRequest filterRequest) {
        Specification<Employee> spec = EmployeeSpecification.filter(filterRequest);
        List<Employee> employees = employeeRepository.findAll(spec);
        return employees.stream()
                .map(entityMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getSubordinates(Long managerId) {
        Employee manager = employeeRepository.findById(Math.toIntExact(managerId))
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        List<Employee> subordinates = employeeRepository.findByManager(manager);
        return subordinates.stream()
                .map(entityMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeProjectionDTO> getAllEmployeeProjections() {
        List<EmployeeProjection> projections = employeeRepository.findAllEmployeeProjections();
        return projections.stream()
                .map(entityMapper::projectionToEmployeeProjectionDTO).collect(Collectors.toList());
    }

    @Override
    public EmployeeProjectionDTO getEmployeeProjectionById(Long id) {
        EmployeeProjection theProjection = employeeRepository.findEmployeeProjectionById(id);
        return entityMapper.projectionToEmployeeProjectionDTO(theProjection);
    }

    @Override
    public void removeEmployeesFromProject(Long projectId, List<Long> employeeIds) {
        Project project = projectRepository.findById(Math.toIntExact(projectId))
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        List<Employee> employeesToRemove = employeeIds.stream()
                .map(id -> employeeRepository.findById(Math.toIntExact(id))
                        .orElseThrow(() -> new ResourceNotFoundException("Employee not found")))
                .collect(Collectors.toList());
        if (project.getManager() != null && employeeIds.contains(project.getManager().getId())) {
            project.setManager(null);
        }

        project.getEmployees().removeAll(employeesToRemove);
        projectRepository.save(project);
    }

    private void validateUniqueFieldsForUpdate(EmployeeDTO employeeDTO, Employee existingEmployee) {
        if (!employeeDTO.getPhoneNumber().equals(existingEmployee.getPhoneNumber()) &&
                employeeRepository.existsByPhoneNumber(employeeDTO.getPhoneNumber())) {
            throw new DuplicateFieldException("Phone number already exists");
        }

        if (!employeeDTO.getEmail().equals(existingEmployee.getEmail()) &&
                employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new DuplicateFieldException("Email address already exists");
        }

        if (employeeDTO.getPersonalInformation() != null &&
                !employeeDTO.getPersonalInformation().getNationalId().equals(existingEmployee.getPersonalInformation().getNationalId()) &&
                personalInformationRepository.existsByNationalId(employeeDTO.getPersonalInformation().getNationalId())) {
            throw new DuplicateFieldException("National ID already exists");
        }

        if (employeeDTO.getOtherInformation() != null &&
                !employeeDTO.getOtherInformation().getIban().equals(existingEmployee.getOtherInformation().getIban()) &&
                otherInformationRepository.existsByIban(employeeDTO.getOtherInformation().getIban())) {
            throw new DuplicateFieldException("IBAN already exists");
        }
    }
    private void validateUniqueFieldsForCreate(EmployeeDTO employeeDTO) {
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

