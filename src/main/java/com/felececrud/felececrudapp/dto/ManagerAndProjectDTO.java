package com.felececrud.felececrudapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerAndProjectDTO {
        @NotNull(message = "Manager ID is required")
        private Long managerId;
}
