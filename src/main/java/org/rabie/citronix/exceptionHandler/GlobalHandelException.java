package org.rabie.citronix.exceptionHandler;

import org.rabie.citronix.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class GlobalHandelException {

    @ExceptionHandler(FarmNullException.class)
    public String farmNull(FarmNullException e){
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            if(!errors.containsKey(field))
                errors.put(field, List.of(Objects.requireNonNull(error.getDefaultMessage())));
            else
                errors.get(field).add(error.getDefaultMessage());

        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


    @ExceptionHandler(AreaOfFiledMustBeInfAreaOfFarmException.class)
    public String farmNull(AreaOfFiledMustBeInfAreaOfFarmException e){
        return e.getMessage();
    }


    @ExceptionHandler(FieldsNullException.class)
    public String FieldsNullException(FieldsNullException e){
        return e.getMessage();
    }
    @ExceptionHandler(TreeNullException.class)
    public String TreeNullException(TreeNullException e){
        return e.getMessage();
    }

    @ExceptionHandler(FarmMustNotBeHaveFieldsException.class)
    public String farmNull(FarmMustNotBeHaveFieldsException e){
        return e.getMessage();
    }
}
