package com.felececrud.felececrudapp.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformationDTO {

        private Long id;
        private Date birthDate;
        private String nationalId;
        private String militaryStatus;
        private String gender;
        private String maritalStatus;



}
