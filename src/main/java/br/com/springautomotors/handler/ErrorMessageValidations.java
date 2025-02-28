package br.com.springautomotors.handler;


import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ErrorMessageValidations {


    private String path;
    private String method;
    private int status;
    private String statusText;
    private String message;
    private LocalDateTime timestamp;

    private Map<String, String> errors;


    public ErrorMessageValidations(HttpServletRequest request, HttpStatus status, String message, BindingResult result){
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.name();
        this.message = message;
        this.timestamp = LocalDateTime.now();
        addErrors(result);
    }

    private void addErrors(BindingResult result) {
        this.errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()){
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

}
