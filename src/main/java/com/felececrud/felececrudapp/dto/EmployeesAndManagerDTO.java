package com.felececrud.felececrudapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesAndManagerDTO {
    @NotEmpty(message = "Employee IDs are required")
    private List<Long> employeeIds;

    @NotNull(message = "Manager ID is required")
    private Long managerId;

}