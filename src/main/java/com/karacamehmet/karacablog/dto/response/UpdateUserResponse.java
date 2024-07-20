package com.karacamehmet.karacablog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserResponse {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
}
