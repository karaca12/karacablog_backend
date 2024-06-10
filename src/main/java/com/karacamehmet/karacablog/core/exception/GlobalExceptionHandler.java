package com.karacamehmet.karacablog.core.exception;

import com.karacamehmet.karacablog.core.exception.detail.BadCredentialsProblemDetails;
import com.karacamehmet.karacablog.core.exception.detail.BusinessProblemDetails;
import com.karacamehmet.karacablog.core.exception.detail.InternalAuthenticationProblemDetails;
import com.karacamehmet.karacablog.core.exception.detail.ValidationProblemDetails;
import com.karacamehmet.karacablog.core.exception.type.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BusinessProblemDetails handleBusinessException(BusinessException exception) {
        BusinessProblemDetails problemDetails = new BusinessProblemDetails();
        problemDetails.setDetail(exception.getMessage());
        return problemDetails;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationProblemDetails handleValidationException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        Map<String, String> errorDetails = new HashMap<>();
        for (FieldError error : fieldErrors) {
            errorDetails.put(error.getField(), error.getDefaultMessage());
        }

        ValidationProblemDetails problemDetails = new ValidationProblemDetails();
        problemDetails.setDetail(errorDetails);

        return problemDetails;

    }

    @ExceptionHandler({BadCredentialsException.class})
    public BadCredentialsProblemDetails handleBadCredentialsException(BadCredentialsException exception) {
        BadCredentialsProblemDetails problemDetails = new BadCredentialsProblemDetails();
        problemDetails.setDetail(exception.getMessage());
        return problemDetails;
    }

    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public InternalAuthenticationProblemDetails InternalAuthenticationServiceException(InternalAuthenticationServiceException exception) {
        InternalAuthenticationProblemDetails problemDetails = new InternalAuthenticationProblemDetails();
        problemDetails.setDetail(exception.getMessage());
        return problemDetails;
    }
    //unlock later
    /*@ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetails handleException() {
        return new ProblemDetails("Unknown Error", "Some error occurred.", "https://karacablog.com/exceptions/unknown");
    }*/
}
