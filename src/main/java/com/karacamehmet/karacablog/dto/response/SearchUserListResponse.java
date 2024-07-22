package com.karacamehmet.karacablog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchUserListResponse {
    private List<SearchUserResponse> users;
    private long totalPages;
}
