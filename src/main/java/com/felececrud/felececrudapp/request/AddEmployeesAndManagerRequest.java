package com.felececrud.felececrudapp.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddEmployeesAndManagerRequest {
    private List<Long> employeeIds;
    private Long managerId;

}
