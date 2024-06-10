package com.karacamehmet.karacablog.core.exception.detail;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessProblemDetails extends ProblemDetails {
    public BusinessProblemDetails() {
        setType("https://karacablog.com/exceptions/business");
        setTitle("Business Rule Violation");
    }
}
