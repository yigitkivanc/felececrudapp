package com.felececrud.felececrudapp.service.projectService;

import com.felececrud.felececrudapp.dto.EmployeeDTO;
import com.felececrud.felececrudapp.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO projectDTO);
    ProjectDTO getProjectById(Long projectId);
    ProjectDTO updateProject(Long projectId, ProjectDTO projectDTO);
    void deleteProject(Long projectId);
    List<ProjectDTO> getAllProjects();
    List<EmployeeDTO> getEmployeesByProjectId(Long projectId);
    ProjectDTO addEmployeesAndManagerToProject(Long projectId, List<Long> employeeIds, Long managerId);
    ProjectDTO assignProjectToManager(Long projectId, Long managerId);

}
