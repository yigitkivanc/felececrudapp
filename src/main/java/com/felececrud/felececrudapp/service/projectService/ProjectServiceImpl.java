package com.felececrud.felececrudapp.service.projectService;

import com.felececrud.felececrudapp.dto.EmployeeDTO;
import com.felececrud.felececrudapp.dto.ProjectDTO;
import com.felececrud.felececrudapp.entity.Employee;
import com.felececrud.felececrudapp.entity.Project;
import com.felececrud.felececrudapp.enums.ProjectType;
import com.felececrud.felececrudapp.filterRequest.ProjectFilterRequest;
import com.felececrud.felececrudapp.jparepository.EmployeeRepository;
import com.felececrud.felececrudapp.jparepository.ProjectRepository;
import com.felececrud.felececrudapp.mapper.EntityMapper;
import com.felececrud.felececrudapp.specifications.ProjectSpecification;
import com.felececrud.felececrudapp.validation.DuplicateFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityMapper entityMapper;

    @Override
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        validateUniqueFieldsByProjectName(projectDTO, null);
        validateUniqueFieldsByVpnUsername(projectDTO,null);
        Project project = entityMapper.toProject(projectDTO);
        Project savedProject = projectRepository.save(project);
        return entityMapper.toProjectDTO(savedProject);
    }

    @Override
    public ProjectDTO getProjectById(Long projectId) {
        Project project = projectRepository.findById(Math.toIntExact(projectId))
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        return entityMapper.toProjectDTO(project);
    }

    @Override
    public ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(Math.toIntExact(projectId))
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        validateUniqueFieldsByVpnUsername(projectDTO, existingProject);


        existingProject.setProjectName(projectDTO.getProjectName());
        existingProject.setProjectType(ProjectType.valueOf(projectDTO.getProjectType()));
        existingProject.setDepartment(projectDTO.getDepartment());
        existingProject.setVpnUsername(projectDTO.getVpnUsername());
        existingProject.setVpnPassword(projectDTO.getVpnPassword());
        existingProject.setEnvironmentDetails(projectDTO.getEnvironmentDetails());

        if (projectDTO.getEmployeeIds() != null) {
            List<Employee> employees = projectDTO.getEmployeeIds().stream()
                    .map(id -> employeeRepository.findById(Math.toIntExact(id))
                            .orElseThrow(() -> new IllegalArgumentException("Employee not found")))
                    .collect(Collectors.toList());
            existingProject.setEmployees(employees);
        }

        if (projectDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(Math.toIntExact(projectDTO.getManagerId()))
                    .orElseThrow(() -> new IllegalArgumentException("Manager not found"));
            existingProject.setManager(manager);
        } else {
            existingProject.setManager(null);
        }

        Project savedProject = projectRepository.save(existingProject);
        return entityMapper.toProjectDTO(savedProject);
    }

    @Override
    public void deleteProject(Long projectId) {
        projectRepository.deleteById(Math.toIntExact(projectId));
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream()
                .map(entityMapper::toProjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByProjectId(Long projectId) {
        Project project = projectRepository.findById(Math.toIntExact(projectId))
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
        List<Employee> employees = project.getEmployees();
        return employees.stream()
                .map(entityMapper::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO addEmployeesAndManagerToProject(Long projectId, List<Long> employeeIds, Long managerId) {
        Project project = projectRepository.findById(Math.toIntExact(projectId))
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        if (employeeIds != null) {
            List<Employee> employees = employeeIds.stream()
                    .map(id -> employeeRepository.findById(Math.toIntExact(id))
                            .orElseThrow(() -> new IllegalArgumentException("Employee not found")))
                    .collect(Collectors.toList());
            project.setEmployees(employees);
        }

        if (managerId != null) {
            Employee manager = employeeRepository.findById(Math.toIntExact(managerId))
                    .orElseThrow(() -> new IllegalArgumentException("Manager not found"));
            project.setManager(manager);
        } else {
            project.setManager(null);
        }

        Project savedProject = projectRepository.save(project);
        return entityMapper.toProjectDTO(savedProject);
    }

    @Override
    public ProjectDTO assignProjectToManager(Long projectId, Long managerId) {
        Project project = projectRepository.findById(Math.toIntExact(projectId))
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Employee manager = employeeRepository.findById(Math.toIntExact(managerId))
                .orElseThrow(() -> new IllegalArgumentException("Manager not found"));

        project.setManager(manager);

        if (manager.getProjects() == null) {
            manager.setProjects(new ArrayList<>());
        }
        manager.getProjects().add(project);

        project = projectRepository.save(project);
        employeeRepository.save(manager);

        return entityMapper.toProjectDTO(project);
    }

    @Override
    public List<ProjectDTO> filterProjects(ProjectFilterRequest filterRequest) {
        Specification<Project> spec = ProjectSpecification.filter(filterRequest);
        List<Project> projects = projectRepository.findAll(spec);
        return projects.stream()
                .map(entityMapper::toProjectDTO)
                .collect(Collectors.toList());
    }

    private void validateUniqueFieldsByProjectName(ProjectDTO projectDTO, Project existingProject) {
        if ((existingProject == null || !projectDTO.getProjectName().equals(existingProject.getProjectName())) &&
                projectRepository.existsByProjectName(projectDTO.getProjectName())) {
            throw new DuplicateFieldException("Project name already exists");
        }
    }
    private void validateUniqueFieldsByVpnUsername(ProjectDTO projectDTO, Project existingProject){
        if ((existingProject == null || !projectDTO.getVpnUsername().equals(existingProject.getVpnUsername())) &&
                projectRepository.existsByVpnUsername(projectDTO.getVpnUsername())) {
            throw new DuplicateFieldException("VPN username already exists");
        }
    }
}



