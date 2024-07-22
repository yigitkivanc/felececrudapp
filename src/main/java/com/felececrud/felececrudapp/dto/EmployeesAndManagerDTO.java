package com.felececrud.felececrudapp.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesAndManagerDTO {
    private List<Long> employeeIds;
    private Long managerId;


}