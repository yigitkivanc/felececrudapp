package com.felececrud.felececrudapp.controller;

import com.felececrud.felececrudapp.dto.EmployeeDTO;
import com.felececrud.felececrudapp.dto.EmployeesAndManagerDTO;
import com.felececrud.felececrudapp.dto.ProjectDTO;
import com.felececrud.felececrudapp.request.AddEmployeesAndManagerRequest;
import com.felececrud.felececrudapp.service.projectService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/createProject")
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }

    @GetMapping("/{projectId}")
    public ProjectDTO getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @PutMapping("/updateProject/{projectId}")
    public ProjectDTO updateProject(@PathVariable Long projectId, @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(projectId, projectDTO);
    }

    @DeleteMapping("/deleteProject/{projectId}")
    public void deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
    }

    @GetMapping("/listProjects")
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{projectId}/employees")
    public List<EmployeeDTO> getEmployeesByProjectId(@PathVariable Long projectId) {
        return projectService.getEmployeesByProjectId(projectId);
    }

    @PostMapping("/{projectId}/addEmployeesAndManager")
    public ProjectDTO addEmployeesAndManagerToProject(
            @PathVariable Long projectId,
            @RequestBody AddEmployeesAndManagerRequest request) {
        return projectService.addEmployeesAndManagerToProject(
                projectId,
                request.getEmployeeIds(),
                request.getManagerId());
    }
}
