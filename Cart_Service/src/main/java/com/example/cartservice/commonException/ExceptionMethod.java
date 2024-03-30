
package com.example.cartservice.commonException;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cartservice.exception.Exception_class;

@RestControllerAdvice
public class ExceptionMethod {
	@ExceptionHandler(Exception_class.class)
	public ResponseEntity<customErrorResponse> handleAccoundNotFounException(Exception_class ex)
	{
		customErrorResponse err=new customErrorResponse("NOT_FOUND_EXCPETION",ex.getMessage());
		err.setTimestamp(LocalDateTime.now());
		err.setStatus(HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<customErrorResponse>(err,HttpStatus.NOT_FOUND);
	}

}
