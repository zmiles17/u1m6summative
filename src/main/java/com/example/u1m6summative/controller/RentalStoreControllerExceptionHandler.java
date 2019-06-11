package com.example.u1m6summative.controller;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle Exception
 */
@RestControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class RentalStoreControllerExceptionHandler {
    /**
     * Exception when method arguments are not valid
     * @param e
     * @param request
     * @return Httpstatus code of 422 with responseEntity
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity<VndErrors> doValidationError(MethodArgumentNotValidException e, WebRequest request){
        //BindingResult holds the validation errors
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        List<VndErrors.VndError> vndErrorList = new ArrayList<>();
        for(FieldError error:fieldErrors){
            VndErrors.VndError vndError = new VndErrors.VndError(request.toString(),error.getDefaultMessage());
            vndErrorList.add(vndError);
        }
        VndErrors vndErrors = new VndErrors(vndErrorList);
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(vndErrors,HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }

    /**
     * Exception when normal flow of program breaks
     * @param e
     * @param request
     * @return Httpstatus code of 400 with responseEntity
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<VndErrors> badRequest(IllegalArgumentException e, WebRequest request){
        VndErrors error= new VndErrors(request.toString(),e.getMessage());
        ResponseEntity<VndErrors> responseEntity = new ResponseEntity<>(error,HttpStatus.UNPROCESSABLE_ENTITY);
        return responseEntity;
    }
}

