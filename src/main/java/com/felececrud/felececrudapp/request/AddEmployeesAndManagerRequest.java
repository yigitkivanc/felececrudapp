package com.felececrud.felececrudapp.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddEmployeesAndManagerRequest {
    @NotEmpty(message = "Employee IDs are required")
    private List<Long> employeeIds;

    @NotNull(message = "Manager ID is required")
    private Long managerId;

}
