package com.felececrud.felececrudapp.dto;

import com.felececrud.felececrudapp.entity.Project;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

        @NotBlank(message = "First name can not be empty")
        private String firstName;

        @NotBlank(message = "Last name can not be empty")
        private String lastName;

        private Long managerId;

        @NotBlank(message = "Level can not be empty")
        private String level;

        @NotBlank(message = "Phone number can not be empty")
        private String phoneNumber;

        @NotBlank(message = "Email can not be empty")
        private String email;

        @NotNull(message = "Birth date can not be empty")
        @Past(message = "Birth day must be in the past")
        private Date birthDate;

        @NotBlank(message = "Work Type can not be empty")
        private String workType;

        @NotBlank(message = "Contract Type can not be empty")
        private String contractType;

        @NotBlank(message = "Team can not be empty")
        private String team;

        @NotNull(message = "Start date can not be empty")
        @PastOrPresent(message = "Start date must be in the past or present")
        private Date startDate;

        @Future(message = "End date must be in the future")
        private Date endDate;

        @Valid
        @NotNull(message = "Personal Information can not be empty")
        private PersonalInformationDTO personalInformation;

        @Valid
        @NotNull(message = "Other Information can not be empty")
        private OtherInformationDTO otherInformation;


        private List<ProjectDTO> projects;


}
