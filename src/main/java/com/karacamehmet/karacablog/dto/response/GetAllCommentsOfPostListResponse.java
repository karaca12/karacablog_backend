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
public class GetAllCommentsOfPostListResponse {
    private List<GetAllCommentsOfPostResponse> comments;
    private long totalPages;
}
