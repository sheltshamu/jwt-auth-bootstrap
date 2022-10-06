package zw.co.getsol.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ApiException handleUserNotFoundException(UserNotFoundException e) {
        ApiException exception = new ApiException();
        exception.setErrorMessage(e.getMessage());
        exception.setStatusCode(HttpStatus.NOT_FOUND.value());
        exception.setTime(LocalDateTime.now());
        return exception;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public ApiException handleBadRequestException(BadRequestException e){
        ApiException exception = new ApiException();
        exception.setErrorMessage(e.getMessage());
        exception.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exception.setTime(LocalDateTime.now());
        return exception;
    }
}
