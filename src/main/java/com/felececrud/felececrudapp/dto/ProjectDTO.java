package com.felececrud.felececrudapp.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {
    private Long id;
    private String projectName;
    private String projectType;
    private String department;
    private String vpnUsername;
    private String vpnPassword;
    private String environmentDetails;
    private Long employeeId;

}