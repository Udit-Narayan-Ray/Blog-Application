package com.blog.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.ApiResponse;

@RestControllerAdvice
//since we are using rest api so used restcontrolleradvice if we would have used the controller the controlleradvice
//this for handling exception related to api at global scope

public class GlobalExceptionHandlerForRESTApi {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> exceptionHandlerforResource(ResourceNotFoundException e) {
		// getting message from exception
		String message = e.getMessage();

		return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
	}
	
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> exceptionHandlerforArgumentNotValid(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();

		e.getBindingResult().getAllErrors().forEach((error) -> {
			String field = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(field, message);
		});

		return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
	}

	
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ApiResponse> createSQLException(SQLException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), false), HttpStatus.BAD_REQUEST);
	}

	
	
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class, HttpMessageNotReadableException.class })
	public ResponseEntity<ApiResponse> httpMethodNotSupportedException() {
		return new ResponseEntity<ApiResponse>(
				new ApiResponse("!! Http Method not supported just check the URL and body !!", false),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<ApiResponse> fileNotFoundException()
	{
		return new ResponseEntity<ApiResponse>(new ApiResponse("File Not Found", false),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<ApiResponse> ioException()
	{
		return new ResponseEntity<ApiResponse>(new ApiResponse("Input Ouput Exception",false),HttpStatus.BAD_REQUEST);
	}
}
