package com.felececrud.felececrudapp.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformationDTO {

    private Long id;

    @NotNull(message = "Birth date can not be empty")
    @Past(message = "Birth day must be in the past")
    private Date birthDate;

    @NotBlank(message = "National ID is required")
    @Pattern(regexp = "^[0-9]{11}$", message = "National ID must be 11 digits")
    private String nationalId;

    @NotBlank(message = "Military status is required")
    private String militaryStatus;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Marital Status is required")
    private String maritalStatus;


}
