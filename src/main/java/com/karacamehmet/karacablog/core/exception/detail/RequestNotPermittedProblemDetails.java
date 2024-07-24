package com.karacamehmet.karacablog.core.exception.detail;

public class RequestNotPermittedProblemDetails extends ProblemDetails{
    public RequestNotPermittedProblemDetails() {
        setType("https://karacablog.com/exceptions/business");
        setTitle("Business Rule Violation");
    }
}
