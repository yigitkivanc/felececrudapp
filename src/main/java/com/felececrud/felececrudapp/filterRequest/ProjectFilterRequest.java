package com.felececrud.felececrudapp.filterRequest;

import com.felececrud.felececrudapp.enums.ProjectType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectFilterRequest {
    private String projectName;
    private ProjectType projectType;
    private String department;
    private String vpnUsername;
    private String vpnPassword;
    private String environmentDetails;
    private Long employeeId;
    private Long managerId;


}
