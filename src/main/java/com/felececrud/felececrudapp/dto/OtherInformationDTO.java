package com.felececrud.felececrudapp.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherInformationDTO {
    private Long id;
    private String address;
    private String bankName;
    private String iban;
    private String emergencyContactName;
    private String emergencyContactPhoneNumber;
}
