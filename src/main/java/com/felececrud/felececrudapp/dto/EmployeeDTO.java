package com.felececrud.felececrudapp.dto;

import com.felececrud.felececrudapp.entity.Project;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {


        private Long id;
        private String firstName;
        private String lastName;
        private Long managerId;
        private String level;
        private String phoneNumber;
        private String email;
        private Date birthDate;
        private String workType;
        private String contractType;
        private String team;
        private Date startDate;
        private Date endDate;
        private PersonalInformationDTO personalInformation;
        private OtherInformationDTO otherInformation;
        private List<ProjectDTO> projects;


}
