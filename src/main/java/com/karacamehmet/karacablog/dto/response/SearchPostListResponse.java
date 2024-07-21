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
public class SearchPostListResponse {
    private List<SearchPostResponse> posts;
    private long totalPages;
}
