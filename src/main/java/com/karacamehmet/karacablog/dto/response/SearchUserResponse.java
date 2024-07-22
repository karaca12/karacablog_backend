package com.karacamehmet.karacablog.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchUserResponse {
    private String username;
    private String firstName;
    private String lastName;
}
