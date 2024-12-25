package com.example.project3.ControllerAdvise;

import com.example.project3.API.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<?> DuplicateKeyException(DuplicateKeyException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<?> DataIntegrityViolationException(DataIntegrityViolationException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }


    @ExceptionHandler(value = JpaSystemException.class)
    public ResponseEntity<?> JpaSystemException(JpaSystemException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> ApiException(ApiException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }

    @ExceptionHandler(value = ClassCastException.class)
    public ResponseEntity<?> ClassCastException(ClassCastException e){
        String msg = e.getMessage();
        return ResponseEntity.status(400).body(msg);
    }
}
