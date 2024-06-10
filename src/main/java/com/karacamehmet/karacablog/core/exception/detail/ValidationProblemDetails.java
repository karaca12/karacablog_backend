package com.karacamehmet.karacablog.core.exception.detail;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ValidationProblemDetails {
    private String title;
    private Map<String, String> detail;
    private String type;

    public ValidationProblemDetails() {
        setTitle("Validation Exception");
        setType("https://karacablog.com/exceptions/validation");
    }
}
