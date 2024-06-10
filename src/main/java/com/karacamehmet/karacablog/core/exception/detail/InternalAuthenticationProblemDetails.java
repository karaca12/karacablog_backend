package com.karacamehmet.karacablog.core.exception.detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternalAuthenticationProblemDetails extends ProblemDetails {
    public InternalAuthenticationProblemDetails() {
        setType("https://karacablog.com/exceptions/business");
        setTitle("Business Rule Violation");
    }
}
