package com.felececrud.felececrudapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherInformationDTO {
    private Long id;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Bank Name is required")
    private String bankName;

    @NotBlank(message = "IBAN is required")
    private String iban;

    @NotBlank(message = "Emergency contact name is required")
    private String emergencyContactName;

    @NotBlank(message = "Emergency Contact Number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Emergency contact phone must be 10 digits")
    private String emergencyContactPhoneNumber;
}
