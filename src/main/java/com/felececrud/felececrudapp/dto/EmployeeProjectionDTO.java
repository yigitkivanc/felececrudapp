package com.felececrud.felececrudapp.dto;

import com.felececrud.felececrudapp.entity.OtherInformation;
import com.felececrud.felececrudapp.entity.PersonalInformation;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProjectionDTO {

    private Long id;
    private String fullName;
    private OtherInformation otherInformation;
    private PersonalInformation personalInformation;
}
