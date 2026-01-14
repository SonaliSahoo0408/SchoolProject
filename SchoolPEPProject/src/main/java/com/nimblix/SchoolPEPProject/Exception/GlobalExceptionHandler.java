package com.nimblix.SchoolPEPProject.Exception;

import com.nimblix.SchoolPEPProject.Controller.AdminController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<View> handleUserNotFound(Exception e){

        Map<String,Object> map=new HashMap<>();
        map.put("message",e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
     @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundEntire(UserNotFoundException ex){
        Map<String,Object> map=new HashMap<>();
        map.put("message",ex.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
