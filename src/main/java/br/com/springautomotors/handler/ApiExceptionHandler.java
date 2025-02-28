package br.com.springautomotors.handler;

import br.com.springautomotors.exception.PlacaUnicaViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(PlacaUnicaViolationException.class)
    public ResponseEntity<ApiErrorMessage> placaUnicaViolationException(RuntimeException ex){

        ApiErrorMessage apiErrorMessage = ApiErrorMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.name())
                .statusCode(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
//                .listOfErrors(List.of(ex.getMessage()))
                .build();

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiErrorMessage);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex){

        List<String> listErrors = new ArrayList<>();

        Map<String, String> mapErrors = new HashMap<>();

        for (FieldError error : ex.getFieldErrors()){
            mapErrors.put(error.getField(), error.getDefaultMessage());
            listErrors.add(error.getDefaultMessage());
        }


        ApiErrorMessage apiErrorMessage = ApiErrorMessage
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .mapOfErrors(mapErrors)
//                .listOfErrors(listErrors)
                .build();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiErrorMessage);
    }



    /* v2
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageValidations> methodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request,
            BindingResult result
    ) {

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorMessageValidations(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) inválidos", result));


    }
     */

}
