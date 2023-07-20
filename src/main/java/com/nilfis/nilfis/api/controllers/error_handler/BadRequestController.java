package com.nilfis.nilfis.api.controllers.error_handler;

import com.nilfis.nilfis.api.models.responses.BaseErrorResponse;
import com.nilfis.nilfis.api.models.responses.ErrorResponse;
import com.nilfis.nilfis.api.models.responses.ErrorsResponse;
import com.nilfis.nilfis.util.exceptions.EmailJustExist;
import com.nilfis.nilfis.util.exceptions.IdNotFoundException;
import com.nilfis.nilfis.util.exceptions.NotVerifiedCustomer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;


@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
@AllArgsConstructor
public class BadRequestController {
    @ExceptionHandler(IdNotFoundException.class)
    public BaseErrorResponse handleIdNotFound(IdNotFoundException exception) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .error_code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(NotVerifiedCustomer.class)
    public BaseErrorResponse handleNotVerifiedCustomer(NotVerifiedCustomer exception) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .error_code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = new ArrayList<String>();
        exception.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        return ErrorsResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .error_code(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .build();
    }

    @ExceptionHandler(EmailJustExist.class)
    public  BaseErrorResponse handleEmailJustExist(EmailJustExist exception) {
        return ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .error_code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
    }
}
