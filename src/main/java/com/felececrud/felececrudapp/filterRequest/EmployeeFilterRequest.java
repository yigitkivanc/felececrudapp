package com.felececrud.felececrudapp.filterRequest;

import com.felececrud.felececrudapp.enums.ContractType;
import com.felececrud.felececrudapp.enums.Level;
import com.felececrud.felececrudapp.enums.Team;
import com.felececrud.felececrudapp.enums.WorkType;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
@Getter
@Setter
public class EmployeeFilterRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private Level level;
    private WorkType workType;
    private ContractType contractType;
    private Team team;
    private Date startDate;
    private Date endDate;

}