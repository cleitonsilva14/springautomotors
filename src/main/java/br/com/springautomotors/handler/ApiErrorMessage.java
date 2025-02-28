package br.com.springautomotors.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ApiErrorMessage(
        Integer statusCode,
        String status,
        @JsonFormat(pattern = "dd-MM-YYYY HH:mm:ss")
        LocalDateTime timestamp,
        String message,
        List<String> listOfErrors,
        Map<String, String> mapOfErrors
) {


}
