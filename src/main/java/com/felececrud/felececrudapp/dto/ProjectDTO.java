package com.felececrud.felececrudapp.dto;

import com.felececrud.felececrudapp.entity.Employee;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long id;

    @NotBlank(message = "Project name is required")
    private String projectName;


    @NotBlank(message = "Project type is required")
    private String projectType;


    @NotBlank(message = "Department is required")
    private String department;


    @NotBlank(message = "VPN username is required")
    private String vpnUsername;


    @NotBlank(message = "VPN password is required")
    private String vpnPassword;


    @NotBlank(message = "Environment is required")
    private String environmentDetails;
    private List<Long> employeeIds;
    private Long managerId;
}
